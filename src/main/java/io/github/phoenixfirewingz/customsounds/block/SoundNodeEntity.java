package io.github.phoenixfirewingz.customsounds.block;

import io.github.phoenixfirewingz.customsounds.CustomSounds;
import io.github.phoenixfirewingz.customsounds.gui.NodeScreenHandle;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class SoundNodeEntity extends BlockEntity implements NamedScreenHandlerFactory {
    public SoundNodeEntity(BlockPos pos, BlockState state) {
        super(CustomSounds.node_entity, pos, state);
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new NodeScreenHandle(syncId,playerInventory);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
    }
}
