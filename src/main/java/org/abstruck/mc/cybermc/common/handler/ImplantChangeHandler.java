package org.abstruck.mc.cybermc.common.handler;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.event.ImplantChangeEvent;
import org.abstruck.mc.cybermc.common.item.implant.IPassive;
import org.abstruck.mc.cybermc.common.utils.ImplantUtil;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class ImplantChangeHandler {
    @SubscribeEvent
    public static void saveImplant(@NotNull ImplantChangeEvent event){
        event.getPlayer().getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> cap.setTypeImplantMap(event.getImplantTypeListMap()));
    }

    @SubscribeEvent
    public static void speedChanger(@NotNull ImplantChangeEvent event){
        int totalWeight = ImplantUtil.getTotalWeight(event.getAllImplants());
        int oldWeight = ImplantUtil.getTotalWeight(event.getAllOldImplants());

        if (totalWeight <= 10000 || oldWeight == 0){
            return;
        }

        event.getPlayer().setSpeed(event.getPlayer().getSpeed() * (oldWeight/100000F) * (10000F/totalWeight));
    }

    @SubscribeEvent
    public static void passiveImplantChange(@NotNull ImplantChangeEvent event){
        event.getAllOldImplants().stream().filter(implant -> implant instanceof IPassive).forEach(implant -> ((IPassive)implant).onRemove(event));
        event.getAllImplants().stream().filter(implant -> implant instanceof IPassive).forEach(implant -> ((IPassive)implant).onInstall(event));
    }

    @SubscribeEvent
    public static void setMaxSan(@NotNull ImplantChangeEvent event){
        event.getAllOldImplants().forEach(implant -> event.getPlayer().getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> cap.setMaxSan(cap.getMaxSan() + implant.getSanForeverConsume())));
        event.getAllImplants().forEach(implant -> event.getPlayer().getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> cap.setMaxSan(cap.getMaxSan() - implant.getSanForeverConsume())));
        event.getPlayer().getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> {
            if (cap.getSan() > cap.getMaxSan()){
                cap.setSan(cap.getMaxSan());
            }
        });
    }
}
