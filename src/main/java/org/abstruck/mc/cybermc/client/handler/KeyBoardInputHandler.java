package org.abstruck.mc.cybermc.client.handler;

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
import org.abstruck.mc.cybermc.common.Data.ImplantItemStack;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.network.ActivateImplantPack;
import org.abstruck.mc.cybermc.network.NetWorking;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyBoardInputHandler {
    public static final KeyBinding SHOW_IMPLANTS_HUD = new KeyBinding("key.showImplantsHud",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_CAPS_LOCK,
            "key.category." + Utils.MOD_ID);

    public static final KeyBinding NEXT_IMPLANT = new KeyBinding("key.nextImplant",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputMappings.Type.MOUSE,
            GLFW.GLFW_MOUSE_BUTTON_RIGHT,
            "key.category" + Utils.MOD_ID
            );
    @SubscribeEvent
    public static void onSwitchActiveImplantHud(InputEvent.KeyInputEvent event){

        if (!SHOW_IMPLANTS_HUD.consumeClick() || Minecraft.getInstance().player == null){
            return;
        }

        if (PlayerProfile.getInstance().getActiveImplants().isEmpty()){
            return;
        }

        if (PlayerProfile.getInstance().getHudState()){
            onCloseHud(PlayerProfile.getInstance().getActiveImplants(), PlayerProfile.getInstance().getCurrentImplantIndex());
        }

        PlayerProfile.getInstance().switchHudState();
    }

    private static void onCloseHud(@NotNull List<ImplantItemStack> activeImplants, int currentImplantIndex){
        PlayerProfile.getInstance().setCurrentActiveImplantIndex(0);
        NetWorking.INSTANCE.sendToServer(new ActivateImplantPack(Minecraft.getInstance().player,activeImplants.get(currentImplantIndex)));
    }

    @SubscribeEvent
    public static void onSwitchActiveImplant(InputEvent.@NotNull MouseInputEvent event){
        if (!NEXT_IMPLANT.consumeClick()){
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
