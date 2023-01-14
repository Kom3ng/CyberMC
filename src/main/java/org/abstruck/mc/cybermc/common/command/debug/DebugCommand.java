package org.abstruck.mc.cybermc.common.command.debug;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.TranslationTextComponent;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.jetbrains.annotations.NotNull;

public class DebugCommand implements Command<CommandSource> {
    public static DebugCommand instance = new DebugCommand();
    @Override
    public int run(@NotNull CommandContext<CommandSource> context) throws CommandSyntaxException {
        final StringBuilder message = new StringBuilder();
        context.getSource().getPlayerOrException().getCapability(ModCapability.CAP).ifPresent(cap -> message.append(cap.getAllImplants().toString()));
        context.getSource().sendSuccess(new TranslationTextComponent(message.toString()),false);
        return 0;
    }
}
