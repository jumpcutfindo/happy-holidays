package com.jumpcutfindo.happyholidays.common.item.christmas.gingerbread;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class GingerbreadCookieItem extends ChristmasItem {
    public static final String ITEM_ID = "gingerbread_cookie";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                .tab(HappyHolidaysTabs.HAPPY_HOLIDAYS_GROUP)
                .food(new FoodProperties.Builder()
                        .nutrition(2)
                        .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1), 0.5f)
                        .saturationMod(0.1f)
                        .alwaysEat()
                        .build()
                );

    public GingerbreadCookieItem() {
        super(ITEM_PROPERTIES);
    }
}
