package org.abstruck.mc.cybermc.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.abstruck.mc.cybermc.client.event.KeyBoardInputHandler;
import org.abstruck.mc.cybermc.client.gui.ActiveImplantGui;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class HudRenderHandler {

    private final static ActiveImplantGui GUI = new ActiveImplantGui(new MatrixStack());

    @SubscribeEvent
    public static void onRenderGameOverlay(@NotNull RenderGameOverlayEvent event){
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if (Minecraft.getInstance().player == null || ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(Minecraft.getInstance().player.getUUID())==null){
            return;
        }
        AtomicBoolean hudState = new AtomicBoolean(false);

        ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(Minecraft.getInstance().player.getUUID()).getCapability(ModCapability.CAP).ifPresent(cap -> hudState.set(cap.getHudState()));
        if (!hudState.get()){
            return;
        }
        LogManager.getLogger().info("start rend");
        GUI.render(KeyBoardInputHandler.activeImplants,KeyBoardInputHandler.currentImplantIndex);

    }

}
