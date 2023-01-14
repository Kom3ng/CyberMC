package org.abstruck.mc.cybermc.client.utils;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class ServerUtil {
    public static ServerPlayerEntity getServerPlayer(ClientPlayerEntity player){
        return ServerLifecycleHooks.getCurrentServer().getPlayerList()
    }
}
