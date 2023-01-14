package org.abstruck.mc.cybermc.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyBoardInputHandler {
    public static boolean isShowedActiveImplantHud = false;
    public static List<Implant> activeImplants;
    public static int currentImplantIndex = 0;
    public static final KeyBinding CAPS_LOCK = new KeyBinding("key.showImplantHud",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_CAPS_LOCK,
            "key.category." + Utils.MOD_ID);

    @SubscribeEvent
    public static void onSwitchActiveImplantHud(InputEvent.KeyInputEvent event){
        if (!CAPS_LOCK.isDown() || Minecraft.getInstance().player == null){
            return;
        }

        Minecraft.getInstance().player.getCapability(ModCapability.CAP).ifPresent(cap -> {
            System.out.println(cap.getAllImplants());
            activeImplants = cap.getAllImplants().stream().filter(implant -> implant instanceof IActive).collect(Collectors.toList());
        });
        if (activeImplants == null || activeImplants.isEmpty()){
            return;
        }

        isShowedActiveImplantHud = !isShowedActiveImplantHud;
    }

    @SubscribeEvent
    public static void onSwitchActiveImplant(InputEvent.@NotNull MouseInputEvent event){
        if (event.getButton() == GLFW.GLFW_MOUSE_BUTTON_RIGHT&&KeyBoardInputHandler.isShowedActiveImplantHud){
            currentImplantIndex = (currentImplantIndex + 1) % activeImplants.size();
        }
    }
}
