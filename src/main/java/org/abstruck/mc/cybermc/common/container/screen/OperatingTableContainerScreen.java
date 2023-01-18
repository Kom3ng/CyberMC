package org.abstruck.mc.cybermc.common.container.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.container.OperatingTableContainer;
import org.abstruck.mc.cybermc.common.container.screen.button.ImplantButton;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

public class OperatingTableContainerScreen extends ContainerScreen<OperatingTableContainer> {
    private final ResourceLocation OPERATING_TABLE_RECOURSE = new ResourceLocation(Utils.MOD_ID,"textures/gui/operating_table_container.png");
    private static final int textureWidth = 175;
    private static final int textureHeight = 161;

    int i = (this.width - this.imageWidth) / 2;
    int j = (this.height - this.imageHeight) / 2;

    private ImplantButton brainImplantsButton;
    private ImplantButton heartImplantsButton;
    private ImplantButton visionSystemImplantsButton;
    private ImplantButton backboneImplantsButton;
    private ImplantButton boneImplantsButton;
    private ImplantButton epidermalSystemButton;
    private ImplantButton handImplantsButton;
    private ImplantButton armImplantsButton;
    private ImplantButton legImplantsButton;

    public OperatingTableContainerScreen(OperatingTableContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
    }

    @Override
    protected void init() {
        brainImplantsButton = new ImplantButton(this,ImplantType.BRAIN,i+128,j+8,18,18,new TranslationTextComponent(""),new ImplantButton.Press());
        heartImplantsButton = new ImplantButton(this,ImplantType.HEART,i+12,j+38,18,18,new TranslationTextComponent(""),new ImplantButton.Press());
        visionSystemImplantsButton = new ImplantButton(this,ImplantType.VISION_SYSTEM,i+32,j+8,18,18,new TranslationTextComponent(""),new ImplantButton.Press());
        backboneImplantsButton = new ImplantButton(this,ImplantType.BACKBONE,i+32,j+48,18,18,new TranslationTextComponent(""),new ImplantButton.Press());
        boneImplantsButton = new ImplantButton(this,ImplantType.BONE,i+32,28,j+18,18,new TranslationTextComponent(""),new ImplantButton.Press());
        epidermalSystemButton = new ImplantButton(this,ImplantType.EPIDERMAL_SYSTEM,i+12,j+18,18,18,new TranslationTextComponent(""),new ImplantButton.Press());
        handImplantsButton = new ImplantButton(this,ImplantType.HAND,i+128,j+48,18,18,new TranslationTextComponent(""),new ImplantButton.Press());
        armImplantsButton = new ImplantButton(this,ImplantType.ARM,i+128,j+28,18,18,new TranslationTextComponent(""),new ImplantButton.Press());
        legImplantsButton = new ImplantButton(this,ImplantType.LEG,i+148,j+18,18,18,new TranslationTextComponent(""),new ImplantButton.Press());

        super.init();
    }

    @Override
    protected void renderBg(@NotNull MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.renderBackground(matrixStack);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bind(OPERATING_TABLE_RECOURSE);
        blit(matrixStack, i, j, 0, 0, imageWidth, imageHeight, textureWidth, textureHeight);
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderTooltip(matrixStack,mouseX,mouseY);

        this.legImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.armImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.handImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.boneImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.visionSystemImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.backboneImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.heartImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.brainImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.epidermalSystemButton.render(matrixStack, mouseX, mouseY, partialTicks);

    }
}
