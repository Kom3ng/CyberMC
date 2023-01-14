package org.abstruck.mc.cybermc.common.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;

public class ImplantInventory extends Inventory {
    public final int SLOT_PER_TYPE = 3;

    public ImplantInventory(PlayerEntity player){
        //这里的3是只每种type的implant可以放几个
        super(3 * ImplantType.values().length);
    }
}
