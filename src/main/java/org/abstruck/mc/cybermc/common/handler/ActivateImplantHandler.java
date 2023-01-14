package org.abstruck.mc.cybermc.common.handler;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.common.event.ActivateImplantEvent;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class ActivateImplantHandler {
    @SubscribeEvent
    public static void onActivateImplant(@NotNull ActivateImplantEvent event){
        System.out.println("get:"+event.getPlayer().getName()+" used "+event.getImplant().getName());
    }
}
