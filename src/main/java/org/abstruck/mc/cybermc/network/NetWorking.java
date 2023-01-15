package org.abstruck.mc.cybermc.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.abstruck.mc.cybermc.Utils;

public class NetWorking {
    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1.0";
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MOD_ID, "networking"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        INSTANCE.messageBuilder(ActivateImplantPack.class, nextID())
                .encoder(ActivateImplantPack::encode)
                .decoder(ActivateImplantPack::decode)
                .consumer(ActivateImplantPack::handler)
                .add();
        INSTANCE.messageBuilder(ClientUpdateActiveImplantListPack.class,nextID())
                .encoder(ClientUpdateActiveImplantListPack::encode)
                .decoder(ClientUpdateActiveImplantListPack::decode)
                .consumer(ClientUpdateActiveImplantListPack::handle)
                .add();
    }
}
