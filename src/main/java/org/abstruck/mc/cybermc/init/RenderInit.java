package org.abstruck.mc.cybermc.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import org.abstruck.mc.cybermc.common.entity.monster.render.CyberLunaticRender;

@OnlyIn(Dist.CLIENT)
public class RenderInit {
    public static void register(){
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.CYBER_LUNATIC.get(), CyberLunaticRender::new);
    }
}
