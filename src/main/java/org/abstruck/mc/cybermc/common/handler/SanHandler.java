package org.abstruck.mc.cybermc.common.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class SanHandler {
    @SubscribeEvent
    public static void onPlayerHurtCostSan(@NotNull LivingHurtEvent event){
        if (!(event.getEntity() instanceof PlayerEntity)){
            return;
        }
        event.getEntity().getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> {
            cap.consumeSan((int) event.getAmount()*5);
        });
    }
}
