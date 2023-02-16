package io.github.phoenixfirewingz.customsounds.gui;

import io.github.phoenixfirewingz.customsounds.CustomSounds;
import io.github.phoenixfirewingz.customsounds.block.SoundNode;
import io.github.phoenixfirewingz.customsounds.block.SoundNodeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;

public class NodeScreenHandle extends ScreenHandler {
    public NodeScreenHandle(int syncId, PlayerInventory playerInventory) {
        super(CustomSounds.node_handle, syncId);
    }
    public NodeScreenHandle(int syncId, PlayerInventory playerInventory, SoundNodeEntity entity) {
        super(CustomSounds.node_handle, syncId);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return player.isCreative();
    }
}