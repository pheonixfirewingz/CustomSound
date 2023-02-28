package io.github.phoenixfirewingz.customsounds;


import com.mojang.datafixers.types.Type;
import io.github.phoenixfirewingz.customsounds.block.Blocks;
import io.github.phoenixfirewingz.customsounds.block.entity.SoundNodeEntity;
import io.github.phoenixfirewingz.customsounds.client.screen.NodeScreenHandle;
import io.github.phoenixfirewingz.customsounds.item.Items;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;


public class CustomSounds implements ModInitializer {
    public static final String MOD_ID = "custom_sounds";
    public static ScreenHandlerType<NodeScreenHandle> SOUND_NODE_SCREEN_HANDLER_TYPE;
    public final static BlockEntityType<SoundNodeEntity> SOUND_NODE_ENTITY_BLOCK_ENTITY_TYPE;
    private Items items = new Items();

    static {
        SOUND_NODE_ENTITY_BLOCK_ENTITY_TYPE = create("sound_node",BlockEntityType.Builder.create(SoundNodeEntity::new, Blocks.SOUND_NODE));
        SOUND_NODE_SCREEN_HANDLER_TYPE = new io.github.phoenixfirewingz.customsounds.mixin.ScreenHandlerType<NodeScreenHandle>().patch_register("sound_node",NodeScreenHandle::new);
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
}
