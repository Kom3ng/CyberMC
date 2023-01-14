package org.abstruck.mc.cybermc.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;

import java.util.List;
import java.util.Map;

public interface ICyberMcCapability extends INBTSerializable<CompoundNBT> {
    public List<Implant> getAllImplants();
    public List<Implant> getImplants(ImplantType type);

    public void setTypeImplantMap(Map<ImplantType,List<Implant>> map);

    public void addImplant(Implant implant);

    public void switchHudState();
    public boolean getHudState();
    public int getCurrentImplantIndex();
    public List<Implant> getActiveImplants();

    public void nextActiveImplant();
}
