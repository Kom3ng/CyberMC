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

    public OperatingTableTileEntity() {
        super(TileEntityInit.OPERATING_TABLE.get());
    }


    @Override
    public @NotNull ITextComponent getDisplayName() {
        return new TranslationTextComponent(Utils.MOD_ID + ".gui.operating_table_container_gui_name");
    }

    @Nullable
    @Override
    public Container createMenu(int id, @NotNull PlayerInventory inventory, @NotNull PlayerEntity player) {
        return new OperatingTableContainer(id,inventory,null);
    }
}
