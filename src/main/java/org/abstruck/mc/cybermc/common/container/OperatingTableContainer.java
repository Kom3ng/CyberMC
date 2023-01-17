package org.abstruck.mc.cybermc.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
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
    public OperatingTableContainer(int id, @NotNull PlayerInventory playerInventory, BlockPos blockPos) {
        super(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(), id);
        this.playerInventory = playerInventory;
        this.implantInventory = new ImplantInventory();

        initImplantInventory();

        layoutPlayerInventory(getPlayerInventory());
        layoutImplantInventory(getImplantInventory());
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

    public void initImplantInventory(){
//        PlayerEntity player = getPlayerInventory().player;
//        for (int typeIndex = 0, index = 0;index<getImplantInventory().getContainerSize() && typeIndex<ImplantType.values().length; index +=3,typeIndex++){
//            int finalTypeIndex = typeIndex;
//            List<Implant> implants = new ArrayList<>();
//            player.getCapability(ModCapability.CAP).ifPresent(cap -> implants.addAll(cap.getImplants(ImplantType.values()[finalTypeIndex])));
//
//            if (implants.isEmpty()){
//                continue;
//            }
//            for (int i = 0;i<3;i++){
//                if (implants.size()<=i){
//                    break;
//                }
//                getImplantInventory().setItem(index,new ItemStack(implants.get(i)));
//            }
//        }
//
//        PlayerEntity player = getPlayerInventory().player;
//
//        Map<ImplantType,List<Implant>> map = new HashMap<>();
//        player.getCapability(ModCapability.CYBER_PLAYER_DATA_CAP).ifPresent(cap -> Arrays.stream(ImplantType.values()).forEach(type -> map.put(type,cap.getImplants(type))));
//
//        for (int i = 0;i < getImplantInventory().getContainerSize();){
//            for (int j = 0;j<3;j++){
//                if (map.get(ImplantType.values()[i/3]).size()>j){
//                    getImplantInventory().setItem(i,new ItemStack(map.get(ImplantType.values()[i/3]).get(j)));
//                }
//                i++;
//            }
//        }
    }


    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull PlayerEntity pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }
}
