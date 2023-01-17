package org.abstruck.mc.cybermc.common.handler;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.common.event.ActivateImplantEvent;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Mod.EventBusSubscriber
public class ActivateImplantHandler {
    @SubscribeEvent
    public static void onActivateImplant(@NotNull ActivateImplantEvent event){
        Implant implant = event.getImplant().getImplant();
        if (implant == null){
            return;
        }
        if (event.getPlayer().getCooldowns().isOnCooldown(implant) || !(implant instanceof IActive)){
            return;
        }
        event.getPlayer().getCooldowns().addCooldown(Objects.requireNonNull(event.getImplant().getImplant()), ((IActive) implant).getCoolDownTime());
        ((IActive) implant).onActivate(event);
    }
}
