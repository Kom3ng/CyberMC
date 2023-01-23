package org.abstruck.mc.cybermc.client.gui.widget.button;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class CyberButton extends AbstractCyberButton{
    public static final CyberButton.ITooltip NO_TOOLTIP = (p_238488_0_, p_238488_1_, p_238488_2_, p_238488_3_) -> {
    };
    protected final CyberButton.IPressable onPress;
    protected final CyberButton.ITooltip onTooltip;

    public CyberButton(int pX, int pY, int pWidth, int pHeight, ITextComponent pMessage, CyberButton.IPressable pOnPress) {
        this(pX, pY, pWidth, pHeight, pMessage, pOnPress, NO_TOOLTIP);
    }

    public CyberButton(int pX, int pY, int pWidth, int pHeight, ITextComponent pMessage, CyberButton.IPressable pOnPress, CyberButton.ITooltip pOnTooltip) {
        super(pX, pY, pWidth, pHeight, pMessage);
        this.onPress = pOnPress;
        this.onTooltip = pOnTooltip;
    }

    public void onPress() {
        this.onPress.onPress(this);
    }

    public void renderButton(@NotNull MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        super.renderButton(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        if (this.isHovered()) {
            this.renderToolTip(pMatrixStack, pMouseX, pMouseY);
        }

    }

    public void renderToolTip(MatrixStack pPoseStack, int pMouseX, int pMouseY) {
        this.onTooltip.onTooltip(this, pPoseStack, pMouseX, pMouseY);
    }

    @OnlyIn(Dist.CLIENT)
    public interface IPressable {
        void onPress(CyberButton p_onPress_1_);
    }

    @OnlyIn(Dist.CLIENT)
    public interface ITooltip {
        void onTooltip(CyberButton p_onTooltip_1_, MatrixStack p_onTooltip_2_, int p_onTooltip_3_, int p_onTooltip_4_);
    }
}
