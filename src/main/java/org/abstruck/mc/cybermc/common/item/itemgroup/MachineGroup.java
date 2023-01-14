package org.abstruck.mc.cybermc.common.item.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.abstruck.mc.cybermc.init.ItemInit;

public class MachineGroup extends ItemGroup {
    public MachineGroup() {
        super("machine_group");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemInit.OPERATING_TABLE.get());
    }
}
