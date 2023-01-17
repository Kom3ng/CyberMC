package org.abstruck.mc.cybermc.common.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import org.abstruck.mc.cybermc.common.Data.ImplantItemStack;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.abstruck.mc.cybermc.network.ClientUpdateActiveImplantListPack;
import org.abstruck.mc.cybermc.network.NetWorking;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber
public class PlayerJoinHandler {
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.@NotNull PlayerLoggedInEvent event){
        PlayerEntity player = event.getPlayer();
        List<ImplantItemStack> implantItemStacks = new ArrayList<>();
        player.getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> {
            implantItemStacks.addAll(cap .getAllImplants().stream().filter(implantItemStack -> implantItemStack.getImplant() instanceof IActive).collect(Collectors.toList()));
        });
        NetWorking.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new ClientUpdateActiveImplantListPack(implantItemStacks));
    }
}
