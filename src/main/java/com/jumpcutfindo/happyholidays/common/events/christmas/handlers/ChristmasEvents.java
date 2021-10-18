package com.jumpcutfindo.happyholidays.common.events.christmas.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarBlockEntity;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarHelper;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.AngrySantaEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.ChristmasStarEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.GingerbreadConversionEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.GrinchEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.MusicBoxEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaElfEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.StockingEvent;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.ChristmasFoodItem;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEffects;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasParticles;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasTriggers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
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
        if (event.getEntity() instanceof Player && event.getItem().isEdible()) {
            Player playerEntity = (Player) event.getEntity();
            MobEffectInstance effectInstance = playerEntity.getEffect(ChristmasEffects.SPIRIT_OF_CHRISTMAS_EFFECT.get());

            if (effectInstance != null) {
                ItemStack foodItemStack = event.getItem();

                int effectAmplifier = effectInstance.getAmplifier();
                int nutritionValue = foodItemStack.getItem().getFoodProperties().getNutrition();

                if (ChristmasFoodItem.isChristmasFood(foodItemStack)) {
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
        if (event.getEntity() instanceof Player && ChristmasBlocks.isInfluencedByStar(event.getPlacedBlock().getBlock())) {
            Player playerEntity = (Player) event.getEntity();
            ChristmasStarBlockEntity starBlockEntity =
                    ChristmasStarHelper.getStarInfluencingBlock(playerEntity.level, event.getPos());

            if (starBlockEntity != null && starBlockEntity.isPosAffected(event.getPos())) {
                // Block is under influence of a star
                BlockPos placedBlockPos = event.getBlockSnapshot().getPos();

                int particleCount = starBlockEntity.getCurrentTier() + (starBlockEntity.isBonusActive() ? 1 : 0);

                for (int i = 0; i < starBlockEntity.getCurrentTier(); i++) {
                    double d0 = (double)(playerEntity.getRandom().nextFloat() * 0.1F) + 0.25D;
                    double d1 = (double)(playerEntity.getRandom().nextFloat() * 0.1F) + 0.25D;
                    double d2 = (double)(playerEntity.getRandom().nextFloat() * 0.1F) + 0.25D;

                    SimpleParticleType particleType =
                            playerEntity.getRandom().nextBoolean() ? ChristmasParticles.CHRISTMAS_MEDIUM_RED_PARTICLE.get() : ChristmasParticles.CHRISTMAS_MEDIUM_GREEN_PARTICLE.get();

                    ((ServerLevel) playerEntity.level).sendParticles(particleType,
                            placedBlockPos.getX() + 0.5D,
                            placedBlockPos.getY() + 0.5D,
                            placedBlockPos.getZ() + 0.5D, 1, d0, d1, d2, 0.0D);
                }

                ((ServerLevel) playerEntity.level).playSound(null, event.getPos(), ChristmasSounds.CHRISTMAS_STAR_BLOCK_PLACE.get(),
                        SoundSource.NEUTRAL, 1.0f, 1.0f);
            }
        }
    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        ChristmasStarHelper.onWorldLoad(event);
    }

    @SubscribeEvent
    public static void onGingerbreadConversion(GingerbreadConversionEvent event) {
        if (event instanceof GingerbreadConversionEvent.ToSoggy) {
            ChristmasTriggers.GINGERBREAD_MAN_TURN_SOGGY.trigger((ServerPlayer) event.getPlayer());
        } else if (event instanceof GingerbreadConversionEvent.ToDry) {
            ChristmasTriggers.GINGERBREAD_MAN_TURN_DRY.trigger((ServerPlayer) event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onSantaElfInteract(SantaElfEvent event) {
        if (event instanceof SantaElfEvent.Summon) {
            ChristmasTriggers.SANTA_ELF_SUMMON.trigger((ServerPlayer) event.getPlayer());
        } else if (event instanceof SantaElfEvent.Trade) {
            ChristmasTriggers.SANTA_ELF_TRADE.trigger((ServerPlayer) event.getPlayer());
        } else if (event instanceof SantaElfEvent.CompleteRequest) {
            ChristmasTriggers.SANTA_ELF_COMPLETE_REQUEST.trigger((ServerPlayer) event.getPlayer());

            if (((SantaElfEvent.CompleteRequest) event).getTimeTaken() <= 6000) {
                ChristmasTriggers.SANTA_ELF_COMPLETE_REQUEST_QUICK.trigger((ServerPlayer) event.getPlayer());
            }
        }
    }

    @SubscribeEvent
    public static void onGrinchInteract(GrinchEvent event) {
        if (event instanceof GrinchEvent.Encounter) {
            ChristmasTriggers.GRINCH_ENCOUNTER.trigger((ServerPlayer) event.getPlayer());
        } else if (event instanceof GrinchEvent.Appease) {
            ChristmasTriggers.GRINCH_APPEASE.trigger((ServerPlayer) event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onSantaInteract(SantaEvent event) {
        if (event instanceof SantaEvent.AngryDie) {
            ChristmasTriggers.SANTA_ANGRY_DIE.trigger((ServerPlayer) event.getPlayer());

            AngrySantaEntity angrySantaEntity = (AngrySantaEntity) event.getSantaEntity();
            if (!angrySantaEntity.isDamagedByPlayer()) {
                ChristmasTriggers.SANTA_NO_TOUCHY.trigger((ServerPlayer) event.getPlayer());
            }

        } else if (event instanceof SantaEvent.CompleteDropParty) {
            ChristmasTriggers.SANTA_DROP_PARTY_COMPLETE.trigger((ServerPlayer) event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onStockingFill(StockingEvent event) {
        if (event instanceof StockingEvent.Fill) {
            ChristmasTriggers.STOCKING_FILL.trigger((ServerPlayer) event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onChristmasStarInteract(ChristmasStarEvent event) {
        if (event instanceof ChristmasStarEvent.PutOrnament) {
            ChristmasTriggers.STAR_PUT_ORNAMENT.trigger((ServerPlayer) event.getPlayer());
        } else if (event instanceof ChristmasStarEvent.IncreaseTier) {
            ChristmasStarEvent.IncreaseTier increaseTierEvent = (ChristmasStarEvent.IncreaseTier) event;

            if (increaseTierEvent.getTier() == 5) {
                ChristmasTriggers.STAR_MAXED_TIER.trigger((ServerPlayer) event.getPlayer());
            }
        } else if (event instanceof ChristmasStarEvent.SummonSanta) {
            ChristmasTriggers.STAR_SUMMON_SANTA.trigger((ServerPlayer) event.getPlayer());
        } else if (event instanceof ChristmasStarEvent.ReachBonus) {
            ChristmasTriggers.STAR_REACH_BONUS.trigger((ServerPlayer) event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onMusicBoxPlay(MusicBoxEvent event) {
        if (event instanceof MusicBoxEvent.Play) {
            ChristmasTriggers.PLAY_MUSIC_BOX.trigger((ServerPlayer) event.getPlayer());
        }
    }
}
