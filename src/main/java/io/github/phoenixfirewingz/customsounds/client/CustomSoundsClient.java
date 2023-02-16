package io.github.phoenixfirewingz.customsounds.client;

import io.github.phoenixfirewingz.customsounds.CustomSounds;
import io.github.phoenixfirewingz.customsounds.gui.NodeScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class CustomSoundsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(CustomSounds.node_handle, NodeScreen::new);
    }
}
