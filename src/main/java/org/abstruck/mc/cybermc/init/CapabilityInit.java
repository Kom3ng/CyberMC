package org.abstruck.mc.cybermc.init;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.abstruck.mc.cybermc.common.capability.player.CyberPlayerDataCapability;
import org.abstruck.mc.cybermc.common.capability.player.ICyberPlayerDataCapability;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CapabilityInit {
    public static void register(@NotNull FMLCommonSetupEvent event){
        event.enqueueWork(() ->{
            CapabilityManager.INSTANCE.register(ICyberPlayerDataCapability.class, new Capability.IStorage<ICyberPlayerDataCapability>() {
                @Nullable
                @Override
                public INBT writeNBT(Capability<ICyberPlayerDataCapability> capability, ICyberPlayerDataCapability instance, Direction side) {
                    if (instance == null){
                        return null;
                    }

                    return instance.serializeNBT();
                }

                @Override
                public void readNBT(Capability<ICyberPlayerDataCapability> capability, ICyberPlayerDataCapability instance, Direction side, INBT nbt) {
                    //check null
                    if (instance == null || !(nbt instanceof CompoundNBT)){
                        return;
                    }
                    instance.deserializeNBT((CompoundNBT) nbt);
                }
            }, CyberPlayerDataCapability::new);
        });
    }
}
