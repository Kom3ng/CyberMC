package org.abstruck.mc.cybermc.common.item.implant;

import net.minecraft.entity.player.PlayerEntity;
import org.abstruck.mc.cybermc.common.event.ActivateImplantEvent;

public interface IActive {
    //冷却时间
    int getCoolDownTime();
    //被激活时调用时会调用这个方法
    void onActivate(ActivateImplantEvent event);
    void consumeSan(PlayerEntity player);
}
