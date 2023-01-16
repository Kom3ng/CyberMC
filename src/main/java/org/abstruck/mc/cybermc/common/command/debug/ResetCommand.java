package org.abstruck.mc.cybermc.common.command.debug;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.jetbrains.annotations.NotNull;

public class ResetCommand implements Command<CommandSource> {
    public static ResetCommand instance = new ResetCommand();
    @Override
    public int run(@NotNull CommandContext<CommandSource> context) throws CommandSyntaxException {
        context.getSource().getPlayerOrException().getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> cap.setSan(cap.getMaxSan()));
        return 0;
    }
}
