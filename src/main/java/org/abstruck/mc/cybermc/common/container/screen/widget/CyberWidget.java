package org.abstruck.mc.cybermc.common.container.screen.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import org.abstruck.mc.cybermc.Utils;
import org.jetbrains.annotations.NotNull;

public abstract class CyberWidget extends Widget {
    public static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation(Utils.MOD_ID,"textures/gui/widgets.png");
    public CyberWidget(int pX, int pY, int pWidth, int pHeight, ITextComponent pMessage) {
        super(pX, pY, pWidth, pHeight, pMessage);
    }

    @Override
    public void renderButton(@NotNull MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        FontRenderer fontrenderer = minecraft.font;
        minecraft.getTextureManager().bind(WIDGETS_LOCATION);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        int i = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        this.blit(pMatrixStack, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
        this.blit(pMatrixStack, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
        this.renderBg(pMatrixStack, minecraft, pMouseX, pMouseY);
        int j = getFGColor();
        drawCenteredString(pMatrixStack, fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
    }
}
