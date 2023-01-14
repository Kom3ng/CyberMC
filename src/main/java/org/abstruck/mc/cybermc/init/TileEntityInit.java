package org.abstruck.mc.cybermc.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.block.tile.OperatingTableTileEntity;

public class TileEntityInit {
    public static final DeferredRegister<TileEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Utils.MOD_ID);

    public static final RegistryObject<TileEntityType<OperatingTableTileEntity>> OPERATING_TABLE = REGISTER.register("operating_table_tile_entity", () -> TileEntityType.Builder.of(OperatingTableTileEntity::new, BlockInit.OPERATING_TABLE.get()).build(null));
}
