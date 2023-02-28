package io.github.phoenixfirewingz.customsounds.mixin;

import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(net.minecraft.screen.ScreenHandlerType.class)
public class ScreenHandlerType<T extends ScreenHandler> {

    public ScreenHandlerType()
    {
    }
    @Shadow
    private static <T extends ScreenHandler> net.minecraft.screen.ScreenHandlerType<T> register(String id, net.minecraft.screen.ScreenHandlerType.Factory<T> factory) {
        throw new AssertionError();
    }

    public <T extends ScreenHandler> net.minecraft.screen.ScreenHandlerType<T> patch_register(String id, net.minecraft.screen.ScreenHandlerType.Factory<T> factory) {
        return  register(id,factory);
    }
}
