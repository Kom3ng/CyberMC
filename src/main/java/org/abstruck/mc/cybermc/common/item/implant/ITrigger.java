package org.abstruck.mc.cybermc.common.item.implant;


import net.minecraftforge.eventbus.api.Event;

public interface ITrigger<T extends Event> {
    public void onEvent(T event);
}
