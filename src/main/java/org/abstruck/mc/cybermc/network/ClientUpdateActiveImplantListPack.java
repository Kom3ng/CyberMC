package org.abstruck.mc.cybermc.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.abstruck.mc.cybermc.client.profile.PlayerProfile;
import org.abstruck.mc.cybermc.common.Data.serializables.ImplantItemStack;
import org.abstruck.mc.cybermc.common.inventory.ImplantInventory;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.abstruck.mc.cybermc.common.utils.ImplantUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ClientUpdateActiveImplantListPack extends BasePack{
    private static final String KEY = "key";
    @Deprecated
    PlayerEntity player;
    @Deprecated
    List<ImplantItemStack> activeImplants = new ArrayList<>();

    @Deprecated
    boolean isCreateSideRemote;

    ImplantInventory inventory;

    @Deprecated
    public ClientUpdateActiveImplantListPack(PlayerEntity player){
        this.player = player;
        this.isCreateSideRemote = false;
    }

    public ClientUpdateActiveImplantListPack(ImplantInventory inventory){
        this.inventory = inventory;
    }

    @Contract("_ -> new")
    public static @NotNull ClientUpdateActiveImplantListPack decode(@NotNull PacketBuffer packetBuffer){

        ImplantInventory implantInventory = new ImplantInventory();
        implantInventory.deserializeNBT(packetBuffer.readAnySizeNbt());
        return new ClientUpdateActiveImplantListPack(implantInventory);

    }

    public void encode(@NotNull PacketBuffer packetBuffer){
        packetBuffer.writeNbt(inventory.serializeNBT());
    }

    public void handle(@NotNull Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(() -> {
            PlayerProfile.getInstance().setActiveImplants(inventory.getAllImplantItemStacks().stream().filter(implantItemStack -> implantItemStack.getImplant() instanceof IActive).collect(Collectors.toList()));
        });
        ctx.get().setPacketHandled(true);
    }
}
