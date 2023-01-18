package org.abstruck.mc.cybermc.common.utils;

import net.minecraft.nbt.CompoundNBT;
import org.abstruck.mc.cybermc.common.Data.serializables.ImplantItemStack;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ImplantUtil {
    public static @NotNull List<ImplantItemStack> readAllImplants(Map<ImplantType,List<ImplantItemStack>> map){
        List<ImplantItemStack> result = new ArrayList<>();

        if (map == null){
            return result;
        }

        for (List<ImplantItemStack> value : map.values()){
            //检查列表是否为空
            if (value == null || value.isEmpty()){
                continue;
            }
            result.addAll(value.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        }

        return result;
    }

    @Deprecated
    public static @NotNull List<ImplantItemStack> readListImplantItemStack(@NotNull CompoundNBT nbt, String key){
        List<ImplantItemStack> result = new ArrayList<>();
        int index = 0;
        while(nbt.contains(key+index)){
            ImplantItemStack implantItemStack = new ImplantItemStack();
            implantItemStack.deserializeNBT(nbt.getCompound(key+index));
            result.add(implantItemStack);
        }
        return result;
    }


    @Deprecated
    public static @NotNull CompoundNBT writeListImplantItemStack(@NotNull List<ImplantItemStack> implantItemStacks, String key){
        CompoundNBT nbt = new CompoundNBT();
        for (int index = 0;index< implantItemStacks.size();index++){
           nbt.put(key+index,implantItemStacks.get(index).serializeNBT());
        }
        return nbt;
    }

    public static int getTotalWeight(List<ImplantItemStack> implants){
        int result = 0;
        if (implants == null){
            return result;
        }
        if (implants.isEmpty()){
            return result + 1;
        }
        for (ImplantItemStack implant : implants){
            if (implant.getImplant() == null){
                continue;
            }
            result += implant.getImplant().getWeight();
        }
        return result;
    }
}
