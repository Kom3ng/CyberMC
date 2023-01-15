package org.abstruck.mc.cybermc.common.item.implant.leg;

import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

public abstract class LegImplant extends Implant {
    @Override
    public @NotNull ImplantType getType() {
        return ImplantType.LEG;
    }
}
