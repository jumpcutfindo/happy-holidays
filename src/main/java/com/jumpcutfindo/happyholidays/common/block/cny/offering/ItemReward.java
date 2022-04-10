package com.jumpcutfindo.happyholidays.common.block.cny.offering;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemReward extends OfferingReward {
    private final Item rewardItem;
    private final int min, max;

    private ItemReward(Item rewardItem, int min, int max) {
        this.rewardItem = rewardItem;
        this.min = min;
        this.max = max;
    }

    @Override
    public void execute(Level level, Player player, BlockPos blockPos) {
        int count = level.getRandom().nextInt(min, max);
        ItemStack reward = rewardItem.getDefaultInstance();
        reward.setCount(count);

        ItemEntity itemEntity = new ItemEntity(level, blockPos.getX() + 0.5D, blockPos.getY() + 1.0D, blockPos.getZ() + 0.5D, reward);
        itemEntity.setDefaultPickUpDelay();
        level.addFreshEntity(itemEntity);
    }

    public static ItemReward withRewards(Item item, int min, int max) {
        ItemReward reward = new ItemReward(item, min, max);
        return reward;
    }
}
