package org.abstruck.mc.cybermc.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.client.gui.widget.button.ImplantTypeChooseButton;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ImplantTypeChooseGui extends Screen {
    private BlockPos tileEntityBlockPos;
    private final ResourceLocation IMPLANT_TYPE_CHOOSE_GUI = new ResourceLocation(Utils.MOD_ID,"textures/gui/implant_type_choose_gui.png");
    private static final int textureWidth = 175;
    private static final int textureHeight = 161;

    private final int WINDOW_WIDTH = Minecraft.getInstance().getWindow().getWidth();
    private final int WINDOW_HEIGHT = Minecraft.getInstance().getWindow().getHeight();

    private int guiXPos;
    private int guiYPos;

    private ImplantTypeChooseButton brainImplantsButton;
    private ImplantTypeChooseButton heartImplantsButton;
    private ImplantTypeChooseButton visionSystemImplantsButton;
    private ImplantTypeChooseButton backboneImplantsButton;
    private ImplantTypeChooseButton boneImplantsButton;
    private ImplantTypeChooseButton epidermalSystemButton;
    private ImplantTypeChooseButton handImplantsButton;
    private ImplantTypeChooseButton armImplantsButton;
    private ImplantTypeChooseButton legImplantsButton;

    public ImplantTypeChooseGui(BlockPos tileEntityBlockPos) {
        super(new TranslationTextComponent(Utils.MOD_ID,"gui.implant_type_choose_gui_title"));
        this.tileEntityBlockPos = tileEntityBlockPos;
    }

    public void show(){
        Minecraft.getInstance().setScreen(this);
    }

    public BlockPos getTileEntityBlockPos() {
        return tileEntityBlockPos;
    }

    @Override
    protected void init() {
        this.width = textureWidth;
        this.height = textureHeight;

        guiXPos = (WINDOW_HEIGHT - this.height)/2;
        guiYPos = (WINDOW_WIDTH - this.width)/2;

        brainImplantsButton = new ImplantTypeChooseButton(this, ImplantType.BRAIN,128,8,18,18,new TranslationTextComponent(""),new ImplantTypeChooseButton.Press());
        heartImplantsButton = new ImplantTypeChooseButton(this, ImplantType.HEART,12,38,18,18,new TranslationTextComponent(""),new ImplantTypeChooseButton.Press());
        visionSystemImplantsButton = new ImplantTypeChooseButton(this, ImplantType.VISION_SYSTEM,32,8,18,18,new TranslationTextComponent(""),new ImplantTypeChooseButton.Press());
        backboneImplantsButton = new ImplantTypeChooseButton(this, ImplantType.BACKBONE,32,48,18,18,new TranslationTextComponent(""),new ImplantTypeChooseButton.Press());
        boneImplantsButton = new ImplantTypeChooseButton(this, ImplantType.BONE,32,28,18,18,new TranslationTextComponent(""),new ImplantTypeChooseButton.Press());
        epidermalSystemButton = new ImplantTypeChooseButton(this, ImplantType.EPIDERMAL_SYSTEM,12,18,18,18,new TranslationTextComponent(""),new ImplantTypeChooseButton.Press());
        handImplantsButton = new ImplantTypeChooseButton(this, ImplantType.HAND,128,48,18,18,new TranslationTextComponent(""),new ImplantTypeChooseButton.Press());
        armImplantsButton = new ImplantTypeChooseButton(this, ImplantType.ARM,128,28,18,18,new TranslationTextComponent(""),new ImplantTypeChooseButton.Press());
        legImplantsButton = new ImplantTypeChooseButton(this, ImplantType.LEG,148,18,18,18,new TranslationTextComponent(""),new ImplantTypeChooseButton.Press());

        this.addButton(backboneImplantsButton);
        this.addButton(heartImplantsButton);
        this.addButton(visionSystemImplantsButton);
        this.addButton(backboneImplantsButton);
        this.addButton(boneImplantsButton);
        this.addButton(epidermalSystemButton);
        this.addButton(handImplantsButton);
        this.addButton(armImplantsButton);
        this.addButton(legImplantsButton);

        super.init();
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bind(IMPLANT_TYPE_CHOOSE_GUI);
        this.blit(matrixStack,guiXPos,guiYPos,0,0,width,height,textureWidth,textureHeight);

        this.legImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.armImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.handImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.boneImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.visionSystemImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.backboneImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.heartImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.brainImplantsButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.epidermalSystemButton.render(matrixStack, mouseX, mouseY, partialTicks);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

}
