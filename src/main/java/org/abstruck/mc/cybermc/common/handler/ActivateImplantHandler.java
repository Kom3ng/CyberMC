package org.abstruck.mc.cybermc.common.handler;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.common.event.ActivateImplantEvent;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class ActivateImplantHandler {
    @SubscribeEvent
    public static void onActivateImplant(@NotNull ActivateImplantEvent event){
        Implant implant = event.getImplant();
        if (event.getPlayer().getCooldowns().isOnCooldown(implant) || !(implant instanceof IActive)){
            return;
        }
        event.getPlayer().getCooldowns().addCooldown(implant, ((IActive) implant).getCoolDownTime());
        event.getPlayer().kill();
    }
}
