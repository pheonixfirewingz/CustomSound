package io.github.phoenixfirewingz.customsounds;


import io.github.phoenixfirewingz.customsounds.block.Blocks;
import io.github.phoenixfirewingz.customsounds.block.entity.SoundNodeEntity;
import io.github.phoenixfirewingz.customsounds.gui.NodeScreenHandle;
import io.github.phoenixfirewingz.customsounds.item.Items;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class CustomSounds implements ModInitializer {
    public static final String MOD_ID = "custom_sounds";
    public static ScreenHandlerType<NodeScreenHandle> SOUND_NODE_SCREEN_HANDLER_TYPE;
    public static BlockEntityType<SoundNodeEntity>  SOUND_NODE_BLOCK_ENTITY;

    private Blocks blocks = new Blocks();
    private Items items = new Items();

    @Override
    public void onInitialize() {
        SOUND_NODE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, genID("sound_node_controller"),  FabricBlockEntityTypeBuilder.create(SoundNodeEntity::new, Blocks.SOUND_NODE).build(null));
        SOUND_NODE_SCREEN_HANDLER_TYPE =  Registry.register(Registries.SCREEN_HANDLER, genID("sound_node"), new ScreenHandlerType<>(NodeScreenHandle::new));
    }

    public static Identifier genID(String name)
    {
        return new Identifier(MOD_ID,name);
    }
}
