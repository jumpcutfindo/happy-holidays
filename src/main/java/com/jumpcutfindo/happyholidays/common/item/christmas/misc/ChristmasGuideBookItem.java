package com.jumpcutfindo.happyholidays.common.item.christmas.misc;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.jumpcutfindo.happyholidays.common.handlers.GuideHandler;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ChristmasGuideBookItem extends ChristmasItem {
    public static final String ITEM_ID = "christmas_guide_book";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public ChristmasGuideBookItem() {
        super(ITEM_ID, ITEM_PROPERTIES);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.awardStat(Stats.ITEM_USED.get(this));
        ItemStack itemStack = playerEntity.getItemInHand(hand);

        if (!world.isClientSide()) return ActionResult.success(itemStack);
        else {
            ChristmasGuideBookItem.showGuide();
            return ActionResult.success(itemStack);
        }
    }

    public static void showGuide() {
        Minecraft.getInstance().setScreen(new GuideScreen(GuideHandler.getGuide("christmas")));
    }
}
