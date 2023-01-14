package org.abstruck.mc.cybermc.common.container.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.container.OperatingTableContainer;
import org.jetbrains.annotations.NotNull;

public class OperatingTableContainerScreen extends ContainerScreen<OperatingTableContainer> {
    private final ResourceLocation OPERATING_TABLE_RECOURSE = new ResourceLocation(Utils.MOD_ID,"textures/gui/operating_table_container.png");
    private static final int textureWidth = 176;
    private static final int textureHeight = 166;
    public OperatingTableContainerScreen(OperatingTableContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        this.imageWidth = textureWidth;
        this.imageHeight = textureHeight;
    }

    @Override
    protected void renderBg(@NotNull MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.renderBackground(matrixStack);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bind(OPERATING_TABLE_RECOURSE);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        blit(matrixStack, i, j, 0, 0, imageWidth, imageHeight, textureWidth, textureHeight);
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderTooltip(matrixStack,mouseX,mouseY);
    }
}
