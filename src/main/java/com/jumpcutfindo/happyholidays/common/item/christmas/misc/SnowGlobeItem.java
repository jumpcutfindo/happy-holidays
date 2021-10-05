package com.jumpcutfindo.happyholidays.common.item.christmas.misc;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PowderSnowCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SnowGlobeItem extends ChristmasItem {
    public static final String ITEM_ID = "snow_globe";

    public static final int DEFAULT_DURABILITY = 0;
    public static final int MAX_DURABILITY = 5;

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(1)
                    .defaultDurability(DEFAULT_DURABILITY)
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
            if (snowGlobe.getDamageValue() >= MAX_DURABILITY) {
                // TODO: Print some result indicating your snow globe is full
                return InteractionResult.sidedSuccess(level.isClientSide());
            }

            if (blockState.getValue(PowderSnowCauldronBlock.LEVEL) != 3) {
                // TODO: Print some result indicating that the cauldron is not full enough
            }

            // TODO: Fill snow globe and empty cauldron

            return InteractionResult.SUCCESS;
        }

        return super.useOn(context);
    }



    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.LEGENDARY;
    }
}
