package org.abstruck.mc.cybermc.common.block;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.abstruck.mc.cybermc.common.block.tile.OperatingTableTileEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OperatingTableBlock extends BedBlock {
    public OperatingTableBlock() {
        super(DyeColor.WHITE, Properties.of(Material.METAL).harvestLevel(3));
    }

    @Override
    public @NotNull ActionResultType use(@NotNull BlockState blockState, @NotNull World level, @NotNull BlockPos blockPos, @NotNull PlayerEntity player, @NotNull Hand hand, @NotNull BlockRayTraceResult blockRayTraceResult) {
        //过滤掉客户端操作以及非主手操作
        if (level.isClientSide || !(hand == Hand.MAIN_HAND)){
            return ActionResultType.PASS;
        }

        if (!(level.getBlockEntity(blockPos) instanceof OperatingTableTileEntity)){
            return ActionResultType.PASS;
        }
        //获取手术台的方块实体
        OperatingTableTileEntity operatingTableTileEntity = (OperatingTableTileEntity) level.getBlockEntity(blockPos);
        if (operatingTableTileEntity == null){
            return ActionResultType.PASS;
        }

        NetworkHooks.openGui((ServerPlayerEntity) player, operatingTableTileEntity, packetBuffer->{
            //把方块实体的坐标放进packetBuffer里
            packetBuffer.writeBlockPos(operatingTableTileEntity.getBlockPos());
        });

        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public boolean isBed(BlockState state, IBlockReader world, BlockPos pos, @Nullable Entity player) {
        return false;
    }

    @Override
    public TileEntity newBlockEntity(@NotNull IBlockReader p_196283_1_) {
        return new OperatingTableTileEntity();
    }
}
