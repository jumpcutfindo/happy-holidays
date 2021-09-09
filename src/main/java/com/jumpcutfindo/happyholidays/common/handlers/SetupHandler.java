package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNice;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasTriggers;

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
                ChristmasBlocks.BABY_PRESENT.get(),
                ChristmasBlocks.ADULT_PRESENT.get(),
                ChristmasBlocks.ELDER_PRESENT.get(),

                ChristmasBlocks.RED_BAUBLE.get(),
                ChristmasBlocks.BLUE_BAUBLE.get(),
                ChristmasBlocks.YELLOW_BAUBLE.get(),
                ChristmasBlocks.GREEN_BAUBLE.get(),
                ChristmasBlocks.GOLD_BAUBLE.get(),
                ChristmasBlocks.SILVER_BAUBLE.get(),

                ChristmasBlocks.BIG_RED_BAUBLE.get(),
                ChristmasBlocks.BIG_BLUE_BAUBLE.get(),
                ChristmasBlocks.BIG_YELLOW_BAUBLE.get(),
                ChristmasBlocks.BIG_GREEN_BAUBLE.get(),
                ChristmasBlocks.BIG_GOLD_BAUBLE.get(),
                ChristmasBlocks.BIG_SILVER_BAUBLE.get(),

                ChristmasBlocks.CREEPER_HEAD_ORNAMENT.get(),
                ChristmasBlocks.SKELETON_HEAD_ORNAMENT.get(),
                ChristmasBlocks.WITHER_SKELETON_HEAD_ORNAMENT.get(),
                ChristmasBlocks.ZOMBIE_HEAD_ORNAMENT.get(),

                ChristmasBlocks.RED_TINSEL.get(),
                ChristmasBlocks.BLUE_TINSEL.get(),
                ChristmasBlocks.YELLOW_TINSEL.get(),
                ChristmasBlocks.GREEN_TINSEL.get(),
                ChristmasBlocks.GOLD_TINSEL.get(),
                ChristmasBlocks.SILVER_TINSEL.get(),

                ChristmasBlocks.RED_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.BLUE_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.YELLOW_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.GREEN_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.GOLD_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.SILVER_CHRISTMAS_LIGHTS.get(),

                ChristmasBlocks.BABY_PRESENT_ORNAMENT.get(),
                ChristmasBlocks.ADULT_PRESENT_ORNAMENT.get(),
                ChristmasBlocks.ELDER_PRESENT_ORNAMENT.get(),
                ChristmasBlocks.CANDY_CANE_ORNAMENT.get(),
                ChristmasBlocks.SANTA_ELF_ORNAMENT.get(),
                ChristmasBlocks.GINGERBREAD_MAN_ORNAMENT.get(),
                ChristmasBlocks.GRINCH_ORNAMENT.get(),

                ChristmasBlocks.GINGERBREAD_BLOCK.get(),
                ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK.get(),
                ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get(),

                ChristmasBlocks.CANDY_CANE_BLOCK.get(),
                ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get(),

                ChristmasBlocks.RED_STOCKING.get(),
                ChristmasBlocks.BLUE_STOCKING.get(),
                ChristmasBlocks.YELLOW_STOCKING.get(),
                ChristmasBlocks.GREEN_STOCKING.get(),
                ChristmasBlocks.GOLD_STOCKING.get(),
                ChristmasBlocks.SILVER_STOCKING.get(),

                ChristmasBlocks.CHRISTMAS_WREATH.get(),
                ChristmasBlocks.SANTA_LIST.get(),

                ChristmasBlocks.MILK_AND_COOKIES.get(),
                ChristmasBlocks.LOG_CAKE.get(),

                ChristmasBlocks.CHRISTMAS_STAR.get()
        };

        christmasItems = new ChristmasItem[] {
                ChristmasItems.RAW_GINGERBREAD.get(),
                ChristmasItems.GINGERBREAD_COOKIE.get(),
                ChristmasItems.PRESENT_SCRAPS.get(),
                ChristmasItems.CANDY_CANE.get(),
                ChristmasItems.FESTIVE_CANDY_CANE.get(),
                ChristmasItems.ENCHANTED_CANDY_CANE.get(),

                ChristmasItems.EGGNOG.get()
        };

        configureBlocks();
        configureItems();
    }

    @SubscribeEvent
    public static void addTriggers(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ChristmasTriggers.registerTriggers();
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
