package com.bayobayobayo.happyholidays.common.item.christmas.misc;

import com.bayobayobayo.happyholidays.common.entity.christmas.SantaElfEntity;
import com.bayobayobayo.happyholidays.common.handlers.modules.ModuleHandler;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasRarity;
import com.bayobayobayo.happyholidays.common.registry.EntityRegistry;
import com.bayobayobayo.happyholidays.common.registry.SoundRegistry;
import com.bayobayobayo.happyholidays.common.sound.christmas.SantaBellSound;
import com.bayobayobayo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class SantaElfBellItem extends ChristmasItem {
    public static final String ITEM_ID = "santa_elf_bell";

    public static final int ITEM_USE_DURATION = 35;
    public static final int ITEM_COOLDOWN = 24000;

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP)
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

            player.playSound(SoundRegistry.SANTA_ELF_BELL.get(), 1.0F, 1.0F);
            return ActionResult.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
        } else {
            TextComponent chatComponent = new TranslationTextComponent("chat.happyholidays.christmas.santa_elf_bell"
                    + ".not_ready");
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

            SantaElfEntity santaElfEntity = EntityRegistry.SANTA_ELF.get().create(world);
            santaElfEntity.moveTo(spawnX, spawnY, spawnZ, 0.0F, 0.0F);
            world.addFreshEntity(santaElfEntity);

            world.addParticle(ParticleTypes.LARGE_SMOKE, spawnX, spawnY, spawnZ, 0.0F, 0.0F, 0.0F);
            world.playSound(null, posAhead, SoundRegistry.SANTA_ELF_ARRIVAL.get(), SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }

        return stack;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i,
                              boolean b) {
        super.inventoryTick(itemStack, world, entity, i, b);

        if (entity instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) entity;

            CompoundNBT nbt = itemStack.getTag();
            if (nbt != null) {
                long nextUseTime = nbt.getLong("NextUseTime");
                itemStack.setDamageValue((int) (Math.min(ITEM_COOLDOWN, nextUseTime - world.getGameTime())));
            }
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return ITEM_USE_DURATION;
    }
}
