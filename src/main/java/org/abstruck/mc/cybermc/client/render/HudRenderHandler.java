package org.abstruck.mc.cybermc.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.client.gui.ActiveImplantGui;
import org.abstruck.mc.cybermc.client.profile.PlayerProfile;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

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
        if (!PlayerProfile.getInstance().getHudState()){
            return;
        }
        if (PlayerProfile.getInstance().getActiveImplants().isEmpty()){
            return;
        }
        GUI.render(PlayerProfile.getInstance().getActiveImplants(), PlayerProfile.getInstance().getCurrentImplantIndex());

    }

}
