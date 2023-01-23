package org.abstruck.mc.cybermc.common.block.tile;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.container.OperatingTableContainer;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.abstruck.mc.cybermc.init.TileEntityInit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OperatingTableTileEntity extends TileEntity implements INamedContainerProvider {
    private ImplantType type;

    public OperatingTableTileEntity() {
        super(TileEntityInit.OPERATING_TABLE.get());
    }


    public void setImplantType(ImplantType type){
        this.type = type;
    }

    public ImplantType getImplantType(){
        return type;
    }

    @Override
    public @NotNull ITextComponent getDisplayName() {
        return new TranslationTextComponent(Utils.MOD_ID + ".gui.operating_table_container_gui_name");
    }

    @Nullable
    @Override
    public Container createMenu(int id, @NotNull PlayerInventory inventory, @NotNull PlayerEntity player) {
        if (getImplantType() == null){
            return null;
        }

        PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());

        buffer.writeUtf(getImplantType().name());
        buffer.writeBlockPos(this.getBlockPos());

        return new OperatingTableContainer(id,inventory,buffer);
    }
}
