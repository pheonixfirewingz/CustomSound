package io.github.phoenixfirewingz.customsounds.networking.packet;

import io.github.phoenixfirewingz.customsounds.CustomSounds;
import io.github.phoenixfirewingz.customsounds.block.SoundNode;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.slf4j.LoggerFactory;

public class SoundNodeSyncC2SPacket {
    public static void receive(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity,
                               ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf buf, PacketSender packetSender) {
        BlockPos pos = buf.readBlockPos();
        minecraftServer.execute(() -> {
            BlockEntity entity = serverPlayerEntity.getWorld().getBlockEntity(pos);
            if (entity instanceof SoundNode.SoundNodeEntity) {
                PacketByteBuf ret_buf = PacketByteBufs.create();
                ret_buf.writeBlockPos(entity.getPos());
                ret_buf.writeString(((SoundNode.SoundNodeEntity) entity).getUrl());
                ServerPlayNetworking.send(serverPlayerEntity, CustomSounds.NODE_SYNC_ID,ret_buf);
            }
        });
    }
}
