package io.github.phoenixfirewingz.customsounds.client;

import io.github.phoenixfirewingz.customsounds.CustomSounds;
import io.github.phoenixfirewingz.customsounds.client.screen.NodeScreenHandle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class CustomSoundsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(CustomSounds.SOUND_NODE_SCREEN_HANDLER_TYPE, NodeScreenHandle.NodeScreen::new);
    }
}
