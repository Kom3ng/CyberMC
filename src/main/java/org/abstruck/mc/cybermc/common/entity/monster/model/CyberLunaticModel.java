package org.abstruck.mc.cybermc.common.entity.monster.model;

import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.abstruck.mc.cybermc.common.entity.monster.CyberLunatic;

@OnlyIn(Dist.CLIENT)
public class CyberLunaticModel extends ZombieModel<CyberLunatic> {
    public CyberLunaticModel() {
        super(0.5f, false);
    }
}
