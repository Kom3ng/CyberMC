package org.abstruck.mc.cybermc.common.capability.item;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ImplantCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    ImplantCapability capability = new ImplantCapability();
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == ModCapability.IMPLANT_CAP ? LazyOptional.of(this::get).cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return get().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        get().deserializeNBT(nbt);
    }

    private ImplantCapability get(){
        return capability;
    }
}
