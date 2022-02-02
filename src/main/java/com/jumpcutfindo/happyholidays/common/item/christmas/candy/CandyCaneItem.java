package com.jumpcutfindo.happyholidays.common.item.christmas.candy;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.ChristmasFoodItem;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class CandyCaneItem extends ChristmasFoodItem {
    public static final String ITEM_ID = "candy_cane";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 100, 1), 0.5f)
                            .saturationMod(0.1f)
                            .alwaysEat()
                            .build())
                    .tab(HappyHolidaysTabs.HAPPY_HOLIDAYS_GROUP);

    public CandyCaneItem() {
        super(ITEM_PROPERTIES);
    }
}
