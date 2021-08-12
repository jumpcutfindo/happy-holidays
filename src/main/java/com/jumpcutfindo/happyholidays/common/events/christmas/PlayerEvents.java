package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.ChristmasFoodItem;
import com.jumpcutfindo.happyholidays.common.registry.EffectRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ParticleRegistry;
import com.jumpcutfindo.happyholidays.common.registry.SoundRegistry;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEvents {

    /**
     * Handles the event where food is eaten under the influence of Christmas Spirit buff.
     * @param event Food eating event.
     */
    @SubscribeEvent
    public static void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof PlayerEntity && event.getItem().isEdible()) {
            PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
            EffectInstance effectInstance = playerEntity.getEffect(EffectRegistry.SPIRIT_OF_CHRISTMAS_EFFECT.get());

            if (effectInstance != null) {
                ItemStack foodItemStack = event.getItem();

                int effectAmplifier = effectInstance.getAmplifier();
                int nutritionValue = foodItemStack.getItem().getFoodProperties().getNutrition();

                if (ChristmasFoodItem.isChristmasFood(foodItemStack.getStack())) {
                    playerEntity.getFoodData().eat(
                            (int) ((effectAmplifier + 1) * 0.2 * nutritionValue),
                            0.0f
                    );
                } else {
                    playerEntity.getFoodData().eat(
                            (int) ((effectAmplifier + 1) * 0.1 * nutritionValue),
                            0.0f
                    );
                }

            }
        }
    }

    @SubscribeEvent
    public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event) {
        if (event.getEntity() instanceof PlayerEntity && ChristmasBlock.isInfluencedByStar(event.getPlacedBlock().getBlock())) {
            PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
            ChristmasStarTileEntity starTileEntity =
                    ChristmasStarTileEntity.getNearestStarToBlock(playerEntity.level, event.getPos());

            if (starTileEntity != null && starTileEntity.isBlockAffected(event.getPos())) {
                // Block is under influence of a star
                BlockPos placedBlockPos = event.getBlockSnapshot().getPos();

                for (int i = 0; i < 4; i++) {
                    double d0 = (double)(playerEntity.getRandom().nextFloat() * 0.1F) + 0.25D;
                    double d1 = (double)(playerEntity.getRandom().nextFloat() * 0.1F) + 0.25D;
                    double d2 = (double)(playerEntity.getRandom().nextFloat() * 0.1F) + 0.25D;

                    BasicParticleType particleType =
                            playerEntity.getRandom().nextBoolean() ? ParticleRegistry.CHRISTMAS_RED_PARTICLE.get() : ParticleRegistry.CHRISTMAS_GREEN_PARTICLE.get();

                    ((ServerWorld) playerEntity.level).sendParticles(particleType,
                            placedBlockPos.getX() + 0.5D,
                            placedBlockPos.getY() + 0.5D,
                            placedBlockPos.getZ() + 0.5D, 1, d0, d1, d2, 0.0D);
                }

                ((ServerWorld) playerEntity.level).playSound(null, event.getPos(), SoundRegistry.CHRISTMAS_STAR_BLOCK_PLACE.get(),
                        SoundCategory.NEUTRAL, 1.0f, 1.0f);
            }
        }
    }
}
