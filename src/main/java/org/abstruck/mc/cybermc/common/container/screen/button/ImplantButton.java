package org.abstruck.mc.cybermc.common.container.screen.button;

import javafx.stage.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import org.abstruck.mc.cybermc.common.container.OperatingTableContainer;
import org.abstruck.mc.cybermc.common.container.screen.OperatingTableContainerScreen;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

public class ImplantButton extends Button {
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

    public static class Press implements Button.IPressable{

        @Override
        public void onPress(@NotNull Button button) {
            if (!(button instanceof ImplantButton)){
                return;
            }
            ((ImplantButton) button).getScreenIn().getMenu().switchTo(((ImplantButton)button).getType());
        }
    }
}
