package io.github.phoenixfirewingz.customsounds.block.entity;

import io.github.phoenixfirewingz.customsounds.client.screen.NodeScreenHandle;
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
import org.slf4j.LoggerFactory;

import static io.github.phoenixfirewingz.customsounds.CustomSounds.SOUND_NODE_ENTITY_BLOCK_ENTITY_TYPE;

public class SoundNodeEntity extends BlockEntity implements NamedScreenHandlerFactory {
    protected String url;
    public SoundNodeEntity(BlockPos pos, BlockState state) {
        super(SOUND_NODE_ENTITY_BLOCK_ENTITY_TYPE, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.setUrl(nbt.getString("url"));
        LoggerFactory.getLogger("Minecraft").info(this.toString());
        LoggerFactory.getLogger("Minecraft").info("NBT:" + nbt.toString());
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        if(this.getUrl() != null && !this.getUrl().isEmpty())
            nbt.putString("url",this.getUrl());
        LoggerFactory.getLogger("Minecraft").info(this.toString());
        LoggerFactory.getLogger("Minecraft").info("URL:" + this.getUrl());
    }



    @Override
    public Text getDisplayName() {
        return  Text.translatable("container.sound_node.sound_node");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new NodeScreenHandle(syncId, inv);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
