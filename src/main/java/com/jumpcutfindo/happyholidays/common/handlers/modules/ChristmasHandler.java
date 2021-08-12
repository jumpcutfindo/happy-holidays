package com.jumpcutfindo.happyholidays.common.handlers.modules;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.screen.ChristmasStarScreen;
import com.jumpcutfindo.happyholidays.client.screen.GiftWrapperScreen;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.registry.BlockRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ContainerTypeRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ItemRegistry;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChristmasHandler {
    private static ChristmasBlock[] christmasBlocks;
    private static ChristmasItem[] christmasItems;

    @SubscribeEvent
    public static void initialise(FMLCommonSetupEvent event) {
        christmasBlocks = new ChristmasBlock[] {
                BlockRegistry.BABY_PRESENT_BLOCK.get(),
                BlockRegistry.ADULT_PRESENT_BLOCK.get(),
                BlockRegistry.ELDER_PRESENT_BLOCK.get(),

                BlockRegistry.RED_BALL_ORNAMENT_BLOCK.get(),
                BlockRegistry.BLUE_BALL_ORNAMENT_BLOCK.get(),
                BlockRegistry.YELLOW_BALL_ORNAMENT_BLOCK.get(),
                BlockRegistry.GREEN_BALL_ORNAMENT_BLOCK.get(),
                BlockRegistry.GOLD_BALL_ORNAMENT_BLOCK.get(),
                BlockRegistry.SILVER_BALL_ORNAMENT_BLOCK.get(),

                BlockRegistry.BIG_RED_BALL_ORNAMENT_BLOCK.get(),
                BlockRegistry.BIG_BLUE_BALL_ORNAMENT_BLOCK.get(),
                BlockRegistry.BIG_YELLOW_BALL_ORNAMENT_BLOCK.get(),
                BlockRegistry.BIG_GREEN_BALL_ORNAMENT_BLOCK.get(),
                BlockRegistry.BIG_GOLD_BALL_ORNAMENT_BLOCK.get(),
                BlockRegistry.BIG_SILVER_BALL_ORNAMENT_BLOCK.get(),

                BlockRegistry.CREEPER_HEAD_ORNAMENT_BLOCK.get(),
                BlockRegistry.SKELETON_HEAD_ORNAMENT_BLOCK.get(),
                BlockRegistry.WITHER_SKELETON_HEAD_ORNAMENT_BLOCK.get(),
                BlockRegistry.ZOMBIE_HEAD_ORNAMENT_BLOCK.get(),

                BlockRegistry.RED_TINSEL_BLOCK.get(),
                BlockRegistry.BLUE_TINSEL_BLOCK.get(),
                BlockRegistry.YELLOW_TINSEL_BLOCK.get(),
                BlockRegistry.GREEN_TINSEL_BLOCK.get(),
                BlockRegistry.GOLD_TINSEL_BLOCK.get(),
                BlockRegistry.SILVER_TINSEL_BLOCK.get(),

                BlockRegistry.RED_CHRISTMAS_LIGHT_BLOCK.get(),
                BlockRegistry.BLUE_CHRISTMAS_LIGHT_BLOCK.get(),
                BlockRegistry.YELLOW_CHRISTMAS_LIGHT_BLOCK.get(),
                BlockRegistry.GREEN_CHRISTMAS_LIGHT_BLOCK.get(),
                BlockRegistry.GOLD_CHRISTMAS_LIGHT_BLOCK.get(),
                BlockRegistry.SILVER_CHRISTMAS_LIGHT_BLOCK.get(),

                BlockRegistry.BABY_PRESENT_ORNAMENT_BLOCK.get(),
                BlockRegistry.PRESENT_ORNAMENT_BLOCK.get(),
                BlockRegistry.ELDER_PRESENT_ORNAMENT_BLOCK.get(),
                BlockRegistry.CANDY_CANE_ORNAMENT_BLOCK.get(),
                BlockRegistry.SANTA_ELF_ORNAMENT_BLOCK.get(),
                BlockRegistry.GINGERBREAD_MAN_ORNAMENT_BLOCK.get(),
                BlockRegistry.GRINCH_ORNAMENT_BLOCK.get(),

                BlockRegistry.GINGERBREAD_BLOCK.get(),
                BlockRegistry.RAW_GINGERBREAD_BLOCK.get(),
                BlockRegistry.SOGGY_GINGERBREAD_BLOCK.get(),

                BlockRegistry.CANDY_CANE_BLOCK.get(),
                BlockRegistry.FESTIVE_CANDY_CANE_BLOCK.get(),

                BlockRegistry.STOCKING_BLOCK.get(),
                BlockRegistry.CHRISTMAS_WREATH_BLOCK.get(),

                BlockRegistry.MILK_AND_COOKIES_BLOCK.get(),
                BlockRegistry.LOG_CAKE_BLOCK.get(),

                BlockRegistry.CHRISTMAS_STAR_BLOCK.get()
        };

        christmasItems = new ChristmasItem[] {
                ItemRegistry.RAW_GINGERBREAD.get(),
                ItemRegistry.GINGERBREAD_COOKIE.get(),
                ItemRegistry.PRESENT_SCRAPS.get(),
                ItemRegistry.CANDY_CANE.get(),
                ItemRegistry.FESTIVE_CANDY_CANE.get(),
                ItemRegistry.ENCHANTED_CANDY_CANE.get(),

                ItemRegistry.EGGNOG.get()
        };

        configureBlocks();
        configureItems();
    }

    public static void configureBlocks() {
        for (ChristmasBlock block : christmasBlocks) {
            block.configureBlock();
        }
    }

    public static void configureItems() {
        for (ChristmasItem item : christmasItems) {
            item.configureItem();
        }
    }
}
