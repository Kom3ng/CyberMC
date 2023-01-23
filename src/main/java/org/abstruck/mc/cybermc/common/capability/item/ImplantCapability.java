package org.abstruck.mc.cybermc.common.capability.item;

import net.minecraft.nbt.CompoundNBT;
import org.abstruck.mc.cybermc.common.Data.NbtData;
import org.abstruck.mc.cybermc.common.Data.SerializableNbtData;
import org.abstruck.mc.cybermc.common.Data.serializables.NbtInteger;
import org.abstruck.mc.cybermc.common.item.implant.ImplantProficiencyLevel;
import org.jetbrains.annotations.NotNull;

public class ImplantCapability implements IImplantCapability{
    NbtData<Integer> proficiency = new NbtData<>("proficiency",0);

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT result = new CompoundNBT();
        result.putInt(proficiency.getKey(),proficiency.getValue());
        return result;
    }

    @Override
    public void deserializeNBT(@NotNull CompoundNBT nbt) {
        proficiency.setValue(nbt.getInt(proficiency.getKey()));
    }

    @Override
    public void addProficiency(int i) {
        proficiency.setValue(proficiency.getValue() + i);
    }

    @Override
    public void addProficiency() {
        addProficiency(1);
    }

    @Override
    public int getProficiency() {
        return proficiency.getValue();
    }

    @Override
    public ImplantProficiencyLevel getProficiencyLevel() {
        return ImplantProficiencyLevel.fromProficiency(getProficiency());
    }
}
