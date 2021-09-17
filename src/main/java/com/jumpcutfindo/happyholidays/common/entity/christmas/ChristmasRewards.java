package com.jumpcutfindo.happyholidays.common.entity.christmas;

import com.jumpcutfindo.happyholidays.common.blockentity.christmas.ChristmasStarBlockEntity;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChristmasRewards {
    public static double computeDropModifier(Level level, Vec3 position, double step) {
        double modifier;

        ChristmasStarBlockEntity starBlockEntity = ChristmasStarBlockEntity.getStarInfluencingEntity(level, position);
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
