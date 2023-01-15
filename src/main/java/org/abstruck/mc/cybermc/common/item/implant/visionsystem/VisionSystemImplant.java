package org.abstruck.mc.cybermc.common.item.implant.visionsystem;

import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;

public abstract class VisionSystemImplant extends Implant {

    @Override
    public ImplantType getType() {
        return ImplantType.VISION_SYSTEM;
    }
}
