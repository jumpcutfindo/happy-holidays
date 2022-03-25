package com.jumpcutfindo.happyholidays.common.item.christmas;

import com.jumpcutfindo.happyholidays.client.events.GuideBookEvent;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;

public class ChristmasGuideBookItem extends ChristmasItem {

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.CHRISTMAS_GROUP)
                    .stacksTo(1);

    public ChristmasGuideBookItem() {
        super(ITEM_PROPERTIES, ChristmasRarity.UNIQUE);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player playerEntity, InteractionHand hand) {
        playerEntity.awardStat(Stats.ITEM_USED.get(this));
        ItemStack itemStack = playerEntity.getItemInHand(hand);

        if (world.isClientSide()) {
            this.showGuide();
        }

        return InteractionResultHolder.success(itemStack);
    }

    public void showGuide() {
        MinecraftForge.EVENT_BUS.post(new GuideBookEvent.Open(this.getDefaultInstance()));
    }
}
