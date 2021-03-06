package com.jumpcutfindo.happyholidays.common.item.christmas;

import com.jumpcutfindo.happyholidays.common.events.christmas.SnowGlobeEvent;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.utils.message.GameplayMessage;
import com.jumpcutfindo.happyholidays.common.utils.message.MessageType;
import com.jumpcutfindo.happyholidays.common.utils.message.Messenger;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PowderSnowCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;

public class SnowGlobeItem extends ChristmasItem {

    public static final String MESSAGE_WEATHER_MAXED = "chat.happyholidays.snow_globe.weather_maxed";
    public static final String MESSAGE_WEATHER_RAIN_TO_THUNDER = "chat.happyholidays.snow_globe.weather_rain_to_thunder";
    public static final String MESSAGE_WEATHER_CLEAR_TO_RAIN = "chat.happyholidays.snow_globe.weather_clear_to_rain";
    public static final String MESSAGE_TRY_USE_WHEN_EMPTY = "chat.happyholidays.snow_globe.try_use_when_empty";
    public static final String MESSAGE_TRY_FILL_WHEN_FULL = "chat.happyholidays.snow_globe.try_fill_when_full";
    public static final String MESSAGE_TRY_FILL_WHEN_CAULDRON_NOT_FULL = "chat.happyholidays.snow_globe.try_fill_when_cauldron_not_full";

    public static final int DEFAULT_DAMAGE = 5;
    public static final int MAX_DURABILITY = 5;

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.CHRISTMAS_GROUP)
                    .stacksTo(1)
                    .defaultDurability(DEFAULT_DAMAGE)
                    .durability(MAX_DURABILITY);

    public SnowGlobeItem() {
        super(ITEM_PROPERTIES, ChristmasRarity.LEGENDARY);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack snowGlobe = context.getItemInHand();

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState blockState = level.getBlockState(pos);

        Player player = context.getPlayer();

        if (blockState.is(Blocks.POWDER_SNOW_CAULDRON)) {
            if (this.isFullyCharged(snowGlobe)) {
                GameplayMessage message = new GameplayMessage(MessageType.ERROR, MESSAGE_TRY_FILL_WHEN_FULL);
                Messenger.sendClientMessage(message, player);

                return InteractionResult.sidedSuccess(level.isClientSide());
            }

            if (blockState.getValue(PowderSnowCauldronBlock.LEVEL) != 3) {
                GameplayMessage message = new GameplayMessage(MessageType.ERROR, MESSAGE_TRY_FILL_WHEN_CAULDRON_NOT_FULL);
                Messenger.sendClientMessage(message, player);

                return InteractionResult.sidedSuccess(level.isClientSide());
            }

            level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
            this.addCharge(snowGlobe);
            level.playSound(null, pos, ChristmasSounds.SNOW_GLOBE_FILL.get(), SoundSource.BLOCKS, 1.0f, 1.0f);

            return InteractionResult.SUCCESS;
        }

        return super.useOn(context);
    }

    public void addCharge(ItemStack snowGlobe) {
        snowGlobe.setDamageValue(snowGlobe.getDamageValue() - 1);
    }

    public int getCharges(ItemStack snowGlobe) {
        return snowGlobe.getDamageValue();
    }

    public boolean isFullyCharged(ItemStack snowGlobe) {
        return snowGlobe.getDamageValue() <= 0;
    }

    public boolean hasCharges(ItemStack snowGlobe) {
        return snowGlobe.getDamageValue() < MAX_DURABILITY;
    }

    public void useCharge(ItemStack snowGlobe, Level level, Player player) {
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;

            boolean shouldReduceCharges = false;
            if (serverLevel.isRaining() && serverLevel.isThundering()) {
                // Weather is maxed out
                GameplayMessage message = new GameplayMessage(MessageType.STANDARD, MESSAGE_WEATHER_MAXED);
                Messenger.sendChatMessage(message, player);
            } else if (serverLevel.isRaining() && !serverLevel.isThundering()) {
                // Weather is raining, but not thundering
                serverLevel.setWeatherParameters(0, 6000, true, true);
                shouldReduceCharges = true;

                GameplayMessage message = new GameplayMessage(MessageType.STANDARD, MESSAGE_WEATHER_RAIN_TO_THUNDER);
                Messenger.sendChatMessage(message, player);
            } else if (!serverLevel.isRaining()) {
                // Weather is clear
                serverLevel.setWeatherParameters(0, 6000, true, false);
                shouldReduceCharges = true;

                GameplayMessage message = new GameplayMessage(MessageType.STANDARD, MESSAGE_WEATHER_CLEAR_TO_RAIN);
                Messenger.sendChatMessage(message, player);
            }

            if (player.isCreative()) shouldReduceCharges = false;

            if (shouldReduceCharges) {
                // If should reduce charges, this means we've successfully used it
                snowGlobe.setDamageValue(snowGlobe.getDamageValue() + 1);
            }

            // Add a cooldown to prevent using immediately after
            player.getCooldowns().addCooldown(snowGlobe.getItem(), 20);

            // Produce effects on Snow Globe use
            double d0 = player.getX() + (player.getRandom().nextDouble() * 2.0D - 1.0D) * (double) player.getBbWidth() * 0.5D;
            double d1 = player.getY() + 0.05D + player.getRandom().nextDouble();
            double d2 = player.getZ() + (player.getRandom().nextDouble() * 2.0D - 1.0D) * (double) player.getBbWidth() * 0.5D;
            double d3 = (player.getRandom().nextDouble() * 2.0D - 1.0D) * 0.3D;
            double d4 = 0.3D + player.getRandom().nextDouble() * 0.3D;
            double d5 = (player.getRandom().nextDouble() * 2.0D - 1.0D) * 0.3D;

            serverLevel.sendParticles(ParticleTypes.SNOWFLAKE, d0, d1 + 1.0D, d2,
                    25, d3, d4, d5, 0.0D);

            serverLevel.playSound(null, player.blockPosition(), ChristmasSounds.SNOW_GLOBE_SUCCESS.get(), SoundSource.NEUTRAL, 1.0f, 1.0f);

            // Emit use event
            SnowGlobeEvent.Use useGlobeEvent = new SnowGlobeEvent.Use(player);
            MinecraftForge.EVENT_BUS.post(useGlobeEvent);
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack snowGlobe = player.getItemInHand(hand);

        if (this.hasCharges(snowGlobe)) {
            player.startUsingItem(hand);
            level.playSound(null, player.blockPosition(), ChristmasSounds.SNOW_GLOBE_USING.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
        } else {
            GameplayMessage message = new GameplayMessage(MessageType.ERROR, MESSAGE_TRY_USE_WHEN_EMPTY);
            Messenger.sendClientMessage(message, player);
        }

        return super.use(level, player, hand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack snowGlobe, Level level, LivingEntity entity) {
        if (this.hasCharges(snowGlobe) && entity instanceof Player) {
            this.useCharge(snowGlobe, level, (Player) entity);
        }

        return super.finishUsingItem(snowGlobe, level, entity);
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 50;
    }
}
