package org.abstruck.mc.cybermc.common.Data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

public class SerializableNbtData<T extends INBTSerializable<CompoundNBT>> extends NbtData<T> implements INBTSerializable<CompoundNBT>{
    public SerializableNbtData(@NotNull String key, @NotNull T value){
        super(key,value);
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
