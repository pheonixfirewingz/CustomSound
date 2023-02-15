package io.github.phoenixfirewingz.customsounds.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundManager;

@Environment(EnvType.CLIENT)
public class CustomSoundsClient implements ClientModInitializer {
    private MinecraftClient game;
    @Override
    public void onInitializeClient() {
        game = MinecraftClient.getInstance();
    }
}
