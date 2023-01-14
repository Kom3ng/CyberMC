package org.abstruck.mc.cybermc.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.client.event.KeyBoardInputHandler;
import org.abstruck.mc.cybermc.client.gui.ActiveImplantGui;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class HudRenderHandler {

    private final static ActiveImplantGui GUI = new ActiveImplantGui(new MatrixStack());

    @SubscribeEvent
    public static void onRenderGameOverlay(@NotNull RenderGameOverlayEvent event){
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if (Minecraft.getInstance().player == null){
            return;
        }
        if (!KeyBoardInputHandler.isShowedActiveImplantHud){
            return;
        }

        GUI.render(KeyBoardInputHandler.activeImplants,KeyBoardInputHandler.currentImplantIndex);
    }

}
