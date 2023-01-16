package org.abstruck.mc.cybermc.common.entity.monster;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.abstruck.mc.cybermc.common.entity.monster.goal.CyberLunaticGoal;
import org.jetbrains.annotations.NotNull;

public class CyberLunatic extends MonsterEntity {
    public CyberLunatic(EntityType<? extends CyberLunatic> type, World level) {
        super(type, level);
        this.getAttributes().getInstance(Attributes.MAX_HEALTH);
    }

    public static AttributeModifierMap.@NotNull MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH,30)
                .add(Attributes.ARMOR,10d)
                .add(Attributes.ATTACK_DAMAGE,5d)
                .add(Attributes.ATTACK_SPEED,1d)
                .add(Attributes.MOVEMENT_SPEED, 05d);
    }
}
