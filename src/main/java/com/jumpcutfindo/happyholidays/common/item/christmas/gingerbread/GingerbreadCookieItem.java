package com.jumpcutfindo.happyholidays.common.item.christmas.gingerbread;

import com.jumpcutfindo.happyholidays.common.handlers.modules.ModuleHandler;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class GingerbreadCookieItem extends ChristmasItem {
    public static final String ITEM_ID = "gingerbread_cookie";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP)
                .food(new Food.Builder()
                        .nutrition(2)
                        .effect(() -> new EffectInstance(Effects.MOVEMENT_SPEED, 100, 1), 0.5f)
                        .saturationMod(0.1f)
                        .alwaysEat()
                        .build()
                );

    public GingerbreadCookieItem() {
        super(ITEM_ID, ITEM_PROPERTIES);
    }
}
