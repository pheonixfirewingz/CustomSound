package io.github.phoenixfirewingz.customsounds.networking.packet;

import io.github.phoenixfirewingz.customsounds.block.SoundNode;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class SoundNodeSyncC2SPacket {
    public static void receive(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf buf, PacketSender packetSender) {
        BlockPos position = buf.readBlockPos();
        for(ServerWorld world:minecraftServer.getWorlds()) {
            if (world.getBlockEntity(position) instanceof SoundNode.SoundNodeEntity blockEntity){
                blockEntity.setUrl(buf.readString());
            }
        }
    }
}
