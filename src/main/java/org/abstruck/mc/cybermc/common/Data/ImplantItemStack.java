package org.abstruck.mc.cybermc.common.Data;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ImplantItemStack implements INBTSerializable<CompoundNBT> {
    private final NbtData<ItemStack> itemStack = new NbtData<>("item_stack",new ItemStack(Items.AIR));
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

        nbt.put(itemStack.getKey(), itemStack.getValue().serializeNBT());
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
        this.itemStack.setValue(new ItemStack(imp,1,nbt.getCompound(itemStack.getKey())));
    }
}
