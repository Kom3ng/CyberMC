package org.abstruck.mc.cybermc.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

public class ImplantSlot extends Slot {
    ImplantType type;
    public ImplantSlot(IInventory inventory, int index, int x, int y,ImplantType type) {
        super(inventory, index, x, y);
        this.type = type;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack itemStack) {
        Item item = itemStack.getItem();
        if (!(item instanceof Implant)){
            return false;
        }
        return ((Implant) item).getType().equals(type);
    }
}
