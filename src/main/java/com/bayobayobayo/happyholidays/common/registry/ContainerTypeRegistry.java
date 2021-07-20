package com.bayobayobayo.happyholidays.common.registry;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.container.christmas.ChristmasStarContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeRegistry {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPE =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<ContainerType<ChristmasStarContainer>> CHRISTMAS_STAR_CONTAINER =
            CONTAINER_TYPE.register(ChristmasStarContainer.CONTAINER_ID,
                    () -> IForgeContainerType.create(ChristmasStarContainer::new)
            );
}
