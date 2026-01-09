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
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class ChestOpenCallback {

    public static void register() {
        UseBlockCallback.EVENT.register(ChestOpenCallback::onUseBlock);
    }

    private static ActionResult onUseBlock(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        if (!world.isClient()) {
            Block block = world.getBlockState(hitResult.getBlockPos()).getBlock();

            if (block == Blocks.CHEST || block == Blocks.TRAPPED_CHEST) {
                System.out.println("A player tried to open a chest!");

                if (player instanceof ServerPlayerEntity serverPlayer) {
                    serverPlayer.networkHandler.sendPacket(new TitleS2CPacket(Text.literal("Warning")));
                    serverPlayer.networkHandler.sendPacket(new SubtitleS2CPacket(Text.literal("You opened a chest!")));
                    serverPlayer.networkHandler.sendPacket(new TitleFadeS2CPacket(10, 60, 20));

                    serverPlayer.sendMessage(Text.literal("Chest opened!"), true);
                }

                return ActionResult.FAIL;
            }
        }
        return ActionResult.PASS;
    }

}
