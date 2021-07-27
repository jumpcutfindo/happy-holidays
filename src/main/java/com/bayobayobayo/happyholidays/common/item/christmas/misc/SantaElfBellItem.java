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
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SantaElfBellItem extends ChristmasItem {
    public static final String ITEM_ID = "santa_elf_bell";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(1);

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
        player.startUsingItem(hand);

        player.playSound(SoundRegistry.SANTA_ELF_BELL.get(), 1.0F, 1.0F);

        return ActionResult.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livingEntity) {
        if (livingEntity instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) livingEntity;
            BlockPos playerPos = playerEntity.blockPosition();

            playerEntity.getCooldowns().addCooldown(this, 24000);
            BlockPos posAhead = HappyHolidaysUtils.getPosInFront(playerEntity.getDirection(), playerPos, 4.0D);

            double spawnX, spawnY, spawnZ;
            spawnX = posAhead.getX();
            spawnY = posAhead.getY();
            spawnZ = posAhead.getZ();

            SantaElfEntity santaElfEntity = EntityRegistry.SANTA_ELF.get().create(world);
            santaElfEntity.moveTo(spawnX, spawnY, spawnZ, 0.0F, 0.0F);
            world.addFreshEntity(santaElfEntity);

            world.addParticle(ParticleTypes.LARGE_SMOKE, spawnX, spawnY, spawnZ, 0.0F, 0.0F, 0.0F);
            world.playSound(null, posAhead, SoundEvents.EGG_THROW, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }

        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 35;
    }
}
