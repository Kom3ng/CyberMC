package org.abstruck.mc.cybermc.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.backbone.Sandevistan;
import org.abstruck.mc.cybermc.common.item.implant.leg.SimpleSteelLeg;
import org.abstruck.mc.cybermc.common.item.implant.visionsystem.EnhancedEye;
import org.abstruck.mc.cybermc.common.item.itemgroup.ModGroup;

import java.util.HashSet;
import java.util.Set;

public class ItemInit {
    private static Set<Implant> implants;
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);

    public static final RegistryObject<Item> SANDEVISTAN = REGISTER.register("sandevistan", Sandevistan::new);
    public static final RegistryObject<Item> ENHANCED_EYE = REGISTER.register("enhanced_eye", EnhancedEye::new);
    public static final RegistryObject<Item> SIMPLE_STEEL_LEG = REGISTER.register("simple_steel_leg", SimpleSteelLeg::new);
    //以下是方块
    public static final RegistryObject<Item> OPERATING_TABLE = REGISTER.register("operating_table",() -> new BlockItem(BlockInit.OPERATING_TABLE.get(), new Item.Properties().tab(ModGroup.MACHINE_GROUP)));

    private static void initImplantItem(){
        implants = new HashSet<>();

        implants.add((Implant) SANDEVISTAN.get());
        implants.add((Implant) ENHANCED_EYE.get());
        implants.add((Implant) SIMPLE_STEEL_LEG.get());
    }

    public static Set<Implant> getImplants() {
        if (implants == null){
            initImplantItem();
        }
        return implants;
    }
}
