package org.abstruck.mc.cybermc.common.item.implant;

import org.abstruck.mc.cybermc.common.event.ActivateImplantEvent;

public interface IActive {
    int getCoolDownTime();
    void onActivate(ActivateImplantEvent event);
    void consumeSan();
}
