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
import org.abstruck.mc.cybermc.client.profile.PlayerProfile;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.network.ActivateImplantPack;
import org.abstruck.mc.cybermc.network.NetWorking;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyBoardInputHandler {
    public static final KeyBinding CAPS_LOCK = new KeyBinding("key.showImplantHud",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_CAPS_LOCK,
            "key.category." + Utils.MOD_ID);

    @SubscribeEvent
    public static void onSwitchActiveImplantHud(InputEvent.KeyInputEvent event){
        System.out.println(1);
        if (!CAPS_LOCK.consumeClick() || Minecraft.getInstance().player == null){
            return;
        }
        PlayerProfile.getInstance().updateActiveImplants();
        System.out.println(2);
        if (PlayerProfile.getInstance().getActiveImplants().isEmpty()){
            return;
        }
        System.out.println(3);
        if (PlayerProfile.getInstance().getHudState()){
            onCloseHud(PlayerProfile.getInstance().getActiveImplants(), PlayerProfile.getInstance().getCurrentImplantIndex());
        }
        System.out.println(4);
        PlayerProfile.getInstance().switchHudState();
    }

    private static void onCloseHud(List<Implant> activeImplants,int currentImplantIndex){
        NetWorking.INSTANCE.sendToServer(new ActivateImplantPack(Minecraft.getInstance().player,activeImplants.get(currentImplantIndex)));
        System.out.println("packet sent");
    }

    @SubscribeEvent
    public static void onSwitchActiveImplant(InputEvent.@NotNull MouseInputEvent event){
        if (event.getButton() == GLFW.GLFW_MOUSE_BUTTON_RIGHT){
            return;
        }

        if (PlayerProfile.getInstance().getHudState()){
            PlayerProfile.getInstance().nextActiveImplant();
        }
    }

//    @SubscribeEvent
//    public static void onCloseHud(InputEvent.@NotNull KeyInputEvent event){
//        if (event.getKey() != GLFW.GLFW_KEY_ESCAPE || hudState){
//            hudState = false;
//        }
//        AtomicBoolean hudState = new AtomicBoolean(false);
//        Minecraft.getInstance().player.getCapability(ModClientCapability.CAP).ifPresent(cap -> hudState.set(cap.getHudState()));
//
//    }
}
