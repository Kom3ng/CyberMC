package org.abstruck.mc.cybermc.client.profile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.network.ClientUpdateActiveImplantListPack;
import org.abstruck.mc.cybermc.network.NetWorking;

import java.util.ArrayList;
import java.util.List;

public class PlayerProfile {
    ClientPlayerEntity player = Minecraft.getInstance().player;
    List<Implant> activeImplants = new ArrayList<>();
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

    public List<Implant> getActiveImplants() {
        return activeImplants;
    }

    public void nextActiveImplant() {
        if (activeImplants.size() == 0){
            return;
        }
        currentActiveImplantIndex = (currentActiveImplantIndex + 1) % activeImplants.size();
    }

    public void updateActiveImplants() {
        currentActiveImplantIndex = 0;
        NetWorking.INSTANCE.sendToServer(new ClientUpdateActiveImplantListPack(Minecraft.getInstance().player));
    }

    public void setActiveImplants(List<Implant> implants) {
        this.activeImplants = implants;
    }
}
