package org.abstruck.mc.cybermc.common.item.implant.backbone;

import org.abstruck.mc.cybermc.common.item.implant.IActive;

public class Sandevistan extends BackboneImplant implements IActive {
    final String NAME = "Sandevistan";
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getCoolDownTime() {
        return 200;
    }

    @Override
    public int getWeight() {
        return 10000;
    }
}
