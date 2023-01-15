package org.abstruck.mc.cybermc.common.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.abstruck.mc.cybermc.common.utils.ImplantUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ImplantChangeEvent extends PlayerEvent {
    Map<ImplantType, List<Implant>> oldImplantTypeMap;
    Map<ImplantType, List<Implant>> implantTypeListMap;
    public ImplantChangeEvent(PlayerEntity player, Map<ImplantType, List<Implant>> implantTypeListMap, Map<ImplantType, List<Implant>> oldImplantTypeMap) {
        super(player);
        this.implantTypeListMap = implantTypeListMap;
        this.oldImplantTypeMap = oldImplantTypeMap;


    }

    public Map<ImplantType, List<Implant>> getImplantTypeListMap() {
        return implantTypeListMap;
    }

    public List<Implant> getAllImplants() {
        return ImplantUtil.readAllImplants(implantTypeListMap);
    }

    public List<Implant> getAllOldImplants(){
        return ImplantUtil.readAllImplants(oldImplantTypeMap);
    }
}
