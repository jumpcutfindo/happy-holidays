package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.inventory.christmas.ChristmasStarContainer;
import com.jumpcutfindo.happyholidays.common.inventory.christmas.GiftWrapperContainer;
import com.jumpcutfindo.happyholidays.common.inventory.christmas.MusicBoxContainer;
import com.jumpcutfindo.happyholidays.common.inventory.christmas.NutcrackerContainer;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINER =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<MenuType<ChristmasStarContainer>> CHRISTMAS_STAR =
            registerContainer("christmas_star", ChristmasStarContainer::new);

    public static final RegistryObject<MenuType<GiftWrapperContainer>> GIFT_WRAPPER =
            registerContainer("gift_wrapping_station", GiftWrapperContainer::new);

    public static final RegistryObject<MenuType<MusicBoxContainer>> MUSIC_BOX =
            registerContainer("music_box", MusicBoxContainer::new);

    public static final RegistryObject<MenuType<NutcrackerContainer>> NUTCRACKER_INVENTORY =
            registerContainer("nutcracker", NutcrackerContainer::createContainer);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerContainer(String id, IContainerFactory<T> supplier) {
        return CONTAINER.register(id, () -> IForgeMenuType.create(supplier));
    }
}
