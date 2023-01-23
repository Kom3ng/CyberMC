package org.abstruck.mc.cybermc.client.gui.widget.button;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import org.abstruck.mc.cybermc.client.gui.widget.CyberWidget;

public abstract class AbstractCyberButton extends CyberWidget {
    public AbstractCyberButton(int pX, int pY, int pWidth, int pHeight, ITextComponent pMessage) {
        super(pX, pY, pWidth, pHeight, pMessage);
    }

    public abstract void onPress();

    public void onClick(double pMouseX, double pMouseY) {
        this.onPress();
    }

    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (this.active && this.visible) {
            if (pKeyCode != 257 && pKeyCode != 32 && pKeyCode != 335) {
                return false;
            } else {
                this.playDownSound(Minecraft.getInstance().getSoundManager());
                this.onPress();
                return true;
            }
        } else {
            return false;
        }
    }
}
