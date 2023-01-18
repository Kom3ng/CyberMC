package org.abstruck.mc.cybermc.common.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.abstruck.mc.cybermc.common.Data.serializables.ImplantItemStack;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.abstruck.mc.cybermc.common.utils.ImplantUtil;

import java.util.List;
import java.util.Map;

public class ImplantChangeEvent extends PlayerEvent {
    Map<ImplantType, List<ImplantItemStack>> oldImplantTypeMap;
    Map<ImplantType, List<ImplantItemStack>> implantTypeListMap;
    public ImplantChangeEvent(PlayerEntity player, Map<ImplantType, List<ImplantItemStack>> implantTypeListMap, Map<ImplantType, List<ImplantItemStack>> oldImplantTypeMap) {
        super(player);
        this.implantTypeListMap = implantTypeListMap;
        this.oldImplantTypeMap = oldImplantTypeMap;
    }

    public Map<ImplantType, List<ImplantItemStack>> getImplantTypeListMap() {
        return implantTypeListMap;
    }

    public List<ImplantItemStack> getAllImplantItemStacks() {
        return ImplantUtil.readAllImplants(implantTypeListMap);
    }

    public List<ImplantItemStack> getAllOldImplantItemStacks(){
        return ImplantUtil.readAllImplants(oldImplantTypeMap);
    }
}
