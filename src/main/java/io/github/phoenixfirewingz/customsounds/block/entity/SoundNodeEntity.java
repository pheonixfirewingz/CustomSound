package io.github.phoenixfirewingz.customsounds.block.entity;

import io.github.phoenixfirewingz.customsounds.CustomSounds;
import io.github.phoenixfirewingz.customsounds.gui.NodeScreenHandle;
import io.github.phoenixfirewingz.customsounds.util.PropertySaver;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class SoundNodeEntity extends BlockEntity implements NamedScreenHandlerFactory {
    protected String url = "";
    protected final PropertySaver saver = new PropertySaver() {
        @Override
        public String get(int index) {
            return url;
        }

        @Override
        public void set(int index, String value) {
            url = value;
            markDirty();
        }

        @Override
        public int size() {
            return 1;
        }
    };

    public SoundNodeEntity(BlockPos pos, BlockState state) {
        super(CustomSounds.SOUND_NODE_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        url = Arrays.toString(nbt.getByteArray("url"));
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        if(url != null)
            nbt.putByteArray("url",url.getBytes());
        super.writeNbt(nbt);
    }

    @Override
    public Text getDisplayName() {
        return  Text.translatable("container.sound_node.sound_node_controller");
    }


    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new NodeScreenHandle(syncId,saver);
    }
}
