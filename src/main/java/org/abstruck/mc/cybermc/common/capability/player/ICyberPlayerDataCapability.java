package org.abstruck.mc.cybermc.common.capability.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import org.abstruck.mc.cybermc.common.Data.ImplantItemStack;
import org.abstruck.mc.cybermc.common.inventory.ImplantInventory;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;

import java.util.List;
import java.util.Map;

public interface ICyberPlayerDataCapability extends INBTSerializable<CompoundNBT> {
    List<ImplantItemStack> getAllImplants();
    List<ImplantItemStack> getImplants(ImplantType type);

//    void setTypeImplantMap(Map<ImplantType,List<ImplantItemStack>> map);

//    void addImplant(ImplantItemStack implant);

    Map<ImplantType,List<ImplantItemStack>> getTypeImplantMap();

    int getSan();
    void consumeSan(int san);
    void setMaxSan(int maxSan);
    int getMaxSan();
    void setSan(int san);

    ImplantInventory getImplantInventory();
    void setImplantInventory(ImplantInventory implantInventory);
}
