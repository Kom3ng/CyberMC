package org.abstruck.mc.cybermc.common.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import org.abstruck.mc.cybermc.common.capability.item.IImplantCapability;
import org.abstruck.mc.cybermc.common.capability.player.ICyberPlayerDataCapability;

public class ModCapability {
    @CapabilityInject(ICyberPlayerDataCapability.class)
    public static Capability<ICyberPlayerDataCapability> CYBER_PLAYER_DATA_CAP;
    @CapabilityInject(IImplantCapability.class)
    public static Capability<IImplantCapability> IMPLANT_CAP;
}
