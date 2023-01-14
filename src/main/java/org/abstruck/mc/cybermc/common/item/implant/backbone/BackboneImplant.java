package org.abstruck.mc.cybermc.common.item.implant.backbone;

import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;

public abstract class BackboneImplant extends Implant {
    @Override
    public ImplantType getType() {
        return ImplantType.BACKBONE;
    }
}
