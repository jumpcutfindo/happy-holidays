package com.jumpcutfindo.happyholidays.common.item.christmas.misc;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChristmasGuideBookItem extends ChristmasItem {
    public static final String ITEM_ID = "christmas_guide_book";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(1);

    public ChristmasGuideBookItem() {
        super(ITEM_PROPERTIES);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player playerEntity, InteractionHand hand) {
        playerEntity.awardStat(Stats.ITEM_USED.get(this));
        ItemStack itemStack = playerEntity.getItemInHand(hand);

        if (world.isClientSide()) {
            this.showGuide();
        }

        return InteractionResultHolder.success(itemStack);
    }

    public void showGuide() {
        HappyHolidaysMod.PROXY.openGuideGUI(this.getDefaultInstance());
    }
}
