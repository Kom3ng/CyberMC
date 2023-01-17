package org.abstruck.mc.cybermc.common.handler;

import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.event.ProficiencyUpEvent;
import org.abstruck.mc.cybermc.common.item.implant.ImplantProficiencyLevel;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class ProficiencyHandler {
    @SubscribeEvent
    public static void onProficiency(@NotNull ProficiencyUpEvent event){
//        int proficiency = event.getImplant().getProficiency();
//        for (ImplantProficiencyLevel level : ImplantProficiencyLevel.values()){
//            if (proficiency == level.getValue()){
//                event.getPlayer().displayClientMessage(new TranslationTextComponent(level.getName(),false),true);
//                break;
//            }
//        }
        event.getItemStack().getItemStack().getCapability(ModCapability.IMPLANT_CAP).ifPresent(cap -> {
        });
    }
}
