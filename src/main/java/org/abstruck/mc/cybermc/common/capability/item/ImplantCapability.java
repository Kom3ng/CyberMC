package org.abstruck.mc.cybermc.common.capability.item;

import net.minecraft.nbt.CompoundNBT;
import org.abstruck.mc.cybermc.common.Data.NbtData;
import org.abstruck.mc.cybermc.common.Data.SerializableNbtData;
import org.abstruck.mc.cybermc.common.Data.serializables.NbtInteger;
import org.abstruck.mc.cybermc.common.item.implant.ImplantProficiencyLevel;
import org.jetbrains.annotations.NotNull;

public class ImplantCapability implements IImplantCapability{
    SerializableNbtData<NbtInteger> proficiency = new SerializableNbtData<>("proficiency",new NbtInteger());
    NbtData<ImplantProficiencyLevel> level = new NbtData<>("proficiency_level",ImplantProficiencyLevel.RUSTY);

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT result = new CompoundNBT();
        result.put(proficiency.getKey(),proficiency.serializeNBT());
        return result;
    }

    @Override
    public void deserializeNBT(@NotNull CompoundNBT nbt) {
        proficiency.deserializeNBT(nbt.getCompound(proficiency.getKey()));

        level.setValue(ImplantProficiencyLevel.fromProficiency(proficiency.getValue().getValue()));
    }
}
