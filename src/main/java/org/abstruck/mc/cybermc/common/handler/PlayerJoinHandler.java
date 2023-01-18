package org.abstruck.mc.cybermc.common.handler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.network.ClientUpdateActiveImplantListPack;
import org.abstruck.mc.cybermc.network.NetWorking;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class PlayerJoinHandler {
    @SubscribeEvent
    public static void onJoinSynchronous(PlayerEvent.@NotNull PlayerLoggedInEvent event){
        event.getPlayer().getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> {
            NetWorking.INSTANCE.send(PacketDistributor.PLAYER.with(()-> (ServerPlayerEntity)event.getPlayer()), new ClientUpdateActiveImplantListPack(cap.getImplantInventory()));
        });
    }
}
