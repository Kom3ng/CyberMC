package org.abstruck.mc.cybermc;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.abstruck.mc.cybermc.init.*;
import org.abstruck.mc.cybermc.network.NetWorking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Utils.MOD_ID)
public class CyberMC {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public CyberMC() {
        init(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void init(IEventBus bus){
        ItemInit.REGISTER.register(bus);
        BlockInit.REGISTER.register(bus);
        ContainerTypeInit.REGISTER.register(bus);
        TileEntityInit.REGISTER.register(bus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        CapabilityInit.register(event);
        event.enqueueWork(NetWorking::registerMessage);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ScreenInit.register(event);
        KeyBindInit.register(event);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

}
