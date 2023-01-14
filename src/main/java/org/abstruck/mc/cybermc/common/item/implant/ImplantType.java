package org.abstruck.mc.cybermc.common.item.implant;

public enum ImplantType {
    BRAIN("brain"), VISION_SYSTEM("vision_system"), EPIDERMAL_SYSTEM("epidermal_system"),
    BACKBONE("backbone"),HEART("heart"),BONE("bone"),
    ARM("arm"),HAND("hand"),LEG("leg");

    private final String NAME;
    ImplantType(String name) {
        this.NAME = name;
    }

    public String getName(){
        return NAME;
    }
}
