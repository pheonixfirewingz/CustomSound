package io.github.phoenixfirewingz.customsounds.item;

import io.github.phoenixfirewingz.customsounds.CustomSounds;
import io.github.phoenixfirewingz.customsounds.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class Items {
    public static final Item SOUND_NODE_BLOCK = Registry.register(Registries.ITEM, CustomSounds.genID("sound_node"),new BlockItem(Blocks.SOUND_NODE,new Item.Settings()));
}