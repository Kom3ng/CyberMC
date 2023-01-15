package org.abstruck.mc.cybermc.common.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.capability.CyberMcCapabilityProvider;
import org.abstruck.mc.cybermc.common.capability.ICyberMcCapability;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.container.OperatingTableContainer;
import org.abstruck.mc.cybermc.common.event.ImplantChangeEvent;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber
public class CapabilityHandler {
    @SubscribeEvent
    public static void attachCapability(@NotNull AttachCapabilitiesEvent<Entity> event){

        if (!(event.getObject() instanceof PlayerEntity)){
            return;
        }
        event.addCapability(new ResourceLocation(Utils.MOD_ID, "mod_cap"), new CyberMcCapabilityProvider());
    }

    @SubscribeEvent
    public static void playerCloned(PlayerEvent.@NotNull Clone event){
        PlayerEntity player = event.getPlayer();
        World level = player.getCommandSenderWorld();

        if (level.isClientSide){
            return;
        }

        MinecraftServer server = level.getServer();

        assert server != null;
        boolean keepInventory = server.getGameRules().getBoolean(new GameRules.RuleKey<>("keepInventory", GameRules.Category.PLAYER));

        if (!keepInventory && event.isWasDeath()){
            return;
        }

        LazyOptional<ICyberMcCapability> oldCyberCap = event.getOriginal().getCapability(ModCapability.CAP);
        LazyOptional<ICyberMcCapability> newCyberCap = player.getCapability(ModCapability.CAP);

        if (oldCyberCap.isPresent() && newCyberCap.isPresent()){
            newCyberCap.ifPresent(newCap -> {oldCyberCap.ifPresent(oldCap -> newCap.deserializeNBT(oldCap.serializeNBT()));});
        }
    }

    @SubscribeEvent
    public static void onCloseOperatingTable(PlayerContainerEvent.@NotNull Close event){
        if (!(event.getContainer() instanceof OperatingTableContainer)){
            return;
        }

        OperatingTableContainer operatingTableContainer = (OperatingTableContainer) event.getContainer();

        Map<ImplantType, List<Implant>> oldImplantTypeMap = new HashMap<>();
        event.getPlayer().getCapability(ModCapability.CAP).ifPresent(cap -> oldImplantTypeMap.putAll(cap.getTypeImplantMap()));

        MinecraftForge.EVENT_BUS.post(new ImplantChangeEvent(event.getPlayer(), operatingTableContainer.getTypeImplantMap(),oldImplantTypeMap));
    }
}
