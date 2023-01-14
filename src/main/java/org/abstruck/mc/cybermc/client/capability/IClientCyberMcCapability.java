package org.abstruck.mc.cybermc.client.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.INBTSerializable;

@OnlyIn(Dist.CLIENT)
public interface IClientCyberMcCapability extends INBTSerializable<CompoundNBT> {
}
