package org.abstruck.mc.cybermc.common.item.implant;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import org.abstruck.mc.cybermc.Config;
import org.jetbrains.annotations.NotNull;

public enum ImplantProficiencyLevel {
    RUSTY(Config.RUSTY_PROFICIENCY.get(), "rusty"),NORMAL(Config.NORMAL_PROFICIENCY.get(), "normal"),SKILLED(Config.SKILLED_PROFICIENCY.get(), "skilled"),EXPERT(Config.EXPERT_PROFICIENCY.get(), "expert");


    final int value;
    final String name;
    ImplantProficiencyLevel(int value, String name) {
        this.value = value;
        this.name = name;
    }
    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static ImplantProficiencyLevel fromProficiency(int v){
        if (v> RUSTY.getValue()){
            return RUSTY;
        }
        if (v> NORMAL.getValue()){
            return NORMAL;
        }
        if (v> SKILLED.getValue()){
            return SKILLED;
        }
        if (v> EXPERT.getValue()){
            return EXPERT;
        }
        return RUSTY;
    }
}
