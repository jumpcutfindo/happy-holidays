package com.jumpcutfindo.happyholidays.common.item.christmas.misc;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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

public class SnowGlobeItem extends ChristmasItem {
    public static final String ITEM_ID = "snow_globe";

    public static final int DEFAULT_DAMAGE = 5;
    public static final int MAX_DURABILITY = 5;

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(1)
                    .defaultDurability(DEFAULT_DAMAGE)
                    .durability(MAX_DURABILITY);

    public SnowGlobeItem() {
        super(ITEM_PROPERTIES);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack snowGlobe = context.getItemInHand();

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState blockState = level.getBlockState(pos);

        if (blockState.is(Blocks.POWDER_SNOW_CAULDRON)) {
            if (this.isFullyCharged(snowGlobe)) {
                // TODO: Print some result indicating your snow globe is full
                return InteractionResult.sidedSuccess(level.isClientSide());
            }

            if (blockState.getValue(PowderSnowCauldronBlock.LEVEL) != 3) {
                // TODO: Print some result indicating that the cauldron is not full enough
                return InteractionResult.sidedSuccess(level.isClientSide());
            }

            level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
            this.addCharge(snowGlobe);

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

    public void useCharge(Level level, ItemStack snowGlobe) {
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;

            boolean shouldReduceCharges = false;
            if (serverLevel.isRaining() && serverLevel.isThundering()) {
                // TODO: Send some message that indicates that you can't amplify the weather conditions
            } else if (serverLevel.isRaining() && !serverLevel.isThundering()) {
                serverLevel.setWeatherParameters(0, 6000, true, true);
                shouldReduceCharges = true;
            } else if (!serverLevel.isRaining()) {
                serverLevel.setWeatherParameters(0, 6000, true, false);
                shouldReduceCharges = true;
            }

            if (shouldReduceCharges) snowGlobe.setDamageValue(snowGlobe.getDamageValue() + 1);
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
        }

        return super.use(level, player, hand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack snowGlobe, Level level, LivingEntity entity) {
        if (this.hasCharges(snowGlobe)) this.useCharge(level, snowGlobe);

        return super.finishUsingItem(snowGlobe, level, entity);
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 10;
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.LEGENDARY;
    }
}
