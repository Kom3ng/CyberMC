package org.abstruck.mc.cybermc.init;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.abstruck.mc.cybermc.common.container.screen.OperatingTableContainerScreen;
import org.jetbrains.annotations.NotNull;

public class ScreenInit {
    public static void register(@NotNull FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            ScreenManager.register(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(), OperatingTableContainerScreen::new);
        });
    }
}
