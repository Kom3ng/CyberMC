package org.abstruck.mc.cybermc.common.entity.monster.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.abstruck.mc.cybermc.common.entity.monster.CyberLunatic;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class CyberLunaticModel extends BipedModel<CyberLunatic> {
    public CyberLunaticModel() {
        super(0.5f);
    }
}
