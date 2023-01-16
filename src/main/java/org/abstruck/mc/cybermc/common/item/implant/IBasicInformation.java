package org.abstruck.mc.cybermc.common.item.implant;

import org.jetbrains.annotations.NotNull;

public interface IBasicInformation {
    @NotNull String getName();
    @NotNull ImplantType getType();
    int getWeight();
//    int getProficiency();
    int getSanForeverConsume();
//    ImplantProficiencyLevel getProficiencyLevel();
//    void setLevel(ImplantProficiencyLevel level);
}
