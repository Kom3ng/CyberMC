package org.abstruck.mc.cybermc.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import org.abstruck.mc.cybermc.common.capability.ModCapability;
import org.abstruck.mc.cybermc.common.container.slot.ImplantSlot;
import org.abstruck.mc.cybermc.init.ContainerTypeInit;
import org.abstruck.mc.cybermc.common.inventory.ImplantInventory;
import org.abstruck.mc.cybermc.common.item.implant.Implant;
import org.abstruck.mc.cybermc.common.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class OperatingTableContainer extends Container {

    ImplantInventory implantInventory;
    PlayerInventory playerInventory;
    public OperatingTableContainer(int id, @NotNull PlayerInventory playerInventory, PacketBuffer buffer) {
        super(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(), id);

        this.playerInventory = playerInventory;
        this.implantInventory = new ImplantInventory();

        switchTo(ImplantType.valueOf(buffer.readUtf()));
        layoutPlayerInventory(getPlayerInventory());
//        layoutImplantInventory(getImplantInventory());
    }

    private void layoutImplantInventory(@NotNull IInventory inventory){
        int startX = 8;
        int startY = 10;
        int dx = 18;
        int dy = 18;

        for (int index = 0;index<inventory.getContainerSize();index++){
            addSlot(new ImplantSlot(inventory,index,(index%9)*dx + startX,(index/9)*dy + startY, ImplantType.values()[index/3]));
        }
    }

    private void layoutPlayerInventory(IInventory inventory){
        // 创建玩家背包物品栏
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++){
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // 工具栏
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 142));
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

    public void switchTo(ImplantType type){
        this.slots.clear();
        int range = 3;
        int startIndex = Arrays.stream(ImplantType.values()).collect(Collectors.toList()).indexOf(type)*range;
        for (int i = 0;i<range;i++){
            addSlot(new Slot(getImplantInventory(),startIndex+i,61+18*i,27));
        }
    }
}
