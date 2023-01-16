package org.abstruck.mc.cybermc.common.Data.serializables;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

public class NbtInteger implements INBTSerializable<CompoundNBT> {
    private static final String KEY = "key";
    private @NotNull Integer value = 0;

    public NbtInteger(){}
    public NbtInteger(int value){
        this.value = value;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt(KEY,value);
        return nbt;
    }

    @Override
    public void deserializeNBT(@NotNull CompoundNBT nbt) {
        this.setValue(nbt.getInt(KEY));
    }

    public void setValue(@NotNull Integer value) {
        this.value = value;
    }

    public @NotNull Integer getValue() {
        return value;
    }
}
