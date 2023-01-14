package org.abstruck.mc.cybermc.init;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.abstruck.mc.cybermc.common.capability.CyberMcCapability;
import org.abstruck.mc.cybermc.common.capability.ICyberMcCapability;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CapabilityInit {
    public static void register(@NotNull FMLCommonSetupEvent event){
        event.enqueueWork(() ->{
            CapabilityManager.INSTANCE.register(ICyberMcCapability.class, new Capability.IStorage<ICyberMcCapability>() {
                @Nullable
                @Override
                public INBT writeNBT(Capability<ICyberMcCapability> capability, ICyberMcCapability instance, Direction side) {
                    if (instance == null){
                        return null;
                    }

                    return instance.serializeNBT();
                }

                @Override
                public void readNBT(Capability<ICyberMcCapability> capability, ICyberMcCapability instance, Direction side, INBT nbt) {
                    //check null
                    if (instance == null || !(nbt instanceof CompoundNBT)){
                        return;
                    }
                    instance.deserializeNBT((CompoundNBT) nbt);
                }
            }, CyberMcCapability::new);
        });
    }
}
