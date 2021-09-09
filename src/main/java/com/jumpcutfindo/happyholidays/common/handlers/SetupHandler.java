package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNice;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.registry.BlockRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ItemRegistry;
import com.jumpcutfindo.happyholidays.common.registry.TriggerRegistry;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupHandler {
    private static ChristmasBlock[] christmasBlocks;
    private static ChristmasItem[] christmasItems;

    @SubscribeEvent
    public static void christmasCommonSetup(FMLCommonSetupEvent event) {
        CapabilityNaughtyNice.register();
    }

    @SubscribeEvent
    public static void initialise(FMLCommonSetupEvent event) {
        christmasBlocks = new ChristmasBlock[] {
                BlockRegistry.BABY_PRESENT.get(),
                BlockRegistry.ADULT_PRESENT.get(),
                BlockRegistry.ELDER_PRESENT.get(),

                BlockRegistry.RED_BAUBLE.get(),
                BlockRegistry.BLUE_BAUBLE.get(),
                BlockRegistry.YELLOW_BAUBLE.get(),
                BlockRegistry.GREEN_BAUBLE.get(),
                BlockRegistry.GOLD_BAUBLE.get(),
                BlockRegistry.SILVER_BAUBLE.get(),

                BlockRegistry.BIG_RED_BAUBLE.get(),
                BlockRegistry.BIG_BLUE_BAUBLE.get(),
                BlockRegistry.BIG_YELLOW_BAUBLE.get(),
                BlockRegistry.BIG_GREEN_BAUBLE.get(),
                BlockRegistry.BIG_GOLD_BAUBLE.get(),
                BlockRegistry.BIG_SILVER_BAUBLE.get(),

                BlockRegistry.CREEPER_HEAD_ORNAMENT.get(),
                BlockRegistry.SKELETON_HEAD_ORNAMENT.get(),
                BlockRegistry.WITHER_SKELETON_HEAD_ORNAMENT.get(),
                BlockRegistry.ZOMBIE_HEAD_ORNAMENT.get(),

                BlockRegistry.RED_TINSEL.get(),
                BlockRegistry.BLUE_TINSEL.get(),
                BlockRegistry.YELLOW_TINSEL.get(),
                BlockRegistry.GREEN_TINSEL.get(),
                BlockRegistry.GOLD_TINSEL.get(),
                BlockRegistry.SILVER_TINSEL.get(),

                BlockRegistry.RED_CHRISTMAS_LIGHTS.get(),
                BlockRegistry.BLUE_CHRISTMAS_LIGHTS.get(),
                BlockRegistry.YELLOW_CHRISTMAS_LIGHTS.get(),
                BlockRegistry.GREEN_CHRISTMAS_LIGHTS.get(),
                BlockRegistry.GOLD_CHRISTMAS_LIGHTS.get(),
                BlockRegistry.SILVER_CHRISTMAS_LIGHTS.get(),

                BlockRegistry.BABY_PRESENT_ORNAMENT_BLOCK.get(),
                BlockRegistry.ADULT_PRESENT_ORNAMENT_BLOCK.get(),
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

                BlockRegistry.RED_STOCKING_BLOCK.get(),
                BlockRegistry.BLUE_STOCKING_BLOCK.get(),
                BlockRegistry.YELLOW_STOCKING_BLOCK.get(),
                BlockRegistry.GREEN_STOCKING_BLOCK.get(),
                BlockRegistry.GOLD_STOCKING_BLOCK.get(),
                BlockRegistry.SILVER_STOCKING_BLOCK.get(),

                BlockRegistry.CHRISTMAS_WREATH_BLOCK.get(),
                BlockRegistry.SANTA_LIST_BLOCK.get(),

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

    @SubscribeEvent
    public static void addTriggers(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            TriggerRegistry.registerTriggers();
        });
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
