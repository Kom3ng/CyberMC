package org.abstruck.mc.cybermc.common.capability.item;

import net.minecraft.nbt.CompoundNBT;
import org.abstruck.mc.cybermc.common.Data.NbtData;
import org.abstruck.mc.cybermc.common.item.implant.ImplantProficiencyLevel;
import org.jetbrains.annotations.NotNull;

public class ImplantCapability implements IImplantCapability{
    NbtData<Integer> proficiency = new NbtData<>("proficiency",0);
    NbtData<ImplantProficiencyLevel> level = new NbtData<>("proficiency_level",ImplantProficiencyLevel.RUSTY);

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT result = new CompoundNBT();
        result.putInt(proficiency.getKey(), proficiency.getValue());
        return result;
    }

    @Override
    public void deserializeNBT(@NotNull CompoundNBT nbt) {
        proficiency.setValue(nbt.getInt(proficiency.getKey()));
        level.setValue(ImplantProficiencyLevel.fromProficiency(proficiency.getValue()));
    }
}
