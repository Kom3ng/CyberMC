package org.abstruck.mc.cybermc.common.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class ModCapability {
    @CapabilityInject(ICyberMcCapability.class)
    public static Capability<ICyberMcCapability> CAP;
}
