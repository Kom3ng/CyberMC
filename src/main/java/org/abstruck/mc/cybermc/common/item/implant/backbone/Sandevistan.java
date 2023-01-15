package org.abstruck.mc.cybermc.common.item.implant.backbone;

import net.minecraft.client.Minecraft;
import net.minecraft.util.FrameTimer;
import net.minecraft.util.Timer;
import org.abstruck.mc.cybermc.common.event.ActivateImplantEvent;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.jetbrains.annotations.NotNull;

public class Sandevistan extends BackboneImplant implements IActive {
    final String NAME = "Sandevistan";
    int proficiency;
    @Override
    public @NotNull String getName() {
        return NAME;
    }

    @Override
    public int getCoolDownTime() {
        return 200;
    }

    @Override
    public void onActivate(ActivateImplantEvent event) {

    }

    @Override
    public void consumeSan() {

    }

    @Override
    public int getWeight() {
        return 10000;
    }

    @Override
    public int getProficiency() {
        return 0;
    }

    @Override
    public int getSanForeverConsume() {
        return 0;
    }
}
