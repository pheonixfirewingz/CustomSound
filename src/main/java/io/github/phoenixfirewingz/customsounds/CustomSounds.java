package io.github.phoenixfirewingz.customsounds;

import com.mojang.datafixers.types.Type;
import io.github.phoenixfirewingz.customsounds.block.SoundNode;
import io.github.phoenixfirewingz.customsounds.client.screen.NodeScreenHandle;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class CustomSounds implements ModInitializer {
    public static final String MOD_ID = "custom_sounds";
    public static final Block SOUND_NODE;
    public static final Item SOUND_NODE_BLOCK;
    public static ScreenHandlerType<NodeScreenHandle> SOUND_NODE_SCREEN_HANDLER_TYPE;
    public static BlockEntityType<SoundNode.SoundNodeEntity> SOUND_NODE_ENTITY_BLOCK_ENTITY_TYPE;

    static {
        SOUND_NODE = Registry.register(Registries.BLOCK, CustomSounds.genID("sound_node"), new SoundNode(AbstractBlock.Settings.of(Material.BARRIER).noCollision().dropsNothing().nonOpaque().noBlockBreakParticles().strength(-1.0F, 3600000.8F)));
        SOUND_NODE_BLOCK = Registry.register(Registries.ITEM, CustomSounds.genID("sound_node"),new BlockItem(CustomSounds.SOUND_NODE,new Item.Settings()));
        SOUND_NODE_ENTITY_BLOCK_ENTITY_TYPE = create("sound_node",BlockEntityType.Builder.create(SoundNode.SoundNodeEntity::new, SOUND_NODE));
        SOUND_NODE_SCREEN_HANDLER_TYPE = register("sound_node",NodeScreenHandle::new);
    }

    @Override
    public void onInitialize() {
    }

    public static Identifier genID(String name)
    {
        return new Identifier(MOD_ID,name);
    }

    private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, builder.build(type));
    }

    private static <T extends ScreenHandler> ScreenHandlerType register(String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, id, new ScreenHandlerType<T>(factory));
    }
}
