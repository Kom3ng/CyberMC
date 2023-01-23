package org.abstruck.mc.cybermc.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import org.abstruck.mc.cybermc.common.container.OperatingTableContainer;
import org.jetbrains.annotations.NotNull;

public class ImplantGui extends ContainerScreen<OperatingTableContainer> {
    public ImplantGui(OperatingTableContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(@NotNull MatrixStack matrixStack, float pPartialTicks, int pX, int pY) {

    }
}
