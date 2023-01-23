package org.abstruck.mc.cybermc.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.container.slot.ImplantSlot;
import org.abstruck.mc.cybermc.init.ContainerTypeInit;
import org.abstruck.mc.cybermc.common.inventory.ImplantInventory;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class OperatingTableContainer extends Container {

    ImplantInventory implantInventory;
    PlayerInventory playerInventory;
    public OperatingTableContainer(int id, @NotNull PlayerInventory playerInventory, PacketBuffer buffer) {
        super(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(), id);

        this.playerInventory = playerInventory;
        this.implantInventory = new ImplantInventory();

//        switchTo(ImplantType.valueOf(buffer.readUtf()));
        layoutPlayerInventory(getPlayerInventory());
        layoutImplantInventory(getImplantInventory());
    }

    private void layoutImplantInventory(@NotNull IInventory inventory){
        int rectifyX = 14;
        int rectifyY = 6;
        int typeIndex = 0;
        int itemIndex = 0;
        int dx = 20;
        int dy = 19;

        for (int i3 = 0; i3 <2; i3++) {
            for (int j3 = 0; j3 < 3; j3++) {
                addSlot(new ImplantSlot(inventory, itemIndex, rectifyX + j3 * dx, rectifyY + i3 * dy, ImplantType.values()[typeIndex]));
                itemIndex++;
            }
            typeIndex++;
        }

        rectifyY = 46;


        for (int i2 = 0; i2 <2; i2++){
            for (int j2 = 0; j2 <3 ; j2++){
                addSlot(new ImplantSlot(inventory, itemIndex, rectifyX + j2 * dx, rectifyY + i2 * dy, ImplantType.values()[typeIndex]));
                itemIndex++;
            }
            typeIndex++;
        }

        rectifyX = 183;

        for (int i1 = 0; i1 <2; i1++){
            for (int j1 = 0; j1 <3 ; j1++){
                addSlot(new ImplantSlot(inventory, itemIndex, rectifyX + j1 * dx, rectifyY + i1 * dy, ImplantType.values()[typeIndex]));
                itemIndex++;
            }
            typeIndex++;
        }

        rectifyY = 6;

        for (int i = 0;i<2;i++){
            for (int j = 0; j<3 ;j++){
                addSlot(new ImplantSlot(inventory, itemIndex, rectifyX + j*dx, rectifyY + i*dy, ImplantType.values()[typeIndex]));
                itemIndex++;
            }
            typeIndex++;
        }

    }

    private void layoutPlayerInventory(IInventory inventory){
        // 创建玩家背包物品栏
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++){
                this.addSlot(new Slot(inventory, col + row * 9 + 9,  47 + col * 18,178 - (4 - row) * 18 - 10));
            }
        }

        // 工具栏
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inventory, col, 47 + col * 18, 178 - 24));
        }
    }

    @Override
    public boolean stillValid(@NotNull PlayerEntity player) {
        return getImplantInventory().stillValid(player);
    }


    public @NotNull Map<ImplantType, List<Implant>> getTypeImplantMap(){
        Map<ImplantType, List<Implant>> result = new HashMap<>();

        //没有implant的情况下返回空map
        if (getImplantInventory().isEmpty()){
            return result;
        }

        //遍历所有的物品
        for (int index = 0; index<getImplantInventory().getContainerSize(); index++){
            //如果找个空没有物品就继续
            if (getImplantInventory().getItem(index).isEmpty()){
                continue;
            }
            //如果不是implant继续
            if (!(getImplantInventory().getItem(index).getItem() instanceof Implant)){
                continue;
            }

            Implant implant = (Implant) getImplantInventory().getItem(index).getItem();
            ImplantType type = implant.getType();

            //如果没有这个key
            if (!result.containsKey(type)){
                result.put(type, new ArrayList<>(Collections.singletonList(implant)));
                continue;
            }

            result.computeIfAbsent(type, t -> new ArrayList<>()).add(implant);
        }

        return result;
    }
    public ImplantInventory getImplantInventory() {
        this.playerInventory.player.getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> implantInventory = cap.getImplantInventory());
        return implantInventory;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull PlayerEntity pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

}
