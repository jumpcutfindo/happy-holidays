package com.jumpcutfindo.happyholidays.common.item.christmas.food;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EggnogItem extends ChristmasFoodItem {

    public static final String ITEM_ID = "eggnog";

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(16)
                    .food(new Food.Builder()
                            .nutrition(4)
                            .saturationMod(0.2f)
                            .build()
                    );

    public EggnogItem() {
        super(ITEM_ID, ITEM_PROPERTIES);
    }

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 32;
    }

    @Override
    public UseAction getUseAnimation(ItemStack p_77661_1_) {
        return UseAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        return DrinkHelper.useDrink(p_77659_1_, p_77659_2_, p_77659_3_);
    }

}
