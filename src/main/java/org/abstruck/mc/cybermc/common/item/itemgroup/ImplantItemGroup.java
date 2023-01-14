package org.abstruck.mc.cybermc.common.item.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.abstruck.mc.cybermc.init.ItemInit;
import org.jetbrains.annotations.NotNull;

public class ImplantItemGroup extends ItemGroup {
    public ImplantItemGroup() {
        super("implant_group");
    }

    @Override
    @NotNull
    public ItemStack makeIcon() {
        return new ItemStack(ItemInit.SANDVISTAN.get());
    }
}
