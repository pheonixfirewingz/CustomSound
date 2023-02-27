package io.github.phoenixfirewingz.customsounds.block;

import io.github.phoenixfirewingz.customsounds.CustomSounds;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class Blocks {
    public static final Block SOUND_NODE = Registry.register(Registries.BLOCK, CustomSounds.genID("sound_node"), new SoundNode(AbstractBlock.Settings.of(Material.BARRIER).noCollision().dropsNothing().nonOpaque().noBlockBreakParticles().strength(-1.0F, 3600000.8F)));
}
