package org.abstruck.mc.cybermc.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.capability.ICyberMcCapability;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.network.ActivateImplantPack;
import org.abstruck.mc.cybermc.network.NetWorking;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyBoardInputHandler {

    public static boolean hudState;

    public static final KeyBinding CAPS_LOCK = new KeyBinding("key.showImplantHud",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_CAPS_LOCK,
            "key.category." + Utils.MOD_ID);

    @SubscribeEvent
    public static void onSwitchActiveImplantHud(InputEvent.KeyInputEvent event){
        if (!CAPS_LOCK.consumeClick() || Minecraft.getInstance().player == null){
            return;
        }

        ServerPlayerEntity player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(Minecraft.getInstance().player.getUUID());

        if (player == null){
            return;
        }

        player.getCapability(ModCapability.CAP).ifPresent(cap -> hudState = cap.getHudState());

        player.getCapability(ModCapability.CAP).ifPresent(cap -> {activeImplants = cap.getAllImplants().stream().filter(implant -> implant instanceof IActive).collect(Collectors.toList());});

        if (activeImplants == null || activeImplants.isEmpty()){
            return;
        }

        if (hudState){
            System.out.println("close hud");
            onCloseHud();
        }else {
            System.out.println("show hud");
        }

        player.getCapability(ModCapability.CAP).ifPresent(ICyberMcCapability::switchHudState);
    }

    private static void onCloseHud(){
        NetWorking.INSTANCE.sendToServer(new ActivateImplantPack(Minecraft.getInstance().player,activeImplants.get(currentImplantIndex)));
        System.out.println("packet sent");
    }

    @SubscribeEvent
    public static void onSwitchActiveImplant(InputEvent.@NotNull MouseInputEvent event){
        if (event.getButton() == GLFW.GLFW_MOUSE_BUTTON_RIGHT&&KeyBoardInputHandler.hudState){

        }
    }

    @SubscribeEvent
    public static void onCloseHud(InputEvent.@NotNull KeyInputEvent event){
        if (event.getKey() == GLFW.GLFW_KEY_ESCAPE || hudState){
            hudState = false;
        }
    }
}
