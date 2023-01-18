package org.abstruck.mc.cybermc.common.event;

import net.minecraftforge.eventbus.api.Event;
import org.abstruck.mc.cybermc.common.Data.serializables.ImplantItemStack;


public class ProficiencyUpEvent extends Event {
    ImplantItemStack itemStack;
    public ProficiencyUpEvent(ImplantItemStack itemStack) {
        super();
        this.itemStack = itemStack;
    }

    public ImplantItemStack getImplantItemStack() {
        return itemStack;
    }
}
