package org.abstruck.mc.cybermc.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public abstract class BasePack {
    abstract void encode(PacketBuffer buffer);

    abstract void handle(Supplier<NetworkEvent.Context> ctx);
}
