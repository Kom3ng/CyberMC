package org.abstruck.mc.cybermc.common.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;
import org.abstruck.mc.cybermc.common.Data.ImplantItemStack;
import org.abstruck.mc.cybermc.common.item.implant.Implant;


public class ProficiencyUpEvent extends Event {
    ImplantItemStack itemStack;
    public ProficiencyUpEvent(ImplantItemStack itemStack) {
        super();
        this.itemStack = itemStack;
    }

    public ImplantItemStack getItemStack() {
        return itemStack;
    }
}
