package io.github.phoenixfirewingz.customsounds;

import io.github.phoenixfirewingz.customsounds.server.SoundNode;
import io.github.phoenixfirewingz.customsounds.server.SoundNodeEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CustomSounds implements ModInitializer {

    public static final String MOD_ID = "custom_sounds";
    public static final Material node_material;
    public static final SoundNode node;
    public static final BlockEntityType<SoundNodeEntity> node_entity;

    static
    {
        var id = new Identifier(MOD_ID, "sound_node");
        node_material = new Material(MapColor.CLEAR, false, false, false, false, false, false, PistonBehavior.BLOCK);
        node = new SoundNode(AbstractBlock.Settings.of(node_material).noCollision().dropsNothing().nonOpaque().noBlockBreakParticles().strength(-1.0F, 3600000.8F));
        node_entity = Registry.register(Registries.BLOCK_ENTITY_TYPE,id, FabricBlockEntityTypeBuilder.create(SoundNodeEntity::new, node.instance).build(null));
    }
    @Override
    public void onInitialize() {

    }
}
