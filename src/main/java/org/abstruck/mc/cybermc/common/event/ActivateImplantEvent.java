package org.abstruck.mc.cybermc.common.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import org.abstruck.mc.cybermc.common.item.implant.Implant;

@Cancelable
public class ActivateImplantEvent extends PlayerEvent {
    Implant implant;
    public ActivateImplantEvent(PlayerEntity player, Implant implant) {
        super(player);
        this.implant = implant;
    }

    public Implant getImplant() {
        return implant;
    }
}
