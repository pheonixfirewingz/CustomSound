package io.github.phoenixfirewingz.customsounds.util;

public class ArrayPropertySaver implements PropertySaver {
    private final String[] data;

    public ArrayPropertySaver(int size) {
        this.data = new String[size];
    }

    @Override
    public String get(int index) {
        return this.data[index];
    }

    @Override
    public void set(int index, String value) {
        this.data[index] = value;
    }

    @Override
    public int size() {
        return this.data.length;
    }
}
