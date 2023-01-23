package org.abstruck.mc.cybermc.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

public class ScreenInit {
    public static void register(@NotNull FMLClientSetupEvent event){
        event.enqueueWork(() -> {
//            ScreenManager.register(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(), ImplantTypeChooseScreen::new);
        });
    }
}
