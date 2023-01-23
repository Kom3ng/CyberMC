package org.abstruck.mc.cybermc.common.capability.item;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import org.abstruck.mc.cybermc.common.item.implant.ImplantProficiencyLevel;

public interface IImplantCapability extends INBTSerializable<CompoundNBT> {
    void addProficiency(int i);
    void addProficiency();
    int getProficiency();
    ImplantProficiencyLevel getProficiencyLevel();
}
