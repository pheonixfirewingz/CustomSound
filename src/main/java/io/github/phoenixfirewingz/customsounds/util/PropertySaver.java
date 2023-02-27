package io.github.phoenixfirewingz.customsounds.util;

import net.minecraft.screen.PropertyDelegate;
import net.minecraft.state.property.Property;

public interface PropertySaver {
    String get(int index);
    void set(int index, String value);
    int size();
}
