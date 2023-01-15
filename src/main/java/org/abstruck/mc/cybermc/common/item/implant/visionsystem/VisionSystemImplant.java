package org.abstruck.mc.cybermc.common.item.implant.visionsystem;

import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

public abstract class VisionSystemImplant extends Implant {

    @Override
    public @NotNull ImplantType getType() {
        return ImplantType.VISION_SYSTEM;
    }
}
