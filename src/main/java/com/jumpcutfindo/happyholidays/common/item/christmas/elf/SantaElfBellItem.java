package com.jumpcutfindo.happyholidays.common.item.christmas.elf;

import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaElfEvent;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.utils.BlockUtils;
import com.jumpcutfindo.happyholidays.common.utils.StringUtils;
import com.jumpcutfindo.happyholidays.common.utils.message.GameplayMessage;
import com.jumpcutfindo.happyholidays.common.utils.message.MessageType;
import com.jumpcutfindo.happyholidays.common.utils.message.Messenger;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;

public class SantaElfBellItem extends ChristmasItem {
    public static final String ITEM_ID = "santa_elf_bell";

    public static final int ITEM_USE_DURATION = 35;
    public static final int ITEM_COOLDOWN = 24000;

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(1)
                    .defaultDurability(ITEM_COOLDOWN);

    public SantaElfBellItem() {
        super(ITEM_PROPERTIES);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        CompoundTag nbt = player.getItemInHand(hand).getTag();
        long nextUseTime = nbt.getLong("NextUseTime");
        if (world.getGameTime() > nextUseTime) {
            player.startUsingItem(hand);

            player.playSound(ChristmasSounds.SANTA_ELF_BELL.get(), 1.0F, 1.0F);
            return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
        } else {
            long timeRemaining = nbt.getLong("NextUseTime") - world.getGameTime();

            GameplayMessage message = new GameplayMessage(
                    MessageType.ERROR,
                    "chat.happyholidays.santa_elf_bell.not_ready",
                    StringUtils.convertTicksToString(timeRemaining)
            );

            Messenger.sendClientMessage(message, player);
            return InteractionResultHolder.fail(player.getItemInHand(hand));
        }

    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            BlockPos playerPos = player.blockPosition();

            if (!player.isCreative()) {
                CompoundTag nbt = stack.getOrCreateTag();
                nbt.putLong("NextUseTime", world.getGameTime() + (long) ITEM_COOLDOWN);
            }

            BlockPos posAhead = BlockUtils.getPosInFront(player.getDirection(), playerPos, 4.0D);

            double spawnX, spawnY, spawnZ;
            spawnX = posAhead.getX();
            spawnY = posAhead.getY();
            spawnZ = posAhead.getZ();

            SantaElfEntity santaElfEntity = ChristmasEntities.SANTA_ELF.get().create(world);
            santaElfEntity.moveTo(spawnX, spawnY, spawnZ, 0.0F, 0.0F);
            world.addFreshEntity(santaElfEntity);

            world.addParticle(ParticleTypes.LARGE_SMOKE, spawnX, spawnY, spawnZ, 0.0F, 0.0F, 0.0F);
            world.playSound(null, posAhead, ChristmasSounds.SANTA_ELF_ARRIVAL.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);

            SantaElfEvent.Summon elfSummonEvent = new SantaElfEvent.Summon(santaElfEntity, player);
            MinecraftForge.EVENT_BUS.post(elfSummonEvent);
        }

        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return ITEM_USE_DURATION;
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.LEGENDARY;
    }

    @Override
    public boolean isFoil(ItemStack p_77636_1_) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        CompoundTag nbt = itemStack.getTag();

        if (nbt != null && world != null) {
            long timeRemaining = nbt.getLong("NextUseTime") - world.getGameTime();

            if (timeRemaining > 0) {
                MutableComponent textComponent =
                        new TranslatableComponent(
                                "item.happyholidays."+ ITEM_ID +".cooldown",
                                StringUtils.convertTicksToString(timeRemaining)
                        );
                textComponent.withStyle(ChatFormatting.GRAY);
                textComponents.add(textComponent);
            }
        }
    }
}
