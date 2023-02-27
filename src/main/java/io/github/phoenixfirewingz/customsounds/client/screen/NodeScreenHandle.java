package io.github.phoenixfirewingz.customsounds.client.screen;

import io.github.phoenixfirewingz.customsounds.CustomSounds;
import io.github.phoenixfirewingz.customsounds.block.entity.SoundNodeEntity;
import io.github.phoenixfirewingz.customsounds.util.ArrayPropertySaver;
import io.github.phoenixfirewingz.customsounds.util.PropertySaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class NodeScreenHandle extends ScreenHandler {
    protected SoundNodeEntity entity = null;

    public NodeScreenHandle(int syncId, PlayerInventory playerInventory) {
        this(syncId);
    }
    public NodeScreenHandle(int syncId) {
        super(CustomSounds.SOUND_NODE_SCREEN_HANDLER_TYPE, syncId);
    }

    public SoundNodeEntity getEntity() {
        return entity;
    }

    public NodeScreenHandle setEntity(SoundNodeEntity entity) {
        this.entity = entity;
        return this;
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