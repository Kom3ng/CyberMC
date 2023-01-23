package org.abstruck.mc.cybermc.client.gui.widget.button;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.abstruck.mc.cybermc.client.gui.ImplantTypeChooseGui;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.abstruck.mc.cybermc.network.ImplantContainerCallPack;
import org.abstruck.mc.cybermc.network.NetWorking;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ImplantTypeChooseButton extends CyberButton {
    private ImplantType type;
    private ImplantTypeChooseGui gui;
    public ImplantTypeChooseButton(ImplantTypeChooseGui screenIn, ImplantType type, int pX, int pY, int pWidth, int pHeight, ITextComponent pMessage, IPressable pOnPress) {
        super(pX, pY, pWidth, pHeight, pMessage, pOnPress);
        this.type = type;
        this.gui = screenIn;
    }

    public ImplantType getType() {
        return type;
    }

    public void setType(ImplantType type) {
        this.type = type;
    }

    public ImplantTypeChooseGui getGui(){
        return gui;
    }

    public static class Press implements CyberButton.IPressable{

        @Override
        public void onPress(@NotNull CyberButton button) {
            if (button instanceof ImplantTypeChooseButton){
                ImplantTypeChooseButton implantTypeChooseButton = (ImplantTypeChooseButton) button;

                NetWorking.INSTANCE.sendToServer(new ImplantContainerCallPack(((ImplantTypeChooseButton) button).getGui().getMinecraft().player,((ImplantTypeChooseButton) button).getType() ,((ImplantTypeChooseButton) button).getGui().getTileEntityBlockPos()));
            }
        }
    }
}
