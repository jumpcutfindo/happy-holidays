package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.container.christmas.MusicBoxContainer;
import com.jumpcutfindo.happyholidays.common.container.christmas.gifts.GiftWrapperContainer;
import com.jumpcutfindo.happyholidays.common.container.christmas.star.ChristmasStarContainer;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ChristmasContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPE =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<MenuType<ChristmasStarContainer>> CHRISTMAS_STAR_CONTAINER =
            CONTAINER_TYPE.register(ChristmasStarContainer.CONTAINER_ID,
                    () -> IForgeContainerType.create(ChristmasStarContainer::new)
            );
    public static final RegistryObject<MenuType<GiftWrapperContainer>> GIFT_WRAPPER_CONTAINER =
            CONTAINER_TYPE.register(GiftWrapperContainer.CONTAINER_ID,
                    () -> IForgeContainerType.create(GiftWrapperContainer::new)
            );
    public static final RegistryObject<MenuType<MusicBoxContainer>> MUSIC_BOX_CONTAINER =
            CONTAINER_TYPE.register(MusicBoxContainer.CONTAINER_ID,
                    () -> IForgeContainerType.create(MusicBoxContainer::new)
            );
}
