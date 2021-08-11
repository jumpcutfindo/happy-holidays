package com.jumpcutfindo.happyholidays.common.container.christmas.star;

import java.util.UUID;

import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.registry.ItemRegistry;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class ChristmasStarBonusSlot extends Slot {
    private final ChristmasStarTileEntity starTileEntity;

    public ChristmasStarBonusSlot(ChristmasStarTileEntity starTileEntity, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(starTileEntity, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        this.starTileEntity = starTileEntity;
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        boolean isTierOK = starTileEntity.getCurrentTier() == 5;

        if (!isTierOK && starTileEntity.getLevel() != null && starTileEntity.getLevel().isClientSide()) {
            Minecraft.getInstance().player.sendMessage(
                    new TranslationTextComponent("chat.happyholidays.christmas_star.tier_not_ok")
                            .withStyle(TextFormatting.RED),
                    UUID.randomUUID()
            );
        }

        return starTileEntity.getCurrentTier() == 5 && ItemStack.isSame(itemStack,
                ItemRegistry.ENCHANTED_SANTA_HAT.get().getDefaultInstance());
    }

    @Override
    public ItemStack onTake(PlayerEntity p_190901_1_, ItemStack p_190901_2_) {
        return super.onTake(p_190901_1_, p_190901_2_);
    }
}
