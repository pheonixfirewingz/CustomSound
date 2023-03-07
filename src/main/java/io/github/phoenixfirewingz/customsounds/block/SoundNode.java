package io.github.phoenixfirewingz.customsounds.block;

import io.github.phoenixfirewingz.customsounds.CustomSounds;
import io.github.phoenixfirewingz.customsounds.client.screen.NodeScreenHandle;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static io.github.phoenixfirewingz.customsounds.CustomSounds.SOUND_NODE_ENTITY_BLOCK_ENTITY_TYPE;

public class SoundNode extends BlockWithEntity {
    public SoundNode(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SoundNodeEntity(pos,state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient())
        {
            NamedScreenHandlerFactory screenHandlerFactory = ((SoundNodeEntity) world.getBlockEntity(pos));
            if (screenHandlerFactory != null)
                player.openHandledScreen(screenHandlerFactory);
        }
        else
            super.onUse(state,world,pos,player,hand,hit);
        return ActionResult.SUCCESS;
    }

    public static class SoundNodeEntity extends BlockEntity implements ExtendedScreenHandlerFactory {
        protected String url;
        public SoundNodeEntity(BlockPos pos, BlockState state) {
            super(SOUND_NODE_ENTITY_BLOCK_ENTITY_TYPE, pos, state);
        }

        @Override
        public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
            buf.writeBlockPos(this.getPos());
            buf.writeString(this.getUrl());
        }

        @Override
        public void readNbt(NbtCompound nbt) {
            super.readNbt(nbt);
            this.setUrl(nbt.getString("url"));
        }

        @Override
        public void writeNbt(NbtCompound nbt) {
            super.writeNbt(nbt);
            nbt.putString("url",this.getUrl());
        }

        @Override
        public Text getDisplayName() {
            return  Text.translatable("container.sound_node.sound_node");
        }

        @Nullable
        @Override
        public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeBlockPos(this.getPos());
            ClientPlayNetworking.send(CustomSounds.NODE_SYNC_ID, buf);
            return new NodeScreenHandle(syncId, inv,this);
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
