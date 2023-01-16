package org.abstruck.mc.cybermc.common.item.implant.visionsystem;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import org.abstruck.mc.cybermc.common.event.ImplantChangeEvent;
import org.abstruck.mc.cybermc.common.item.implant.IPassive;
import org.abstruck.mc.cybermc.common.item.implant.ImplantProficiencyLevel;
import org.jetbrains.annotations.NotNull;

public class EnhancedEye extends VisionSystemImplant implements IPassive {
    ImplantProficiencyLevel level;
    public static final String NAME = "Enhanced Eye";
    @Override
    public @NotNull String getName() {
        return NAME;
    }
    @Override
    public int getWeight() {
        return 1;
    }

//    @Override
//    public int getProficiency() {
//        return 0;
//    }

    @Override
    public int getSanForeverConsume() {
        return 0;
    }

//    @Override
//    public ImplantProficiencyLevel getProficiencyLevel() {
//        return level;
//    }

//    @Override
//    public void setLevel(ImplantProficiencyLevel level) {
//        this.level = level;
//    }

    @Override
    public void onRemove(@NotNull ImplantChangeEvent event) {
        event.getPlayer().removeEffect(Effects.NIGHT_VISION);
    }

    @Override
    public void onInstall(@NotNull ImplantChangeEvent event) {
        event.getPlayer().addEffect(new EffectInstance(Effects.NIGHT_VISION,Integer.MAX_VALUE,2));
    }
}
