package org.abstruck.mc.cybermc.mixin;

import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = Util.class, priority = Integer.MAX_VALUE)
public abstract class UtilMixin {

}
