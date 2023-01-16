package org.abstruck.mc.cybermc.common.capability.data;

public enum CyberLevel {
    NONE(Integer.MAX_VALUE),MILD(0),MODERATE(-100),SEVERE(-300),DEATH(-500);

    CyberLevel(int value){}

    public static CyberLevel getLevel(int value){
        if (value>0){
            return NONE;
        }
        if (value> -100){
            return MILD;
        }
        if (value> -300){
            return MODERATE;
        }
        if (value> -500){
            return SEVERE;
        }
        return DEATH;
    }
}
