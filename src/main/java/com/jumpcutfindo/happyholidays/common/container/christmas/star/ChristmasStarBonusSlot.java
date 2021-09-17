package com.jumpcutfindo.happyholidays.common.container.christmas.star;

import java.util.UUID;

import com.jumpcutfindo.happyholidays.common.events.christmas.ChristmasStarEvent;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.ChristmasStarBlockEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;

public class ChristmasStarBonusSlot extends Slot {
    private final ChristmasStarBlockEntity starBlockEntity;

    public ChristmasStarBonusSlot(ChristmasStarBlockEntity starBlockEntity, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(starBlockEntity, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        this.starBlockEntity = starBlockEntity;
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        boolean isTierOK = starBlockEntity.getCurrentTier() == 5;

        // If cannot place, we notify the user; if can, we emit an event
        if (starBlockEntity.getLevel() != null && !starBlockEntity.getLevel().isClientSide()) {
            if (isTierOK) {
                Level level = starBlockEntity.getLevel();
                if (level != null && !level.isClientSide()) {
                    BlockPos blockPos = starBlockEntity.getBlockPos();

                    Player playerEntity = level.getNearestPlayer(blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                            10.0D, false);

                    if (playerEntity != null) {
                        ChristmasStarEvent event = new ChristmasStarEvent.ReachBonus(starBlockEntity, playerEntity);
                        MinecraftForge.EVENT_BUS.post(event);
                    }
                }
            } else {
                Minecraft.getInstance().player.sendMessage(
                        new TranslatableComponent("chat.happyholidays.christmas_star.tier_not_ok")
                                .withStyle(ChatFormatting.RED),
                        UUID.randomUUID()
                );
            }
        }

        return starBlockEntity.getCurrentTier() == 5 && ItemStack.isSame(itemStack,
                ChristmasItems.ENCHANTED_SANTA_HAT.get().getDefaultInstance());
    }
}
