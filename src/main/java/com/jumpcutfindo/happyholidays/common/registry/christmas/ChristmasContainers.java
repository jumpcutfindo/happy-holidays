package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.inventory.christmas.GiftWrapperContainer;
import com.jumpcutfindo.happyholidays.common.inventory.christmas.MusicBoxContainer;
import com.jumpcutfindo.happyholidays.common.inventory.christmas.NutcrackerContainer;
import com.jumpcutfindo.happyholidays.common.inventory.christmas.ChristmasStarContainer;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINER =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<MenuType<ChristmasStarContainer>> CHRISTMAS_STAR =
            CONTAINER.register(ChristmasStarContainer.CONTAINER_ID,
                    () -> IForgeMenuType.create(ChristmasStarContainer::new)
            );
    public static final RegistryObject<MenuType<GiftWrapperContainer>> GIFT_WRAPPER =
            CONTAINER.register(GiftWrapperContainer.CONTAINER_ID,
                    () -> IForgeMenuType.create(GiftWrapperContainer::new)
            );
    public static final RegistryObject<MenuType<MusicBoxContainer>> MUSIC_BOX =
            CONTAINER.register(MusicBoxContainer.CONTAINER_ID,
                    () -> IForgeMenuType.create(MusicBoxContainer::new)
            );
    public static final RegistryObject<MenuType<NutcrackerContainer>> NUTCRACKER_INVENTORY =
            CONTAINER.register(NutcrackerContainer.CONTAINER_ID,
                    () -> IForgeMenuType.create(NutcrackerContainer::createContainer));
}
