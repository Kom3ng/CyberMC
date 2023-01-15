package org.abstruck.mc.cybermc.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.entity.monster.CyberLunatic;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, Utils.MOD_ID);

    public static final RegistryObject<EntityType<CyberLunatic>> CYBER_LUNATIC = REGISTER.register("cyber_lunatic",() -> EntityType.Builder.of(CyberLunatic::new, EntityClassification.MONSTER).sized(0.5f,2f).build("cyber_lunatic"));
}
