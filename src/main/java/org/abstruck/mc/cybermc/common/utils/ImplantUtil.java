package org.abstruck.mc.cybermc.common.utils;

import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ImplantUtil {
    public static @NotNull List<Implant> readAllImplants(Map<ImplantType,List<Implant>> map){
        List<Implant> result = new ArrayList<>();

        if (map == null){
            return result;
        }

        for (List<Implant> value : map.values()){
            //检查列表是否为空
            if (value == null || value.isEmpty()){
                continue;
            }
            result.addAll(value.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        }

        return result;
    }

    public static int getTotalWeight(List<Implant> implants){
        int result = 0;
        if (implants == null){
            return result;
        }
        if (implants.isEmpty()){
            return result + 1;
        }
        for (Implant implant : implants){
            result += implant.getWeight();
        }
        return result;
    }
}
