package org.abstruck.mc.cybermc.client.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.abstruck.mc.cybermc.common.entity.monster.render.CyberLunaticRender;
import org.abstruck.mc.cybermc.init.EntityInit;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientSetupHandler {
    @SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.CYBER_LUNATIC.get(), CyberLunaticRender::new);

    }
}
