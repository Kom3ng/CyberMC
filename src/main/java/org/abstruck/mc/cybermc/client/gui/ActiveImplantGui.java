package org.abstruck.mc.cybermc.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ActiveImplantGui extends AbstractGui {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private MatrixStack matrixStack;
    private final ResourceLocation HUD = new ResourceLocation(Utils.MOD_ID,"textures/gui/active_implant_hud.png");
    public List<Implant> activeImplantList;
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

    public void render(@NotNull List<Implant> activeImplantList, int currentIndexImplant){
        this.activeImplantList = activeImplantList;
        this.currentIndexImplant = currentIndexImplant;
        this.minecraft.getTextureManager().bind(HUD);
        blit(matrixStack,width/2-9,height/2+9,0,0,18,18,18,18);
        // Render item using matrixStack
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        itemRenderer.renderGuiItem(new ItemStack(activeImplantList.get(currentIndexImplant)),width/2,height/2);
        LogManager.getLogger().info("rending");
    }
}
