package io.github.phoenixfirewingz.customsounds;

import com.mojang.datafixers.types.Type;
import io.github.phoenixfirewingz.customsounds.block.SoundNode;
import io.github.phoenixfirewingz.customsounds.client.screen.NodeScreenHandle;
import io.github.phoenixfirewingz.customsounds.networking.packet.SoundNodeSyncC2SPacket;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import org.slf4j.LoggerFactory;

public class CustomSounds implements ModInitializer {
    public static final String MOD_ID = "custom_sounds";
    public static Block SOUND_NODE;
    public static Item SOUND_NODE_BLOCK;

    public static final Identifier NODE_SYNC_ID = genID("node_sync");
    public static ScreenHandlerType<NodeScreenHandle> SOUND_NODE_SCREEN_HANDLER_TYPE;
    public static BlockEntityType<SoundNode.SoundNodeEntity> SOUND_NODE_ENTITY_BLOCK_ENTITY_TYPE;

    @Override
    public void onInitialize() {
        SOUND_NODE = Registry.register(Registries.BLOCK, CustomSounds.genID("sound_node"), new SoundNode(AbstractBlock.Settings.of(Material.BARRIER).noCollision().dropsNothing().nonOpaque().noBlockBreakParticles().strength(-1.0F, 3600000.8F)));
        SOUND_NODE_BLOCK = Registry.register(Registries.ITEM, CustomSounds.genID("sound_node"),new BlockItem(CustomSounds.SOUND_NODE,new Item.Settings()));
        SOUND_NODE_ENTITY_BLOCK_ENTITY_TYPE = create("sound_node",BlockEntityType.Builder.create(SoundNode.SoundNodeEntity::new, SOUND_NODE));
        ServerPlayNetworking.registerGlobalReceiver(NODE_SYNC_ID, SoundNodeSyncC2SPacket::receive);
        SOUND_NODE_SCREEN_HANDLER_TYPE = register("sound_node",NodeScreenHandle::new);
    }

    public static Identifier genID(String name)
    {
        return new Identifier(MOD_ID,name);
    }

    private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, builder.build(type));
    }

    private static <T extends ScreenHandler> ScreenHandlerType register(String id, ExtendedScreenHandlerType.ExtendedFactory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, id, new ExtendedScreenHandlerType<T>(factory));
    }
}
