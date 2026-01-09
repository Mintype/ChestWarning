package org.mintype.chestwarningmod;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
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
                System.out.println("A player opened a chest!");
            }
        }
        return ActionResult.PASS;
    }
}
