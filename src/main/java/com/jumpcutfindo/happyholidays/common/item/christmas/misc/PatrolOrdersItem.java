package com.jumpcutfindo.happyholidays.common.item.christmas.misc;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.PatrolRoute;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class PatrolOrdersItem extends ChristmasItem {
    public static final String ITEM_ID = "patrol_orders";
    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(1);

    public static final String TOOLTIP_NO_ROUTE = "item.happyholidays.patrol_orders.no_route";
    public static final String TOOLTIP_ROUTE_WITH_LENGTH = "item.happyholidays.patrol_orders.route_with_length";

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

        boolean isSuccess = patrolRoute.takeAction(level, player, clickedPos);

        patrolOrdersTag.put("PatrolRoute", patrolRoute.serializeTag());

        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        CompoundTag nbt = itemStack.getTag();

        if (nbt != null && nbt.contains("PatrolRoute")) {
            CompoundTag routeTag = nbt.getCompound("PatrolRoute");
            int numPoints = routeTag.getInt("NumPoints") - 1;
            textComponents.add(new TranslatableComponent(TOOLTIP_ROUTE_WITH_LENGTH, numPoints).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
        } else {
            textComponents.add(new TranslatableComponent(TOOLTIP_NO_ROUTE).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
        }
    }
}
