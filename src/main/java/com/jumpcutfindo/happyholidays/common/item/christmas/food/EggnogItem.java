package com.jumpcutfindo.happyholidays.common.item.christmas.food;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;

public class EggnogItem extends ChristmasFoodItem {

    public static final String ITEM_ID = "eggnog";

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.CHRISTMAS_GROUP)
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationMod(0.2f)
                            .build()
                    );

    public EggnogItem() {
        super(ITEM_PROPERTIES);
    }

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_77661_1_) {
        return UseAnim.DRINK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_77659_1_, Player p_77659_2_, InteractionHand p_77659_3_) {
        return ItemUtils.startUsingInstantly(p_77659_1_, p_77659_2_, p_77659_3_);
    }

}
