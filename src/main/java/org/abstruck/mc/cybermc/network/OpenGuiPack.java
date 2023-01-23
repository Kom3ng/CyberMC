package org.abstruck.mc.cybermc.network;

import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;
import org.abstruck.mc.cybermc.client.gui.ImplantTypeChooseGui;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class OpenGuiPack extends BasePack{
    private BlockPos tileEntityBlockPos;
    public OpenGuiPack(BlockPos tileEntityBlockPos){
        this.tileEntityBlockPos = tileEntityBlockPos;
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull OpenGuiPack decode(@NotNull PacketBuffer buffer){
        return new OpenGuiPack(buffer.readBlockPos());
    }

    public void encode(@NotNull PacketBuffer buffer){
        buffer.writeBlockPos(tileEntityBlockPos);
    }

    public void handle(@NotNull Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(() -> {
            ImplantTypeChooseGui gui = new ImplantTypeChooseGui(tileEntityBlockPos);
            gui.show();
        });
        ctx.get().setPacketHandled(true);
    }
}
