package org.abstruck.mc.cybermc.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.common.container.OperatingTableContainer;

public class ContainerTypeInit {
    public static final DeferredRegister<ContainerType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.CONTAINERS, Utils.MOD_ID);

    public static final RegistryObject<ContainerType<OperatingTableContainer>> OPERATING_TABLE_CONTAINER_TYPE = REGISTER.register(
            "operating_table_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> new OperatingTableContainer(windowId,inv,data)))
    );
}
