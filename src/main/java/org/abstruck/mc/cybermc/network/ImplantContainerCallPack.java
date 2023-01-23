package org.abstruck.mc.cybermc.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.abstruck.mc.cybermc.common.block.tile.OperatingTableTileEntity;
import org.abstruck.mc.cybermc.common.container.OperatingTableContainer;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ImplantContainerCallPack extends BasePack{
    private ImplantType implantType;
    private PlayerEntity player;
    private BlockPos blockPos;

    public ImplantContainerCallPack(PlayerEntity player, ImplantType implantType , BlockPos blockPos){
        this.implantType = implantType;
        this.player = player;
        this.blockPos = blockPos;
    }

    @Override
    public void encode(@NotNull PacketBuffer buffer) {
        buffer.writeUtf(implantType.name());
        buffer.writeUUID(player.getUUID());
        buffer.writeBlockPos(blockPos);
    }

    @OnlyIn(Dist.DEDICATED_SERVER)
    @Contract("_ -> new")
    public static @NotNull ImplantContainerCallPack decode(@NotNull PacketBuffer buffer){
        return new ImplantContainerCallPack(ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(buffer.readUUID()),ImplantType.valueOf(buffer.readUtf()), buffer.readBlockPos());
    }

    @Override
    public void handle(@NotNull Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            World level = player.getCommandSenderWorld();
            TileEntity tileEntity = level.getBlockEntity(blockPos);

            if (!(tileEntity instanceof OperatingTableTileEntity)){
                return;
            }

            OperatingTableTileEntity operatingTableTileEntity = (OperatingTableTileEntity) tileEntity;
            operatingTableTileEntity.setImplantType(implantType);

            NetworkHooks.openGui((ServerPlayerEntity) player,operatingTableTileEntity,buffer -> {
                buffer.writeUtf(implantType.name());
                buffer.writeBlockPos(blockPos);
            });
        });
        ctx.get().setPacketHandled(true);
    }
}
