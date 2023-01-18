package org.abstruck.mc.cybermc.common.inventory;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import org.abstruck.mc.cybermc.common.Data.serializables.ImplantItemStack;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;

import java.util.*;
import java.util.function.Consumer;

public class ImplantInventory extends Inventory implements INBTSerializable<CompoundNBT> {
    public final int SLOT_PER_TYPE = 3;
    public final String ITEM_STACK_KEY = "item_stack_nbt";
    public final String IMPLANT_NAME_KEY = "implant_name";

    public ImplantInventory(){
        //这里的3是只每种type的implant可以放几个
        super(3 * ImplantType.values().length);
    }

    public List<ImplantItemStack> getAllImplantItemStacks(){
        List<ImplantItemStack> result = new ArrayList<>();
        forEach(itemStack -> {
            if (itemStack.getItem() instanceof Implant){
                result.add(new ImplantItemStack(itemStack));
            }
        });
        return result;
    }

    public Map<ImplantType,List<ImplantItemStack>> getTypeImplantItemStack(){
        Map<ImplantType,List<ImplantItemStack>> result = new HashMap<>();
        forEach(itemStack -> {
            if (!(itemStack.getItem() instanceof Implant)){
                return;
            }
            Implant implant = (Implant) itemStack.getItem();
            if (!result.containsKey(implant.getType()) || result.get(implant.getType())==null){
                result.put(implant.getType(), Collections.singletonList(new ImplantItemStack(itemStack)));
                return;
            }
            result.get(implant.getType()).add(new ImplantItemStack(itemStack));
        });
        return result;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();

        for (int index = 0; index< this.getContainerSize();index++){
            if (this.getItem(index).getItem() instanceof Implant){
                nbt.put(String.valueOf(index), new ImplantItemStack(this.getItem(index)).serializeNBT());
            }
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        for (int index = 0; index<this.getContainerSize();index++){
            if (!nbt.getCompound(String.valueOf(index)).isEmpty()){
                ImplantItemStack implantItemStack = new ImplantItemStack();
                implantItemStack.deserializeNBT(nbt.getCompound(String.valueOf(index)));
                this.setItem(index, implantItemStack.getItemStack());
            }
        }
    }

    public void forEach(Consumer<ItemStack> consumer){
        for (int index = 0; index<this.getContainerSize(); index++){
            consumer.accept(this.getItem(index));
        }
    }
}
