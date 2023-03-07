package io.github.phoenixfirewingz.customsounds.networking.packet;

import io.github.phoenixfirewingz.customsounds.block.SoundNode;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import org.slf4j.LoggerFactory;

public class SoundNodeSyncS2CPacket {

    public static void receive(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf buf, PacketSender packetSender) {
        BlockPos pos = buf.readBlockPos();
        String url = buf.readString();
        client.execute(() -> {
            BlockEntity entity = client.world.getBlockEntity(pos);
            if (entity instanceof SoundNode.SoundNodeEntity)
                ((SoundNode.SoundNodeEntity) entity).setUrl(url);
        });
    }
    }
