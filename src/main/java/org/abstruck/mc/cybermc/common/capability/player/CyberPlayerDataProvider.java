package org.abstruck.mc.cybermc.common.capability.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class CyberPlayerDataProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private ICyberPlayerDataCapability capability = new CyberPlayerDataCapability();

    @Nonnull
    public ICyberPlayerDataCapability get(){
        return capability;
    }
    public void setCapability(ICyberPlayerDataCapability capability){
        this.capability = capability;
    }
    /**
     * Retrieves the Optional handler for the capability requested on the specific side.
     * The return value <strong>CAN</strong> be the same for multiple faces.
     * Modders are encouraged to cache this value, using the listener capabilities of the Optional to
     * be notified if the requested capability get lost.
     *
     * @param cap
     * @param side
     * @return The requested an optional holding the requested capability.
     */
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == ModCapability.CYBER_PLAYER_DATA_CAP ? LazyOptional.of(this::get).cast() : LazyOptional.empty();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ModCapability.CYBER_PLAYER_DATA_CAP){
            return LazyOptional.of(CyberPlayerDataCapability::new).cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return get().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        get().deserializeNBT(nbt);
    }
}
