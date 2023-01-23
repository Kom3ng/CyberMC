package org.abstruck.mc.cybermc.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.container.OperatingTableContainer;
import org.jetbrains.annotations.NotNull;

public class ImplantGui extends ContainerScreen<OperatingTableContainer> {
    private static final ResourceLocation IMPLANT_GUI = new ResourceLocation(Utils.MOD_ID,"textures/gui/implant_gui.png");

    public final int textureWidth = 256;
    public final int textureHeight = 178;


    public ImplantGui(OperatingTableContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = textureWidth;
        this.imageHeight = textureHeight;
    }

    @Override
    protected void renderBg(@NotNull MatrixStack matrixStack, float pPartialTicks, int pX, int pY) {
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bind(IMPLANT_GUI);

        int i = (this.width - this.getXSize()) / 2;
        int j = (this.height - this.getYSize()) / 2;
        blit(matrixStack, i, j, 0, 0, getXSize(), getYSize(), this.textureWidth, textureHeight);

    }

    @Override
    protected void renderLabels(@NotNull MatrixStack pMatrixStack, int pX, int pY) {
    }

    @Override
    public void render(@NotNull MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        renderBackground(pMatrixStack);
        super.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        renderTooltip(pMatrixStack,pMouseX,pMouseY);
    }
}
