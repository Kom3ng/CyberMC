package org.abstruck.mc.cybermc.common.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.Logging;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.capability.CyberMcCapability;
import org.abstruck.mc.cybermc.common.capability.CyberMcCapabilityProvider;
import org.abstruck.mc.cybermc.common.capability.ICyberMcCapability;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.container.OperatingTableContainer;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

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

        if (!keepInventory || event.isWasDeath()){
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

        if (operatingTableContainer.getTypeImplantMap().isEmpty()){
            return;
        }

        event.getPlayer().getCapability(ModCapability.CAP).ifPresent(cap -> cap.setTypeImplantMap(operatingTableContainer.getTypeImplantMap()));
    }
}
