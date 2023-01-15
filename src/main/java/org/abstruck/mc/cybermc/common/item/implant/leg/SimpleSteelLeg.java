package org.abstruck.mc.cybermc.common.item.implant.leg;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import org.abstruck.mc.cybermc.common.event.ActivateImplantEvent;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.jetbrains.annotations.NotNull;

public class SimpleSteelLeg extends LegImplant implements IActive {
    @Override
    public int getCoolDownTime() {
        return 200;
    }

    @Override
    public void onActivate(ActivateImplantEvent event) {
        event.getPlayer().addEffect(new EffectInstance(Effects.JUMP,30,2));
    }

    @Override
    public @NotNull String getName() {
        return "Simple Steel Leg";
    }

    @Override
    public int getWeight() {
        return 5000;
    }
}
