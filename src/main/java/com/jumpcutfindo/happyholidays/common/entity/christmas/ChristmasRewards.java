package com.jumpcutfindo.happyholidays.common.entity.christmas;

import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarBlockEntity;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarHelper;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChristmasRewards {
    /**
     * Computes the modifier for Christmas drops, which can be used for the drop chance or dropping more items when
     * rewards are given. If there is a bonus Christmas Star nearby, the modifier is 2.0. If not, it will be incremented
     * proportionally to the level of the highest-levelled Christmas Star influencing that position.
     */
    public static double computeModifier(Level level, Vec3 position, double step) {
        double modifier;

        ChristmasStarBlockEntity starBlockEntity = ChristmasStarHelper.getStarInfluencingEntity(level, position);
        if (starBlockEntity != null) {
            if (starBlockEntity.isBonusActive()) modifier = 2.0D;
            else modifier = 1.0D + (starBlockEntity.getCurrentTier() * step);
        } else modifier = 1.0D;

        return modifier;
    }

    public static boolean isTool(ItemStack itemStack) {
        Item item = itemStack.getItem();
        return item instanceof TieredItem;
    }
}
