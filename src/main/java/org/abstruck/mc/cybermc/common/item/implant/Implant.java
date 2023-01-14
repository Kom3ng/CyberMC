package org.abstruck.mc.cybermc.common.item.implant;

import net.minecraft.item.Item;
import org.abstruck.mc.cybermc.init.ItemInit;
import org.abstruck.mc.cybermc.common.item.itemgroup.ModGroup;
import org.jetbrains.annotations.Nullable;

public abstract class Implant extends Item implements IBasicInformation{

    public Implant() {
        super(new Properties().tab(ModGroup.IMPLANT_ITEM_GROUP).stacksTo(1));
    }

    public static @Nullable Implant factory(String name){
        for (Implant implant: ItemInit.getImplants()){
            if (!implant.getName().equals(name)){
                continue;
            }
            return implant;
        }
        return null;
    }
}
