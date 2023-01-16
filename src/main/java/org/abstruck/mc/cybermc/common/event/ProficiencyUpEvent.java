package org.abstruck.mc.cybermc.common.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;
import org.abstruck.mc.cybermc.common.item.implant.Implant;


public class ProficiencyUpEvent extends Event {
    ItemStack itemStack;
    public ProficiencyUpEvent(ItemStack itemStack) {
        super();
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
