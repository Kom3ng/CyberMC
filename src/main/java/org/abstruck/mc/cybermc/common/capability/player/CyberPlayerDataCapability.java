package org.abstruck.mc.cybermc.common.capability.player;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import org.abstruck.mc.cybermc.common.Data.ImplantItemStack;
import org.abstruck.mc.cybermc.common.Data.NbtData;
import org.abstruck.mc.cybermc.common.Data.serializables.NbtInteger;
import org.abstruck.mc.cybermc.common.inventory.ImplantInventory;
import org.abstruck.mc.cybermc.common.item.implant.IActive;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.abstruck.mc.cybermc.common.utils.ImplantUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class CyberPlayerDataCapability implements ICyberPlayerDataCapability {
//    Map<ImplantType,List<ImplantItemStack>> typeImplantMap = new HashMap<>();
//    List<ItemStack> activeImplants = new ArrayList<>();
    NbtData<ImplantInventory> inventory = new NbtData<>("implant_inventory",new ImplantInventory());
    NbtData<NbtInteger> san = new NbtData<>("san",new NbtInteger());
    NbtData<NbtInteger> maxSan = new NbtData<>("max_san",new NbtInteger(25500));


    /**
     * @return 如果没有implant则会返回一个空的列表
     */
    @Override
    public List<ImplantItemStack> getAllImplants() {
        return getImplantInventory().getAllImplantItemStacks();
    }

    /**
     * @param type 想要的implant的类型
     * @return 如果没有该类型的则会返回一个空的列表
     */
    @Override
    public List<ImplantItemStack> getImplants(ImplantType type) {
        if (getTypeImplantMap().containsKey(type)){
            return getTypeImplantMap().get(type);
        }
        return new ArrayList<>();
    }

//    @Override
//    public void setTypeImplantMap(Map<ImplantType, List<ImplantItemStack>> map) {
//        this.typeImplantMap = map;
//    }

//    @Override
//    public void addImplant(@NotNull ImplantItemStack implant) {
//        if (implant.getImplant() == null){
//            return;
//        }
//        ImplantType type = implant.getImplant().getType();
//        if (!typeImplantMap.containsKey(type) || typeImplantMap.get(type) == null){
//            typeImplantMap.put(type, Collections.singletonList(implant));
//            return;
//        }
//
//        typeImplantMap.get(type).add(implant);
//    }

    @Override
    public Map<ImplantType, List<ImplantItemStack>> getTypeImplantMap() {
        return getImplantInventory().getTypeImplantItemStack();
    }

    @Override
    public int getSan() {
        return san.getValue().getValue();
    }

    @Override
    public void consumeSan(int san) {
        this.san.getValue().setValue(getSan() - san);
    }

    @Override
    public void setMaxSan(int maxSan) {
        this.maxSan.getValue().setValue(maxSan);
    }

    @Override
    public int getMaxSan() {
        return maxSan.getValue().getValue();
    }

    @Override
    public void setSan(int san) {
        this.san.getValue().setValue(san);
    }

    @Override
    public ImplantInventory getImplantInventory() {
        return null;
    }

    @Override
    public void setImplantInventory(ImplantInventory implantInventory) {
        this.inventory.setValue(implantInventory);
    }

    @Override
    public CompoundNBT serializeNBT() {

        CompoundNBT result = new CompoundNBT();

//        for (ImplantType type:typeImplantMap.keySet()){
//            StringBuilder implantNames = new StringBuilder();
//
//            //null check
//            List<Implant> implants = typeImplantMap.get(type);
//            if (implants == null || implants.isEmpty()){
//                continue;
//            }
//
//            //将所有的implant转换为他的名字，加入implantNames里
//            for (Implant implant:implants){
//                implantNames.append(implant.getName()).append(',');
//            }
//
//            //将最后以为多出来的“,”删除
//            int length = implantNames.length();
//            if (length > 0){
//                //这里减一是因为index会比length少一，因为index是从0开始的
//                implantNames.deleteCharAt(length - 1);
//            }
//
//            result.putString(type.getName(), implantNames.toString());
//        }

        result.put(inventory.getKey(),inventory.getValue().serializeNBT());

        result.putInt(san.getKey(), san.getValue().getValue());
        result.putInt(maxSan.getKey(),maxSan.getValue().getValue());

        return result;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
//        for (ImplantType type:ImplantType.values()){
//            if (!nbt.contains(type.getName())){
//                continue;
//            }
//
//            //将字符串里的所有implant名转换为implant列表
//            List<Implant> implants = new ArrayList<>();
//            for (String implantName:nbt.getString(type.getName()).split(",")){
//                Implant implant = Implant.factory(implantName);
//                if (implant==null){
//                    continue;
//                }
//                implants.add(implant);
//            }
//
//            typeImplantMap.put(type,implants);
//        }

        this.inventory.getValue().deserializeNBT(nbt);

        this.san.getValue().setValue(nbt.getInt(san.getKey()));
        this.maxSan.getValue().setValue(nbt.getInt(maxSan.getKey()));
    }
}
