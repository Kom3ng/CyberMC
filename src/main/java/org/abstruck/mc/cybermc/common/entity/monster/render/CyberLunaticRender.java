package org.abstruck.mc.cybermc.common.entity.monster.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.ZoglinRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.monster.ZoglinEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.entity.monster.CyberLunatic;
import org.abstruck.mc.cybermc.common.entity.monster.model.CyberLunaticModel;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class CyberLunaticRender extends ZombieRenderer {
    public CyberLunaticRender(EntityRendererManager p_i232474_1_) {
        super(p_i232474_1_);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ZombieEntity pEntity) {
        return new ResourceLocation(Utils.MOD_ID,"textures/entity/cyber_lunatic.png");

    }
}
