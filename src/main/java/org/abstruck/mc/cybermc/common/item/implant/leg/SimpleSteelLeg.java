package org.abstruck.mc.cybermc.common.item.implant.leg;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import org.abstruck.mc.cybermc.common.event.ActivateImplantEvent;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.abstruck.mc.cybermc.common.item.implant.ImplantProficiencyLevel;
import org.jetbrains.annotations.NotNull;

public class SimpleSteelLeg extends LegImplant implements IActive {
    ImplantProficiencyLevel level;
    @Override
    public int getCoolDownTime() {
        return 200;
    }

    @Override
    public void onActivate(@NotNull ActivateImplantEvent event) {
        event.getPlayer().addEffect(new EffectInstance(Effects.JUMP,30,2));
    }

    @Override
    public void consumeSan(PlayerEntity player) {

    }

    @Override
    public @NotNull String getName() {
        return "Simple Steel Leg";
    }

    @Override
    public int getWeight() {
        return 5000;
    }

//    @Override
//    public int getProficiency() {
//        return 0;
//    }

    @Override
    public int getSanForeverConsume() {
        return 10;
    }

//    @Override
//    public ImplantProficiencyLevel getProficiencyLevel() {
//        return level;
//    }

//    @Override
//    public void setLevel(ImplantProficiencyLevel level) {
//        this.level = level;
//    }
}
