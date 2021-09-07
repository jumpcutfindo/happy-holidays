package com.jumpcutfindo.happyholidays.common.events.christmas.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.AngrySantaEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.ChristmasStarEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.GingerbreadConversionEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.GrinchEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaElfEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.StockingEvent;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.ChristmasFoodItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.SheetMusicItem;
import com.jumpcutfindo.happyholidays.common.registry.BlockRegistry;
import com.jumpcutfindo.happyholidays.common.registry.EffectRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ParticleRegistry;
import com.jumpcutfindo.happyholidays.common.registry.SoundRegistry;
import com.jumpcutfindo.happyholidays.common.registry.TriggerRegistry;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChristmasEvents {

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
                    ChristmasStarTileEntity.getStarInfluencingBlock(playerEntity.level, event.getPos());

            if (starTileEntity != null && starTileEntity.isPosAffected(event.getPos())) {
                // Block is under influence of a star
                BlockPos placedBlockPos = event.getBlockSnapshot().getPos();

                int particleCount = starTileEntity.getCurrentTier() + (starTileEntity.isBonusActive() ? 1 : 0);

                for (int i = 0; i < starTileEntity.getCurrentTier(); i++) {
                    double d0 = (double)(playerEntity.getRandom().nextFloat() * 0.1F) + 0.25D;
                    double d1 = (double)(playerEntity.getRandom().nextFloat() * 0.1F) + 0.25D;
                    double d2 = (double)(playerEntity.getRandom().nextFloat() * 0.1F) + 0.25D;

                    BasicParticleType particleType =
                            playerEntity.getRandom().nextBoolean() ? ParticleRegistry.CHRISTMAS_MEDIUM_RED_PARTICLE.get() : ParticleRegistry.CHRISTMAS_MEDIUM_GREEN_PARTICLE.get();

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

    @SubscribeEvent
    public static void onItemInteract(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity playerEntity = (ServerPlayerEntity) event.getEntity();
            ItemStack itemInHand = playerEntity.getItemInHand(event.getHand());
            BlockState blockState = playerEntity.level.getBlockState(event.getPos());

            // Trigger music box playing trigger
            if (itemInHand.getItem() instanceof SheetMusicItem && blockState.is(BlockRegistry.MUSIC_BOX_BLOCK.get())) {
                TriggerRegistry.CHRISTMAS_PLAY_MUSIC_BOX.trigger(playerEntity);
            }
        }
    }

    @SubscribeEvent
    public static void onGingerbreadConversion(GingerbreadConversionEvent event) {
        if (event instanceof GingerbreadConversionEvent.ToSoggy) {
            TriggerRegistry.CHRISTMAS_GINGERBREAD_MAN_TURN_SOGGY.trigger((ServerPlayerEntity) event.getPlayerEntity());
        } else if (event instanceof GingerbreadConversionEvent.ToDry) {
            TriggerRegistry.CHRISTMAS_GINGERBREAD_MAN_TURN_DRY.trigger((ServerPlayerEntity) event.getPlayerEntity());
        }
    }

    @SubscribeEvent
    public static void onSantaElfInteract(SantaElfEvent event) {
        if (event instanceof SantaElfEvent.Summon) {
            TriggerRegistry.CHRISTMAS_SANTA_ELF_SUMMON.trigger((ServerPlayerEntity) event.getPlayerEntity());
        } else if (event instanceof SantaElfEvent.Trade) {
            TriggerRegistry.CHRISTMAS_SANTA_ELF_TRADE.trigger((ServerPlayerEntity) event.getPlayerEntity());
        } else if (event instanceof SantaElfEvent.CompleteRequest) {
            TriggerRegistry.CHRISTMAS_SANTA_ELF_COMPLETE_REQUEST.trigger((ServerPlayerEntity) event.getPlayerEntity());

            if (((SantaElfEvent.CompleteRequest) event).getTimeTaken() <= 6000) {
                TriggerRegistry.CHRISTMAS_SANTA_ELF_COMPLETE_REQUEST_QUICK.trigger((ServerPlayerEntity) event.getPlayerEntity());
            }
        }
    }

    @SubscribeEvent
    public static void onGrinchInteract(GrinchEvent event) {
        if (event instanceof GrinchEvent.Encounter) {
            TriggerRegistry.CHRISTMAS_GRINCH_ENCOUNTER.trigger((ServerPlayerEntity) event.getPlayerEntity());
        } else if (event instanceof GrinchEvent.Appease) {
            TriggerRegistry.CHRISTMAS_GRINCH_APPEASE.trigger((ServerPlayerEntity) event.getPlayerEntity());
        }
    }

    @SubscribeEvent
    public static void onSantaInteract(SantaEvent event) {
        if (event instanceof SantaEvent.AngryDie) {
            TriggerRegistry.CHRISTMAS_SANTA_ANGRY_DIE.trigger((ServerPlayerEntity) event.getPlayerEntity());

            AngrySantaEntity angrySantaEntity = (AngrySantaEntity) event.getSantaEntity();
            if (!angrySantaEntity.isDamagedByPlayer()) {
                TriggerRegistry.CHRISTMAS_SANTA_NO_TOUCHY.trigger((ServerPlayerEntity) event.getPlayerEntity());
            }

        } else if (event instanceof SantaEvent.CompleteDropParty) {
            TriggerRegistry.CHRISTMAS_SANTA_DROP_PARTY_COMPLETE.trigger((ServerPlayerEntity) event.getPlayerEntity());
        }
    }

    @SubscribeEvent
    public static void onStockingFill(StockingEvent event) {
        if (event instanceof StockingEvent.Fill) {
            TriggerRegistry.CHRISTMAS_STOCKING_FILL.trigger((ServerPlayerEntity) event.getPlayerEntity());
        }
    }

    @SubscribeEvent
    public static void onChristmasStarInteract(ChristmasStarEvent event) {
        if (event instanceof ChristmasStarEvent.PutOrnament) {
            TriggerRegistry.CHRISTMAS_STAR_PUT_ORNAMENT.trigger((ServerPlayerEntity) event.getPlayerEntity());
        } else if (event instanceof ChristmasStarEvent.IncreaseTier) {
            ChristmasStarEvent.IncreaseTier increaseTierEvent = (ChristmasStarEvent.IncreaseTier) event;

            if (increaseTierEvent.getTier() == 5) {
                TriggerRegistry.CHRISTMAS_STAR_MAXED_TIER.trigger((ServerPlayerEntity) event.getPlayerEntity());
            }
        } else if (event instanceof ChristmasStarEvent.SummonSanta) {
            TriggerRegistry.CHRISTMAS_STAR_SUMMON_SANTA.trigger((ServerPlayerEntity) event.getPlayerEntity());
        } else if (event instanceof ChristmasStarEvent.ReachBonus) {
            TriggerRegistry.CHRISTMAS_STAR_REACH_BONUS.trigger((ServerPlayerEntity) event.getPlayerEntity());
        }
    }
}
