package org.abstruck.mc.cybermc;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.IntValue HURT_SAN_COST_TIME;
    public static ForgeConfigSpec.IntValue TICK_SAN_RECOVER;
    public static ForgeConfigSpec.IntValue RUSTY_PROFICIENCY;
    public static ForgeConfigSpec.IntValue NORMAL_PROFICIENCY;
    public static ForgeConfigSpec.IntValue SKILLED_PROFICIENCY;
    public static ForgeConfigSpec.IntValue EXPERT_PROFICIENCY;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.push("san");
        HURT_SAN_COST_TIME = COMMON_BUILDER.defineInRange("hurt_san_cost_time",10,0,Integer.MAX_VALUE);
        TICK_SAN_RECOVER = COMMON_BUILDER.defineInRange("tick_san_recover",1,0,Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("proficiency");
        RUSTY_PROFICIENCY = COMMON_BUILDER.defineInRange("rusty_proficiency",0,0,0);
        NORMAL_PROFICIENCY = COMMON_BUILDER.defineInRange("normal_proficiency",100,0,Integer.MAX_VALUE);
        SKILLED_PROFICIENCY = COMMON_BUILDER.defineInRange("skilled_proficiency",500,0,Integer.MAX_VALUE);
        EXPERT_PROFICIENCY = COMMON_BUILDER.defineInRange("expert_proficiency",3000,0,Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
