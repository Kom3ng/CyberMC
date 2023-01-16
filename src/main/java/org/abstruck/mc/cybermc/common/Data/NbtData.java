package org.abstruck.mc.cybermc.common.Data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

public class NbtData<T extends INBTSerializable<CompoundNBT>> implements INBTSerializable<CompoundNBT>{
    private @NotNull String key;
    private @NotNull T value;

    public NbtData(@NotNull String key,@NotNull T value){
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

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put(getKey(),getValue().serializeNBT());
        return nbt;
    }

    @Override
    public void deserializeNBT(@NotNull CompoundNBT nbt) {
        getValue().deserializeNBT(nbt.getCompound(getKey()));
    }
}
