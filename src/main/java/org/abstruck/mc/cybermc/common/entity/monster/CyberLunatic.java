package org.abstruck.mc.cybermc.common.entity.monster;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.abstruck.mc.cybermc.common.entity.monster.goal.CyberLunaticGoal;
import org.abstruck.mc.cybermc.init.EntityInit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CyberLunatic extends MonsterEntity {
//    static {
//        GlobalEntityTypeAttributes.getSupplier(EntityInit.CYBER_LUNATIC.get()).createInstance(modifiableAttributeInstance -> {
//            modifiableAttributeInstance.setBaseValue(30D);
//        }, Attributes.MAX_HEALTH);
//    }

    private static AttributeModifierManager attributeModifierManager = new AttributeModifierManager(createAttributes().build());

    public CyberLunatic(EntityType<? extends CyberLunatic> type, World level) {
        super(type, level);
    }

    public static AttributeModifierMap.@NotNull MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.3F)
                .add(Attributes.ATTACK_DAMAGE, 7.0D)
                .add(Attributes.FOLLOW_RANGE, 64.0D);
    }

    @Override
    public @NotNull AttributeModifierManager getAttributes() {
        return attributeModifierManager;
    }
}
