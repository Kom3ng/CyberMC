package org.abstruck.mc.cybermc.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.abstruck.mc.cybermc.common.event.ActivateImplantEvent;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ActivateImplantPack {
    PlayerEntity player;
    Implant implant;
    public ActivateImplantPack(PlayerEntity player, Implant implant){
        this.player = player;
        this.implant = implant;
    }

    @Contract("_ -> new")
    public static @NotNull ActivateImplantPack decode(@NotNull PacketBuffer packetBuffer){
        return new ActivateImplantPack(ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(packetBuffer.readUUID()), Implant.factory(packetBuffer.readUtf()));
    }

    public void encode(@NotNull PacketBuffer packetBuffer){
        packetBuffer.writeUUID(player.getUUID());
        packetBuffer.writeUtf(implant.getName());
    }

    public void handler(@NotNull Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            MinecraftForge.EVENT_BUS.post(new ActivateImplantEvent(player,implant));
            System.out.println("packet received");
        });
        ctx.get().setPacketHandled(true);
    }
}
