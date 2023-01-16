package org.abstruck.mc.cybermc.common.entity.monster.goal;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import org.abstruck.mc.cybermc.common.entity.monster.CyberLunatic;

public class CyberLunaticGoal extends Goal {
    private final CyberLunatic cyberLunatic;

    public CyberLunaticGoal(CyberLunatic entity) {
        /*
        ZombieAttackGoal(double speedIn, boolean longMemoryIn)
        speedIn: 攻击速度
        longMemoryIn: 是否有长时间的记忆（比如说如果玩家离开了视野，是否继续追踪）
         */
        super();
        this.cyberLunatic = entity;
    }

    @Override
    public boolean canUse() {
        return false;
    }
}
