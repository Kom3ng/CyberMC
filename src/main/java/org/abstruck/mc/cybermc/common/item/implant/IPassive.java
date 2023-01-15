package org.abstruck.mc.cybermc.common.item.implant;

import org.abstruck.mc.cybermc.common.event.ImplantChangeEvent;

public interface IPassive{
    public void onRemove(ImplantChangeEvent event);
    public void onInstall(ImplantChangeEvent event);
}
