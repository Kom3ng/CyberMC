package org.abstruck.mc.cybermc.common.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import org.abstruck.mc.cybermc.common.Data.ImplantItemStack;
import org.abstruck.mc.cybermc.common.item.implant.Implant;

@Cancelable
public class ActivateImplantEvent extends PlayerEvent {
    ImplantItemStack implant;
    public ActivateImplantEvent(PlayerEntity player, ImplantItemStack implant) {
        super(player);
        this.implant = implant;
    }

    public ImplantItemStack getImplant() {
        return implant;
    }
}
