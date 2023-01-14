package org.abstruck.mc.cybermc.init;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.abstruck.mc.cybermc.client.event.KeyBoardInputHandler;

public class KeyBindInit {
    public static void register(FMLClientSetupEvent event){
        event.enqueueWork(() -> ClientRegistry.registerKeyBinding(KeyBoardInputHandler.CAPS_LOCK));
    }
}
