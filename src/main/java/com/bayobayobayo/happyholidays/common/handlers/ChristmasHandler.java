package com.bayobayobayo.happyholidays.common.handlers;

import com.bayobayobayo.happyholidays.client.screen.ChristmasStarScreen;
import com.bayobayobayo.happyholidays.common.block.christmas.ChristmasBlock;
import com.bayobayobayo.happyholidays.common.entity.christmas.GingerbreadEntities;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;
import com.bayobayobayo.happyholidays.common.registry.BlockRegistry;
import com.bayobayobayo.happyholidays.common.registry.ContainerTypeRegistry;
import com.bayobayobayo.happyholidays.common.registry.ItemRegistry;

import net.minecraft.client.gui.ScreenManager;

public class ChristmasHandler implements ModuleHandler {
    private ChristmasBlock[] christmasBlocks;
    private ChristmasItem[] christmasItems;


    public ChristmasHandler() {
    }

    public void initialise() {
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

                BlockRegistry.RED_TINSEL_BLOCK.get(),
                BlockRegistry.BLUE_TINSEL_BLOCK.get(),
                BlockRegistry.YELLOW_TINSEL_BLOCK.get(),
                BlockRegistry.GREEN_TINSEL_BLOCK.get(),
                BlockRegistry.GOLD_TINSEL_BLOCK.get(),
                BlockRegistry.SILVER_TINSEL_BLOCK.get(),

                BlockRegistry.GINGERBREAD_BLOCK.get(),
                BlockRegistry.RAW_GINGERBREAD_BLOCK.get(),
                BlockRegistry.SOGGY_GINGERBREAD_BLOCK.get(),
        };

        christmasItems = new ChristmasItem[] {
                ItemRegistry.RAW_GINGERBREAD.get(),
                ItemRegistry.GINGERBREAD_COOKIE.get()
        };
    }

    @Override
    public void configureBlocks() {
        for (ChristmasBlock block : christmasBlocks) {
            block.configureBlock();
        }
    }

    @Override
    public void configureItems() {
        for (ChristmasItem item : christmasItems) {
            item.configureItem();
        }
    }

    @Override
    public void configureEntities() {
        GingerbreadEntities.configureEntities();
    }

    @Override
    public void configureContainers() {
        ScreenManager.register(ContainerTypeRegistry.CHRISTMAS_STAR_CONTAINER.get(), ChristmasStarScreen::new);
    }
}
