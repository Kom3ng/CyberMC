package org.abstruck.mc.cybermc.common.container.screen.widget.button;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import org.abstruck.mc.cybermc.common.container.screen.OperatingTableContainerScreen;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

public class ImplantButton extends CyberButton {
    private ImplantType type;
    private OperatingTableContainerScreen screenIn;
    public ImplantButton(OperatingTableContainerScreen screenIn, ImplantType type, int pX, int pY, int pWidth, int pHeight, ITextComponent pMessage, IPressable pOnPress) {
        super(pX, pY, pWidth, pHeight, pMessage, pOnPress);
        this.type = type;
        this.screenIn = screenIn;
    }

    public ImplantType getType() {
        return type;
    }

    public void setType(ImplantType type) {
        this.type = type;
    }

    public OperatingTableContainerScreen getScreenIn() {
        return screenIn;
    }

    public static class Press implements CyberButton.IPressable{

        @Override
        public void onPress(@NotNull CyberButton button) {
            if (!(button instanceof ImplantButton)){
                return;
            }
            ((ImplantButton) button).getScreenIn().getMenu().switchTo(((ImplantButton)button).getType());
        }
    }
}
