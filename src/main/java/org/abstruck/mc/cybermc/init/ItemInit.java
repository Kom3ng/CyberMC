package org.abstruck.mc.cybermc.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.backbone.Sandevistan;
import org.abstruck.mc.cybermc.common.item.itemgroup.ModGroup;

import java.util.HashSet;
import java.util.Set;

public class ItemInit {
    private static Set<Implant> implants;
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);

    public static final RegistryObject<Item> SANDVISTAN = REGISTER.register("sandvistan", Sandevistan::new);

    public static final RegistryObject<Item> OPERATING_TABLE = REGISTER.register("operating_table",() -> new BlockItem(BlockInit.OPERATING_TABLE.get(), new Item.Properties().tab(ModGroup.MACHINE_GROUP)));

    private static void initImplantItem(){
        implants = new HashSet<>();

        implants.add((Implant) SANDVISTAN.get());
    }

    public static Set<Implant> getImplants() {
        if (implants == null){
            initImplantItem();
        }
        return implants;
    }
}
