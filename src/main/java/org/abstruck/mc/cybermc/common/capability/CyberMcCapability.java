package org.abstruck.mc.cybermc.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class CyberMcCapability implements ICyberMcCapability{
    Map<ImplantType,List<Implant>> typeImplantMap = new HashMap<>();
    List<Implant> activeImplants = new ArrayList<>();
    int currentImplantIndex = 0;
    boolean isShowHud = false;

    /**
     * @return 如果没有implant则会返回一个空的列表
     */
    @Override
    public List<Implant> getAllImplants() {
        List<Implant> result = new ArrayList<>();

        for (List<Implant> value : typeImplantMap.values()){
            //检查列表是否为空
            if (value == null || value.isEmpty()){
                continue;
            }
            result.addAll(value.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        }

        return result;
    }

    /**
     * @param type 想要的implant的类型
     * @return 如果没有该类型的则会返回一个空的列表
     */
    @Override
    public List<Implant> getImplants(ImplantType type) {
        if (typeImplantMap.containsKey(type)){
            return typeImplantMap.get(type);
        }
        return new ArrayList<>();
    }

    @Override
    public void setTypeImplantMap(Map<ImplantType, List<Implant>> map) {
        this.typeImplantMap = map;
    }

    @Override
    public void addImplant(@NotNull Implant implant) {
        ImplantType type = implant.getType();
        if (!typeImplantMap.containsKey(type) || typeImplantMap.get(type) == null){
            typeImplantMap.put(type, Collections.singletonList(implant));
            return;
        }

        typeImplantMap.get(type).add(implant);
    }

    @Override
    public void switchHudState(){
        isShowHud = !isShowHud;
    }
    @Override
    public boolean getHudState(){
        return isShowHud;
    }

    @Override
    public int getCurrentImplantIndex() {
        return currentImplantIndex;
    }

    @Override
    public List<Implant> getActiveImplants() {
        return activeImplants;
    }

    @Override
    public void nextActiveImplant() {
        currentImplantIndex = (currentImplantIndex + 1) % activeImplants.size();
    }

    @Override
    public CompoundNBT serializeNBT() {

        CompoundNBT result = new CompoundNBT();

        for (ImplantType type:typeImplantMap.keySet()){
            StringBuilder implantNames = new StringBuilder();

            //null check
            List<Implant> implants = typeImplantMap.get(type);
            if (implants == null || implants.isEmpty()){
                continue;
            }

            //将所有的implant转换为他的名字，加入implantNames里
            for (Implant implant:implants){
                implantNames.append(implant.getName()).append(',');
            }

            //将最后以为多出来的“,”删除
            int length = implantNames.length();
            if (length > 0){
                //这里减一是因为index会比length少一，因为index是从0开始的
                implantNames.deleteCharAt(length - 1);
            }

            result.putString(type.getName(), implantNames.toString());
        }

        return result;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        for (ImplantType type:ImplantType.values()){
            if (!nbt.contains(type.getName())){
                continue;
            }

            //将字符串里的所有implant名转换为implant列表
            List<Implant> implants = new ArrayList<>();
            for (String implantName:nbt.getString(type.getName()).split(",")){
                Implant implant = Implant.factory(implantName);
                if (implant==null){
                    continue;
                }
                implants.add(implant);
            }

            typeImplantMap.put(type,implants);
        }

        this.activeImplants = this.getAllImplants().stream().filter(implant -> implant instanceof IActive).collect(Collectors.toList());
    }
}
