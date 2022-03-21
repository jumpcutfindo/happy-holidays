package com.jumpcutfindo.happyholidays.common.item.christmas;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.PatrolRoute;
import com.jumpcutfindo.happyholidays.common.events.christmas.PatrolOrdersEvent;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.utils.message.GameplayMessage;
import com.jumpcutfindo.happyholidays.common.utils.message.MessageType;
import com.jumpcutfindo.happyholidays.common.utils.message.Messenger;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;

public class PatrolOrdersItem extends ChristmasItem {
    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.CHRISTMAS_GROUP)
                    .stacksTo(64);

    public static final String TOOLTIP_NO_ROUTE = "item.happyholidays.patrol_orders.no_route";
    public static final String TOOLTIP_ROUTE_WITH_LENGTH = "item.happyholidays.patrol_orders.route_with_length";

    public static final String MESSAGE_ROUTE_RESET = "item.happyholidays.patrol_orders.reset";

    public PatrolOrdersItem() {
        super(ITEM_PROPERTIES);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack patrolOrders = player.getItemInHand(hand);
        if (PatrolOrdersItem.isCompletedPatrolOrders(patrolOrders)) {
            player.startUsingItem(hand);
            level.playSound(player, player.blockPosition(), ChristmasSounds.PATROL_ORDERS_CRUMPLE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
            player.getCooldowns().addCooldown(patrolOrders.getItem(), 20);
        }

        return super.use(level, player, hand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack patrolOrders, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            patrolOrders.setTag(new CompoundTag());

            if (level.isClientSide()) {
                GameplayMessage gameplayMessage = new GameplayMessage(MessageType.INFO, MESSAGE_ROUTE_RESET);
                Messenger.sendChatMessage(gameplayMessage, player);
            }
        }

        return super.finishUsingItem(patrolOrders, level, entity);
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

        if (player != null && patrolRoute.isComplete()) {
            player.getCooldowns().addCooldown(patrolOrders.getItem(), 20);

            if (!player.getLevel().isClientSide()) {
                MinecraftForge.EVENT_BUS.post(new PatrolOrdersEvent.Complete(player));
            }
        }

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

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return isCompletedPatrolOrders(itemStack);
    }

    public static boolean isValidPatrolOrders(ItemStack itemStack) {
        return !itemStack.isEmpty() && itemStack.is(ChristmasItems.PATROL_ORDERS.get()) && itemStack.getTag() != null && itemStack.getTag().contains("PatrolRoute");
    }

    public static boolean isCompletedPatrolOrders(ItemStack itemStack) {
        PatrolRoute patrolRoute = extractRoute(itemStack);
        return patrolRoute.isComplete();
    }

    public static PatrolRoute extractRoute(ItemStack patrolOrders) {
        CompoundTag patrolOrdersTag = patrolOrders.getOrCreateTag();
        PatrolRoute patrolRoute = new PatrolRoute();
        if (patrolOrdersTag.contains("PatrolRoute")) {
            patrolRoute.deserializeTag(patrolOrdersTag.getCompound("PatrolRoute"));
        }

        return patrolRoute;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return isCompletedPatrolOrders(itemStack) ? UseAnim.BOW : super.getUseAnimation(itemStack);
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 60;
    }
}
