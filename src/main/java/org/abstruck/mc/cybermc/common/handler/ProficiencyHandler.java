package org.abstruck.mc.cybermc.common.handler;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.event.ProficiencyUpEvent;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class ProficiencyHandler {
    @SubscribeEvent
    public static void onProficiency(@NotNull ProficiencyUpEvent event){
//        int proficiency = event.getImplantItemStack().getProficiency();
//        for (ImplantProficiencyLevel level : ImplantProficiencyLevel.values()){
//            if (proficiency == level.getValue()){
//                event.getPlayer().displayClientMessage(new TranslationTextComponent(level.getName(),false),true);
//                break;
//            }
//        }
        event.getImplantItemStack().getItemStack().getCapability(ModCapability.IMPLANT_CAP).ifPresent(cap -> {
        });
    }
}
