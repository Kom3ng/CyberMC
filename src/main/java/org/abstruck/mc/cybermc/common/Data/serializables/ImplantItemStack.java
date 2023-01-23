package org.abstruck.mc.cybermc.common.Data.serializables;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import org.abstruck.mc.cybermc.common.Data.SerializableNbtData;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ImplantItemStack implements INBTSerializable<CompoundNBT> {
    private final SerializableNbtData<ItemStack> itemStack = new SerializableNbtData<>("item_stack",new ItemStack(Items.AIR));
    private final String implantKey = "implant_key";

    public ImplantItemStack(Implant implant){
        itemStack.setValue(new ItemStack(implant));
    }

    public ImplantItemStack(ItemStack itemStack){
        this.itemStack.setValue(itemStack);
    }
    public ImplantItemStack(){}

    public ItemStack getItemStack(){
        return itemStack.getValue();
    }

    public @Nullable Implant getImplant(){
        if (!(getItemStack().getItem() instanceof Implant)){
            return null;
        }
        return (Implant) getItemStack().getItem();
    }


    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();

        if (!(itemStack.getValue().getItem() instanceof Implant)){
            return nbt;
        }

        CompoundNBT itemStackCap = new CompoundNBT();

        getItemStack().getCapability(ModCapability.IMPLANT_CAP).ifPresent(cap -> itemStackCap.merge(cap.serializeNBT()));

        nbt.put(itemStack.getKey(), itemStackCap);
        nbt.putString(implantKey, ((Implant) itemStack.getValue().getItem()).getName());

        return nbt;
    }

    @Override
    public void deserializeNBT(@NotNull CompoundNBT nbt) {
        if (!nbt.contains(implantKey) || !nbt.contains(itemStack.getKey())){
            return;
        }
        Implant imp = Implant.factory(nbt.getString(implantKey));
        if (imp == null){
            return;
        }
        ItemStack itemStack1 = new ItemStack(imp,1);
        itemStack1.getCapability(ModCapability.IMPLANT_CAP).ifPresent(cap -> cap.deserializeNBT(nbt.getCompound(itemStack.getKey())));
        this.itemStack.setValue(itemStack1);
    }
}
