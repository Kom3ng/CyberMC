package org.abstruck.mc.cybermc.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.abstruck.mc.cybermc.client.event.KeyBoardInputHandler;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class KeyBindInit {
    public static void register(@NotNull FMLClientSetupEvent event){
        event.enqueueWork(() -> ClientRegistry.registerKeyBinding(KeyBoardInputHandler.CAPS_LOCK));
    }
}
