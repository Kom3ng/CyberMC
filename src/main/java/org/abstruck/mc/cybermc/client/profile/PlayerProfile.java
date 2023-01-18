package org.abstruck.mc.cybermc.client.profile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.abstruck.mc.cybermc.common.Data.serializables.ImplantItemStack;
import org.abstruck.mc.cybermc.common.inventory.ImplantInventory;
import org.abstruck.mc.cybermc.common.item.implant.IActive;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@OnlyIn(Dist.CLIENT)
public class PlayerProfile {
    ClientPlayerEntity player = Minecraft.getInstance().player;

    List<ImplantItemStack> activeImplants = new ArrayList<>();
    boolean hudState = false;
    int currentActiveImplantIndex = 0;

    private static final PlayerProfile INSTANCE = new PlayerProfile();

    private PlayerProfile(){}

    public static PlayerProfile getInstance() {
        return INSTANCE;
    }

    public void switchHudState(){
        hudState = !hudState;
    }

    public boolean getHudState(){
        return hudState;
    }

    public int getCurrentImplantIndex() {
        return currentActiveImplantIndex;
    }

    public List<ImplantItemStack> getActiveImplants() {
        return activeImplants;
    }

    public void nextActiveImplant() {
        if (getActiveImplants().size() == 0){
            return;
        }
        currentActiveImplantIndex += 1;
        if (currentActiveImplantIndex == getActiveImplants().size()){
            currentActiveImplantIndex = 0;
        }
    }


    public void setActiveImplants(List<ImplantItemStack> implants) {
        this.activeImplants = implants;
    }

    public void setCurrentActiveImplantIndex(int currentActiveImplantIndex) {
        this.currentActiveImplantIndex = currentActiveImplantIndex;
    }
}
