package org.abstruck.mc.cybermc.common.handler;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.capability.item.IImplantCapability;
import org.abstruck.mc.cybermc.common.event.ProficiencyUpEvent;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class ProficiencyHandler {
    @SubscribeEvent
    public static void onProficiency(@NotNull ProficiencyUpEvent event){
        event.getImplantItemStack().getItemStack().getCapability(ModCapability.IMPLANT_CAP).ifPresent(IImplantCapability::addProficiency);
    }
}
