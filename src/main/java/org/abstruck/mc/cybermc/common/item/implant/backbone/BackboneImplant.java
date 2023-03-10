package org.abstruck.mc.cybermc.common.item.implant.backbone;

import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

public abstract class BackboneImplant extends Implant {
    @Override
    public @NotNull ImplantType getType() {
        return ImplantType.BACKBONE;
    }
}
