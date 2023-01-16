package org.abstruck.mc.cybermc.common.item.implant;

public enum ImplantProficiencyLevel {
    RUSTY(0, "rusty"),NORMAL(100, "normal"),SKILLED(500, "skilled"),EXPERT(3000, "expert");


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
