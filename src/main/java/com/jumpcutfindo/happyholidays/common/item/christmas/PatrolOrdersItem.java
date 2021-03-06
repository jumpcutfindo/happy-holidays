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

    public static final String ROUTE_ADD_POINT_SUCCESS = "item.happyholidays.patrol_orders.add_point_success";
    public static final String ROUTE_ADD_POINT_FAIL = "item.happyholidays.patrol_orders.add_point_fail";
    public static final String ROUTE_REMOVE_POINT_SUCCESS = "item.happyholidays.patrol_orders.remove_point_success";
    public static final String ROUTE_REMOVE_POINT_FAIL = "item.happyholidays.patrol_orders.remove_point_fail";
    public static final String ROUTE_COMPLETE = "item.happyholidays.patrol_orders.complete_route";
    public static final String ROUTE_LOCKED = "item.happyholidays.patrol_orders.locked_route";


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
            patrolOrders.shrink(1);
            player.addItem(ChristmasItems.PATROL_ORDERS.get().getDefaultInstance());

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

        // Process action and send to result handler for client side effects
        PatrolRoute.ActionResult actionResult = patrolRoute.takeAction(level, player, clickedPos);
        this.handleActionResult(level, player, actionResult);

        // If success, we reset the entire current stack and give the player a new item
        // If not, we just add on to the current stack
        if (actionResult == PatrolRoute.ActionResult.COMPLETE) {
            patrolOrdersTag.remove("PatrolRoute");
            patrolOrders.shrink(1);

            ItemStack newPatrolOrders = ChristmasItems.PATROL_ORDERS.get().getDefaultInstance();
            CompoundTag newTag = newPatrolOrders.getOrCreateTag();
            newTag.put("PatrolRoute", patrolRoute.serializeTag());
            player.addItem(newPatrolOrders);
        } else {
            patrolOrdersTag.put("PatrolRoute", patrolRoute.serializeTag());
        }

        if (player != null && patrolRoute.isComplete()) {
            player.getCooldowns().addCooldown(patrolOrders.getItem(), 20);

            if (!player.getLevel().isClientSide()) {
                MinecraftForge.EVENT_BUS.post(new PatrolOrdersEvent.Complete(player));
            }
        }

        return super.useOn(context);
    }

    public void handleActionResult(Level level, Player player, PatrolRoute.ActionResult actionResult) {
        // Handle playing of sounds and effects depending on the result of the action
        if (level.isClientSide()) {
            switch (actionResult) {
            case ADD_POINT_SUCCESS -> {
                GameplayMessage message = new GameplayMessage(MessageType.SUCCESS, ROUTE_ADD_POINT_SUCCESS);
                Messenger.sendChatMessage(message, player);
                this.playSuccessSound(level, player);
            }
            case ADD_POINT_FAIL -> {
                GameplayMessage message = new GameplayMessage(MessageType.ERROR, ROUTE_ADD_POINT_FAIL);
                Messenger.sendChatMessage(message, player);
                this.playFailSound(level, player);
            }
            case REMOVE_POINT_SUCCESS -> {
                GameplayMessage message = new GameplayMessage(MessageType.SUCCESS, ROUTE_REMOVE_POINT_SUCCESS);
                Messenger.sendChatMessage(message, player);
                this.playSuccessSound(level, player);
            }
            case REMOVE_POINT_FAIL -> {
                GameplayMessage message = new GameplayMessage(MessageType.ERROR, ROUTE_REMOVE_POINT_FAIL);
                Messenger.sendChatMessage(message, player);
                this.playFailSound(level, player);
            }
            case COMPLETE -> {
                GameplayMessage message = new GameplayMessage(MessageType.SUCCESS, ROUTE_COMPLETE);
                Messenger.sendChatMessage(message, player);
                this.playCompleteSound(level, player);
            }
            case LOCKED -> {
                GameplayMessage message = new GameplayMessage(MessageType.NEUTRAL, ROUTE_LOCKED);
                Messenger.sendChatMessage(message, player);
                this.playFailSound(level, player);
            }
            default -> {
                // We don't do anything by default
            }
            }
        }
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

    private void playSuccessSound(Level level, Player player) {
        level.playSound(player, player.blockPosition(), ChristmasSounds.PATROL_ORDERS_ACTION_SUCCESS.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
    }

    private void playFailSound(Level level, Player player) {
        level.playSound(player, player.blockPosition(), ChristmasSounds.PATROL_ORDERS_ACTION_FAIL.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
    }

    private void playCompleteSound(Level level, Player player) {
        level.playSound(player, player.blockPosition(), ChristmasSounds.PATROL_ORDERS_ROUTE_COMPLETE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
    }
}
