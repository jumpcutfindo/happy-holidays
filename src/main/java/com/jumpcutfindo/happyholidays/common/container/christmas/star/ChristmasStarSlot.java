package com.jumpcutfindo.happyholidays.common.container.christmas.star;

import java.util.UUID;

import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class ChristmasStarSlot extends Slot {
    private final ChristmasStarTileEntity starTileEntity;

    public ChristmasStarSlot(ChristmasStarTileEntity starTileEntity, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(starTileEntity, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        this.starTileEntity = starTileEntity;
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return ChristmasItem.isOrnamentItem(itemStack);
    }

    @Override
    public boolean mayPickup(PlayerEntity playerEntity) {
        boolean mayPickup = !this.starTileEntity.isBonusActive();

        if (!mayPickup && starTileEntity.getLevel() != null && starTileEntity.getLevel().isClientSide())
            playerEntity.sendMessage(
                new TranslationTextComponent("chat.happyholidays.christmas_star.bonus_still_active")
                        .withStyle(TextFormatting.RED),
                UUID.randomUUID()
        );

        return mayPickup;
    }

    @Override
    public ItemStack onTake(PlayerEntity p_190901_1_, ItemStack p_190901_2_) {
        return super.onTake(p_190901_1_, p_190901_2_);
    }
}
