package com.jumpcutfindo.happyholidays.common.inventory.christmas;

import com.jumpcutfindo.happyholidays.common.block.entity.christmas.ChristmasStarBlockEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.ChristmasStarEvent;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.utils.message.GameplayMessage;
import com.jumpcutfindo.happyholidays.common.utils.message.MessageType;
import com.jumpcutfindo.happyholidays.common.utils.message.Messenger;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;

public class ChristmasStarSlot extends Slot {
    private final ChristmasStarBlockEntity starBlockEntity;

    public ChristmasStarSlot(ChristmasStarBlockEntity starBlockEntity, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(starBlockEntity, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        this.starBlockEntity = starBlockEntity;
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        boolean flag = ChristmasItems.isOrnamentItem(itemStack);

        if (flag) {
            Level level = starBlockEntity.getLevel();
            if (level != null && !level.isClientSide()) {
                BlockPos blockPos = starBlockEntity.getBlockPos();

                Player playerEntity = level.getNearestPlayer(blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        10.0D, false);

                if (playerEntity != null) {
                    ChristmasStarEvent event = new ChristmasStarEvent.PutOrnament(starBlockEntity, playerEntity);
                    MinecraftForge.EVENT_BUS.post(event);
                }
            }
        }

        return ChristmasItems.isOrnamentItem(itemStack);
    }

    @Override
    public boolean mayPickup(Player playerEntity) {
        boolean mayPickup = !this.starBlockEntity.isBonusActive();

        if (!mayPickup && starBlockEntity.getLevel() != null && starBlockEntity.getLevel().isClientSide()) {
            GameplayMessage message = new GameplayMessage(
                    MessageType.ERROR,
                    "chat.happyholidays.christmas_star.bonus_still_active"
            );

            Messenger.sendChatMessage(message, playerEntity);
        }

        return mayPickup;
    }
}
