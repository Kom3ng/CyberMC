package org.abstruck.mc.cybermc.network;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.abstruck.mc.cybermc.common.Data.serializables.ImplantItemStack;
import org.abstruck.mc.cybermc.common.event.ActivateImplantEvent;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;
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

        CompoundNBT nbt = packetBuffer.readAnySizeNbt();

        if (nbt == null) {
            return null;
        }
        UUID playerUniqueId = nbt.getUUID("player_uuid");
        CompoundNBT implantNbt = nbt.getCompound("implant_nbt");

        ServerPlayerEntity serverPlayerEntity = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(playerUniqueId);

//        if (packetBuffer.readAnySizeNbt() == null){
//            return null;
//        }

        implantItemStack.deserializeNBT(implantNbt);
//        try {
//            implantItemStack.deserializeNBT(JsonToNBT.parseTag(packetBuffer.readUtf()));
//        } catch (CommandSyntaxException e) {
//            throw new RuntimeException(e);
//        }
        return new ActivateImplantPack(serverPlayerEntity, implantItemStack);
    }

    public void encode(@NotNull PacketBuffer packetBuffer){
        CompoundNBT nbt = new CompoundNBT();
        nbt.putUUID("player_uuid",player.getUUID());
        nbt.put("implant_nbt",implant.serializeNBT());
        packetBuffer.writeNbt(nbt);

//        packetBuffer.writeNbt(implant.serializeNBT());
//        packetBuffer.writeUtf(player.getStringUUID());
//        packetBuffer.writeUUID(player.getUUID());
//        packetBuffer.writeUtf(implant.getImplant().getName());
    }

    public void handle(@NotNull Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            MinecraftForge.EVENT_BUS.post(new ActivateImplantEvent(player,implant));
        });
        ctx.get().setPacketHandled(true);
    }
}
