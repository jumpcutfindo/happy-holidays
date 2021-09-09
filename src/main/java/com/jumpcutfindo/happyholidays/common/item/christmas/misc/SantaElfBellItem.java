package com.jumpcutfindo.happyholidays.common.item.christmas.misc;

import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaElfEvent;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.sound.christmas.SantaBellSound;
import com.jumpcutfindo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class SantaElfBellItem extends ChristmasItem {
    public static final String ITEM_ID = "santa_elf_bell";

    public static final int ITEM_USE_DURATION = 35;
    public static final int ITEM_COOLDOWN = 24000;

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(1)
                    .defaultDurability(ITEM_COOLDOWN);

    private SantaBellSound bellSound;

    public SantaElfBellItem() {
        super(ITEM_ID, ITEM_PROPERTIES);
        this.christmasRarity = ChristmasRarity.LEGENDARY;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        CompoundNBT nbt = player.getItemInHand(hand).getTag();
        long nextUseTime = nbt.getLong("NextUseTime");
        if (world.getGameTime() > nextUseTime) {
            player.startUsingItem(hand);

            player.playSound(ChristmasSounds.SANTA_ELF_BELL.get(), 1.0F, 1.0F);
            return ActionResult.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
        } else {
            long timeRemaining = nbt.getLong("NextUseTime") - world.getGameTime();
            IFormattableTextComponent chatComponent =
                    new TranslationTextComponent("chat.happyholidays." + ITEM_ID + ".not_ready",
                            HappyHolidaysUtils.convertTicksToString(timeRemaining));
            chatComponent.withStyle(TextFormatting.RED);
            player.displayClientMessage(chatComponent, true);
            return ActionResult.fail(player.getItemInHand(hand));
        }

    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livingEntity) {
        if (livingEntity instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) livingEntity;
            BlockPos playerPos = playerEntity.blockPosition();

            CompoundNBT nbt = stack.getOrCreateTag();
            nbt.putLong("NextUseTime", world.getGameTime() + (long) ITEM_COOLDOWN);

            BlockPos posAhead = HappyHolidaysUtils.getPosInFront(playerEntity.getDirection(), playerPos, 4.0D);

            double spawnX, spawnY, spawnZ;
            spawnX = posAhead.getX();
            spawnY = posAhead.getY();
            spawnZ = posAhead.getZ();

            SantaElfEntity santaElfEntity = ChristmasEntities.SANTA_ELF.get().create(world);
            santaElfEntity.moveTo(spawnX, spawnY, spawnZ, 0.0F, 0.0F);
            world.addFreshEntity(santaElfEntity);

            world.addParticle(ParticleTypes.LARGE_SMOKE, spawnX, spawnY, spawnZ, 0.0F, 0.0F, 0.0F);
            world.playSound(null, posAhead, ChristmasSounds.SANTA_ELF_ARRIVAL.get(), SoundCategory.NEUTRAL, 1.0F, 1.0F);

            SantaElfEvent.Summon elfSummonEvent = new SantaElfEvent.Summon(playerEntity, santaElfEntity);
            MinecraftForge.EVENT_BUS.post(elfSummonEvent);
        }

        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return ITEM_USE_DURATION;
    }

    @Override
    public boolean isFoil(ItemStack p_77636_1_) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        CompoundNBT nbt = itemStack.getTag();

        if (nbt != null && world != null) {
            long timeRemaining = nbt.getLong("NextUseTime") - world.getGameTime();

            if (timeRemaining > 0) {
                IFormattableTextComponent textComponent =
                        new TranslationTextComponent(
                                "item.happyholidays."+ ITEM_ID +".cooldown",
                                HappyHolidaysUtils.convertTicksToString(timeRemaining)
                        );
                textComponent.withStyle(TextFormatting.GRAY);
                textComponents.add(textComponent);
            }
        }
    }
}
