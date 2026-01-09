package org.mintype.chestwarningmod;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.SubtitleS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleFadeS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.mintype.chestwarningmod.storage.WarnedPlayersStorage;

public class ChestOpenCallback {

    /**
     * public api to register the callback
     */
    public static void register() {
        UseBlockCallback.EVENT.register(ChestOpenCallback::onUseBlock);
    }

    /**
     * CallBack that triggers when any player in the server opens a chest
     */
    private static ActionResult onUseBlock(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        if (!world.isClient()) {
            Block block = world.getBlockState(hitResult.getBlockPos()).getBlock();

            if (block == Blocks.CHEST || block == Blocks.TRAPPED_CHEST) {

                // For debugging purposes only
                System.out.println("A player tried to open a chest!");

                if (player instanceof ServerPlayerEntity serverPlayer) {

                    /*
                    * Check if player has been warned yet
                    * If true, warn them
                    * Else continue
                    */
                    if (!WarnedPlayersStorage.hasBeenWarned(serverPlayer)) {
                        // Main title
                        serverPlayer.networkHandler.sendPacket(new TitleS2CPacket(
                                Text.literal("NO GRIEFING!")
                                        .formatted(Formatting.RED, Formatting.BOLD)
                        ));

                        // Subtitle
                        serverPlayer.networkHandler.sendPacket(new SubtitleS2CPacket(
                                Text.literal("Stealing is a ")
                                        .append(Text.literal("BANNABLE").formatted(Formatting.DARK_RED, Formatting.UNDERLINE))
                                        .append(Text.literal(" offense."))
                        ));

                        // Fade in, stay, fade out times
                        serverPlayer.networkHandler.sendPacket(new TitleFadeS2CPacket(10, 120, 20));

                        // Set the boolean to true so they aren't warned again
                        WarnedPlayersStorage.setWarned(serverPlayer, true);

                        // Fail the chest opening action so the player can see the message
                        return ActionResult.FAIL;
                    }
                }

                return ActionResult.PASS;
            }
        }
        return ActionResult.PASS;
    }

}
