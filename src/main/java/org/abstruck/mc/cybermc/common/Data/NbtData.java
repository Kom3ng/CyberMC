package org.abstruck.mc.cybermc.common.Data;

import org.jetbrains.annotations.NotNull;

public class NbtData<T> {
    private @NotNull String key;
    private @NotNull T value;

    public NbtData(@NotNull String key, @NotNull T value){
        this.key = key;
        this.value = value;
    }

    public @NotNull T getValue() {
        return value;
    }

    public @NotNull String getKey() {
        return key;
    }

    public void setKey(@NotNull String key) {
        this.key = key;
    }

    public void setValue(@NotNull T value) {
        this.value = value;
    }
}
