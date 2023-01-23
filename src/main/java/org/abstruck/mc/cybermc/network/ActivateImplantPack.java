package org.abstruck.mc.cybermc.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.abstruck.mc.cybermc.common.Data.serializables.ImplantItemStack;
import org.abstruck.mc.cybermc.common.event.ActivateImplantEvent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

public class ActivateImplantPack extends BasePack {
    PlayerEntity player;
    ImplantItemStack implant;
    public ActivateImplantPack(PlayerEntity player, ImplantItemStack implant){
        this.player = player;
        this.implant = implant;
    }

    @Contract("_ -> new")
    public static @Nullable ActivateImplantPack decode(@NotNull PacketBuffer packetBuffer){
        ImplantItemStack implantItemStack = new ImplantItemStack();
        if (packetBuffer.readNbt() == null){
            return null;
        }
        implantItemStack.deserializeNBT(Objects.requireNonNull(packetBuffer.readNbt()));
        return new ActivateImplantPack(ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(packetBuffer.readUUID()), implantItemStack);
    }

    public void encode(@NotNull PacketBuffer packetBuffer){
        packetBuffer.writeUUID(player.getUUID());
        packetBuffer.writeNbt(implant.serializeNBT());
    }

    public void handle(@NotNull Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            MinecraftForge.EVENT_BUS.post(new ActivateImplantEvent(player,implant));
        });
        ctx.get().setPacketHandled(true);
    }
}
