package com.jumpcutfindo.happyholidays.common.container.christmas.star;

import java.util.UUID;

import com.jumpcutfindo.happyholidays.common.events.christmas.ChristmasStarEvent;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;

public class ChristmasStarSlot extends Slot {
    private final ChristmasStarTileEntity starTileEntity;

    public ChristmasStarSlot(ChristmasStarTileEntity starTileEntity, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(starTileEntity, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        this.starTileEntity = starTileEntity;
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        boolean flag = ChristmasItems.isOrnamentItem(itemStack);

        if (flag) {
            Level level = starTileEntity.getLevel();
            if (level != null && !level.isClientSide()) {
                BlockPos blockPos = starTileEntity.getBlockPos();

                Player playerEntity = level.getNearestPlayer(blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        10.0D, false);

                if (playerEntity != null) {
                    ChristmasStarEvent event = new ChristmasStarEvent.PutOrnament(starTileEntity, playerEntity);
                    MinecraftForge.EVENT_BUS.post(event);
                }
            }
        }

        return ChristmasItems.isOrnamentItem(itemStack);
    }

    @Override
    public boolean mayPickup(Player playerEntity) {
        boolean mayPickup = !this.starTileEntity.isBonusActive();

        if (!mayPickup && starTileEntity.getLevel() != null && starTileEntity.getLevel().isClientSide())
            playerEntity.sendMessage(
                new TranslatableComponent("chat.happyholidays.christmas_star.bonus_still_active")
                        .withStyle(ChatFormatting.RED),
                UUID.randomUUID()
        );

        return mayPickup;
    }
}
