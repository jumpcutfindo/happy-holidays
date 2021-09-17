package com.jumpcutfindo.happyholidays.common.entity.christmas;

import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChristmasRewards {
    public static double computeDropModifier(Level level, Vec3 position, double step) {
        double modifier;

        ChristmasStarTileEntity starTileEntity = ChristmasStarTileEntity.getStarInfluencingEntity(level, position);
        if (starTileEntity != null) {
            if (starTileEntity.isBonusActive()) modifier = 2.0D;
            else modifier = 1.0D + (starTileEntity.getCurrentTier() * step);
        } else modifier = 1.0D;

        return modifier;
    }

    public static boolean isTool(ItemStack itemStack) {
        Item item = itemStack.getItem();
        return item instanceof TieredItem;
    }
}
