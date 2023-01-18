package org.abstruck.mc.cybermc.common.handler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.capability.player.ICyberPlayerDataCapability;
import org.abstruck.mc.cybermc.common.event.ImplantChangeEvent;
import org.abstruck.mc.cybermc.common.item.implant.IPassive;
import org.abstruck.mc.cybermc.common.utils.ImplantUtil;
import org.abstruck.mc.cybermc.network.ClientUpdateActiveImplantListPack;
import org.abstruck.mc.cybermc.network.NetWorking;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class ImplantChangeHandler {
    @SubscribeEvent
    public static void saveImplant(@NotNull ImplantChangeEvent event){
        event.getPlayer().getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(ICyberPlayerDataCapability::updateOldMap);
    }

    @SubscribeEvent
    public static void refreshClientActiveImplant(@NotNull ImplantChangeEvent event){
        event.getPlayer().getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> {
            NetWorking.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),new ClientUpdateActiveImplantListPack(cap.getImplantInventory()));
        });
    }

    @SubscribeEvent
    public static void speedChanger(@NotNull ImplantChangeEvent event){
        int totalWeight = ImplantUtil.getTotalWeight(event.getAllImplantItemStacks());
        int oldWeight = ImplantUtil.getTotalWeight(event.getAllOldImplantItemStacks());

        if (totalWeight <= 10000 || oldWeight == 0){
            return;
        }

        event.getPlayer().setSpeed(event.getPlayer().getSpeed() * (oldWeight/100000F) * (10000F/totalWeight));
    }

    @SubscribeEvent
    public static void passiveImplantChange(@NotNull ImplantChangeEvent event){
        event.getAllOldImplantItemStacks().stream().filter(implantItemStack -> implantItemStack instanceof IPassive).forEach(implant ->{
            if (implant.getImplant() == null){
                return;
            }
            ((IPassive) implant.getImplant()).onRemove(event);
        });
        event.getAllImplantItemStacks().stream().filter(implantItemStack -> implantItemStack.getImplant() instanceof IPassive).forEach(implant -> {
            if (implant.getImplant() == null){
                return;
            }
            ((IPassive)implant.getImplant()).onInstall(event);
        });

    }

    @SubscribeEvent
    public static void setMaxSan(@NotNull ImplantChangeEvent event){
        event.getAllOldImplantItemStacks().forEach(implant -> event.getPlayer().getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> {
            if (implant.getImplant() != null){
                cap.setMaxSan(cap.getMaxSan() + implant.getImplant().getSanForeverConsume());
            }
        }));
        event.getAllImplantItemStacks().forEach(implant -> event.getPlayer().getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> {
            if (implant.getImplant() != null) {
                cap.setMaxSan(cap.getMaxSan() - implant.getImplant().getSanForeverConsume());
            }
        }));
        event.getPlayer().getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> {
            if (cap.getSan() > cap.getMaxSan()){
                cap.setSan(cap.getMaxSan());
            }
        });
    }
}
