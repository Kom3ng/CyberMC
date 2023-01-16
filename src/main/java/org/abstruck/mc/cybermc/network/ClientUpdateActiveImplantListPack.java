package org.abstruck.mc.cybermc.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.abstruck.mc.cybermc.client.profile.PlayerProfile;
import org.abstruck.mc.cybermc.common.Data.ImplantItemStack;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.utils.ImplantUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ClientUpdateActiveImplantListPack {
    private static final String KEY = "key";
    PlayerEntity player;
    List<ImplantItemStack> activeImplants = new ArrayList<>();
    boolean isCreateSideRemote;
    public ClientUpdateActiveImplantListPack(PlayerEntity player){
        this.player = player;
        this.isCreateSideRemote = false;
    }

    public ClientUpdateActiveImplantListPack(List<ImplantItemStack> activeImplants){
        this.activeImplants = activeImplants;
        this.isCreateSideRemote = true;
    }

    @Contract("_ -> new")
    public static @NotNull ClientUpdateActiveImplantListPack decode(@NotNull PacketBuffer packetBuffer){
        if (packetBuffer.readBoolean()){
            return new ClientUpdateActiveImplantListPack(ImplantUtil.readListImplantItemStack(Objects.requireNonNull(packetBuffer.readNbt()),KEY));
        }
        return new ClientUpdateActiveImplantListPack(ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(packetBuffer.readUUID()));
    }

    public void encode(@NotNull PacketBuffer packetBuffer){
        packetBuffer.writeBoolean(isCreateSideRemote);
        if (isCreateSideRemote){
            packetBuffer.writeUtf(translateImplantListToString(activeImplants));
        }else {
            packetBuffer.writeUUID(player.getUUID());
        }
    }

    public void handle(@NotNull Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(() -> {
            if (this.isCreateSideRemote){
                PlayerProfile.getInstance().setActiveImplants(this.activeImplants);
            }else {
                ServerPlayerEntity serverPlayer = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(this.player.getUUID());
                if (serverPlayer == null ){
                    return;
                }
                List<ImplantItemStack> implants = new ArrayList<>();
                serverPlayer.getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> implants.addAll(cap.getAllImplants().stream().filter(implant -> implant instanceof IActive).collect(Collectors.toList())));
                NetWorking.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer) , new ClientUpdateActiveImplantListPack(implants));
            }
        });
        ctx.get().setPacketHandled(true);
    }

//    private static @NotNull String translateImplantListToString(List<Implant> implants){
//        if (implants == null || implants.isEmpty()){
//            return "";
//        }
//        StringBuilder sb = new StringBuilder();
//        implants.forEach(implant -> sb.append(implant.getName()).append(","));
//        if (sb.length()>0){
//            sb.deleteCharAt(sb.length()-1);
//        }
//        return sb.toString();
//    }
//    @Contract(pure = true)
//    private static @NotNull List<Implant> translateStringToImplantList(String string){
//        List<Implant> result = new ArrayList<>();
//        if (string == null || string.equals("")){
//            return result;
//        }
//        Arrays.stream(string.split(",")).forEach(s -> result.add(Implant.factory(s)));
//        return result;
//    }
}
