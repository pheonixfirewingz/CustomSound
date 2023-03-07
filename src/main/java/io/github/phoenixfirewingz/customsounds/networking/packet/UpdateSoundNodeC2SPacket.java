package io.github.phoenixfirewingz.customsounds.networking.packet;

import io.github.phoenixfirewingz.customsounds.block.SoundNode;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.slf4j.LoggerFactory;

public class UpdateSoundNodeC2SPacket {
    public static void receive(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity,
                               ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf buf, PacketSender packetSender) {
        BlockPos pos = buf.readBlockPos();
        String url = buf.readString();
        minecraftServer.execute(() -> {
            BlockEntity entity = serverPlayerEntity.getWorld().getBlockEntity(pos);
            if (entity instanceof SoundNode.SoundNodeEntity) {
                ((SoundNode.SoundNodeEntity) entity).setUrl(url);
                entity.markDirty();
            }
        });
    }
}
