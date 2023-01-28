package org.abstruck.mc.cybermc.common.item.implant.backbone;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.event.ActivateImplantEvent;
import org.abstruck.mc.cybermc.common.event.ProficiencyUpEvent;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.jetbrains.annotations.NotNull;

public class Sandevistan extends BackboneImplant implements IActive {
    final String NAME = "Sandevistan";

    //    ImplantProficiencyLevel level;
//    int proficiency;
    @Override
    public @NotNull String getName() {
        return NAME;
    }

    @Override
    public int getCoolDownTime() {
        return 200;
    }

    @Override
    public void onActivate(@NotNull ActivateImplantEvent event) {
        consumeSan(event.getPlayer());
//        proficiency++;
        MinecraftForge.EVENT_BUS.post(new ProficiencyUpEvent(event.getImplantItemStack()));
    }

    @Override
    public void consumeSan(@NotNull PlayerEntity player) {
        player.getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> cap.consumeSan(100));
    }

    @Override
    public int getWeight() {
        return 100000000;
    }

    @Override
    public int getSanForeverConsume() {
        return 254000;
    }
}

