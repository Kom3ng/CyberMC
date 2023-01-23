package org.abstruck.mc.cybermc.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.Data.serializables.ImplantItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ActiveImplantGui extends AbstractGui {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private MatrixStack matrixStack;
    private final ResourceLocation HUD = new ResourceLocation(Utils.MOD_ID,"textures/gui/active_implant_hud.png");
    public List<ImplantItemStack> activeImplantList;
    public int currentIndexImplant;

    public ActiveImplantGui(MatrixStack matrixStack){
        this.width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.minecraft = Minecraft.getInstance();
        this.matrixStack = matrixStack;
    }

    public void setMatrixStack(MatrixStack matrixStack){
        this.matrixStack = matrixStack;
    }

    public void render(@NotNull List<ImplantItemStack> activeImplantList, int currentIndexImplant){
        this.activeImplantList = activeImplantList;
        this.currentIndexImplant = currentIndexImplant;
        this.minecraft.getTextureManager().bind(HUD);
        blit(matrixStack,width/2-9,height/2+9,0,0,18,18,18,18);
        // Render item using matrixStack
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        itemRenderer.renderGuiItem(activeImplantList.get(currentIndexImplant).getItemStack(),width/2,height/2);
    }
}
