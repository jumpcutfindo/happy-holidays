package com.jumpcutfindo.happyholidays.common.item.christmas.misc;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.PatrolRoute;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class PatrolOrdersItem extends ChristmasItem {
    public static final String ITEM_ID = "patrol_orders";
    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(1);


    public PatrolOrdersItem() {
        super(ITEM_PROPERTIES);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack patrolOrders = context.getItemInHand();

        CompoundTag patrolOrdersTag = patrolOrders.getOrCreateTag();
        PatrolRoute patrolRoute = new PatrolRoute();
        if (patrolOrdersTag.contains("PatrolRoute")) {
            patrolRoute.deserializeTag(patrolOrdersTag.getCompound("PatrolRoute"));
        }

        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos clickedPos = context.getClickedPos();
        patrolRoute.takeAction(level, player, clickedPos);

        patrolOrdersTag.put("PatrolRoute", patrolRoute.serializeTag());

        return super.useOn(context);
    }
}
