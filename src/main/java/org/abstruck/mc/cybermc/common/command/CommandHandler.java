package org.abstruck.mc.cybermc.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.command.debug.DebugCommand;
import org.abstruck.mc.cybermc.common.command.debug.ResetCommand;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class CommandHandler {
    @SubscribeEvent
    public static void register(@NotNull RegisterCommandsEvent event){
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
        LiteralCommandNode<CommandSource> debug = dispatcher.register(
                Commands.literal(Utils.MOD_ID).then(
                        Commands.literal("debug")
                                .requires((commandSource) -> commandSource.hasPermission(0))
                                .executes(DebugCommand.instance)
                )
        );
        LiteralCommandNode<CommandSource> reset = dispatcher.register(
                Commands.literal(Utils.MOD_ID).then(
                        Commands.literal("reset")
                                .requires((commandSource) -> commandSource.hasPermission(0))
                                .executes(ResetCommand.instance)
                )
        );

        dispatcher.register(Commands.literal("cb").redirect(debug));
    }
}
