package com.jumpcutfindo.happyholidays.common.item.christmas.nutcracker;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SwaggerStickItem extends ChristmasItem {
    public static String ITEM_ID = "swagger_stick";
    public static Item.Properties ITEM_PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .tab(HappyHolidaysTabs.HAPPY_HOLIDAYS_GROUP);

    public SwaggerStickItem() {
        super(ITEM_PROPERTIES);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        return InteractionResultHolder.success(p_41433_.getItemInHand(p_41434_));
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.LEGENDARY;
    }
}
