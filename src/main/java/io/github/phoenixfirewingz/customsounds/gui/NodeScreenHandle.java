package io.github.phoenixfirewingz.customsounds.gui;

import io.github.phoenixfirewingz.customsounds.CustomSounds;
import io.github.phoenixfirewingz.customsounds.util.ArrayPropertySaver;
import io.github.phoenixfirewingz.customsounds.util.PropertySaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class NodeScreenHandle extends ScreenHandler {
    protected final PropertySaver propertyDelegate;

    public NodeScreenHandle(int syncId, PlayerInventory playerInventory) {
        this(syncId, new ArrayPropertySaver(1));
    }
    public NodeScreenHandle(int syncId, PropertySaver propertyDelegate) {
        super(CustomSounds.SOUND_NODE_SCREEN_HANDLER_TYPE, syncId);
        this.propertyDelegate = propertyDelegate;
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