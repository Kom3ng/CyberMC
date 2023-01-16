package org.abstruck.mc.cybermc.common.handler;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.capability.data.CyberLevel;

@Mod.EventBusSubscriber
public class TickHandler {
    @SubscribeEvent
    public static void sanRecover(TickEvent event){
        if (ServerLifecycleHooks.getCurrentServer() == null || ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers().isEmpty()){
            return;
        }
        ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers().forEach(player -> {
            player.getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> {
                if (cap.getSan() == cap.getMaxSan()){
                    return;
                }
                if (cap.getSan() > cap.getMaxSan()){
                    cap.setSan(cap.getMaxSan());
                    return;
                }

                //debug
                //LogManager.getLogger().info("san值恢复");

                cap.consumeSan( -1 );

                CyberLevel level = CyberLevel.getLevel(cap.getSan());

                if (level.equals(CyberLevel.NONE)){
                    return;
                }
                if (level.equals(CyberLevel.MILD)){
                    player.addEffect(new EffectInstance(Effects.CONFUSION,5,1));
                    return;
                }
                if (level.equals(CyberLevel.MODERATE)){
                    player.addEffect(new EffectInstance(Effects.BLINDNESS,5,2));
                    return;
                }
                if (level.equals(CyberLevel.SEVERE)){
                    player.addEffect(new EffectInstance(Effects.WITHER,5,3));
                }
                if (level.equals(CyberLevel.DEATH)){
                    player.kill();
                }
            });
        });
    }
}
