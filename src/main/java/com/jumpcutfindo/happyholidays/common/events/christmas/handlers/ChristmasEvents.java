package com.jumpcutfindo.happyholidays.common.events.christmas.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.ChristmasStarBlockEntity;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.ChristmasStarHelper;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.AngrySantaEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.ChristmasStarEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.GingerbreadConversionEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.GrinchEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.MusicBoxEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.NutcrackerEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.PatrolOrdersEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaElfEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.SnowGlobeEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.StockingEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.SwaggerStickEvent;
import com.jumpcutfindo.happyholidays.common.registry.HappyHolidaysStats;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEffects;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasParticles;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasStats;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasTriggers;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
            MobEffectInstance effectInstance = playerEntity.getEffect(ChristmasEffects.SPIRIT_OF_CHRISTMAS.get());

            if (effectInstance != null) {
                ItemStack foodItemStack = event.getItem();

                int effectAmplifier = effectInstance.getAmplifier();
                int nutritionValue = foodItemStack.getItem().getFoodProperties().getNutrition();

                if (ChristmasItems.isFoodItem(foodItemStack)) {
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
        if (ChristmasBlocks.isInfluencedByStar(event.getPlacedBlock())) onStarAffectedBlockPlaced(event);
    }

    public static void onStarAffectedBlockPlaced(BlockEvent.EntityPlaceEvent event) {
        if (event.getEntity() instanceof Player playerEntity) {
            ChristmasStarBlockEntity starBlockEntity =
                    ChristmasStarHelper.getStarInfluencingBlock(playerEntity.level, event.getPos());

            if (starBlockEntity != null && starBlockEntity.isPosAffected(event.getPos())) {
                // Block is under influence of a star
                BlockPos placedBlockPos = event.getBlockSnapshot().getPos();

                for (int i = 0; i < starBlockEntity.getCurrentTier(); i++) {
                    double d0 = (double)(playerEntity.getRandom().nextFloat() * 0.1F) + 0.25D;
                    double d1 = (double)(playerEntity.getRandom().nextFloat() * 0.1F) + 0.25D;
                    double d2 = (double)(playerEntity.getRandom().nextFloat() * 0.1F) + 0.25D;

                    SimpleParticleType particleType =
                            playerEntity.getRandom().nextBoolean() ? ChristmasParticles.CHRISTMAS_MEDIUM_RED.get() : ChristmasParticles.CHRISTMAS_MEDIUM_GREEN.get();

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
        if (!(event.getPlayer() instanceof ServerPlayer)) return;

        if (event instanceof GingerbreadConversionEvent.ToSoggy) {
            ChristmasTriggers.GINGERBREAD_MAN_TURN_SOGGY.trigger((ServerPlayer) event.getPlayer());
        } else if (event instanceof GingerbreadConversionEvent.ToDry) {
            ServerPlayer serverPlayer = (ServerPlayer) event.getPlayer();
            HappyHolidaysStats.awardStat(serverPlayer, ChristmasStats.GINGERBREAD_MEN_DRIED);

            ChristmasTriggers.GINGERBREAD_MAN_TURN_DRY.trigger(serverPlayer);

            if (HappyHolidaysStats.valueOf(serverPlayer, ChristmasStats.GINGERBREAD_MEN_DRIED) >= 200) {
                ChristmasTriggers.GINGERBREAD_MAN_DRY_CHALLENGE.trigger(serverPlayer);
            }
        }
    }

    @SubscribeEvent
    public static void onSantaElfInteract(SantaElfEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer)) return;

        if (event instanceof SantaElfEvent.Summon) {
            ChristmasTriggers.SANTA_ELF_SUMMON.trigger((ServerPlayer) event.getPlayer());
        } else if (event instanceof SantaElfEvent.Trade) {
            ChristmasTriggers.SANTA_ELF_TRADE.trigger((ServerPlayer) event.getPlayer());
        } else if (event instanceof SantaElfEvent.CompleteRequest) {
            ServerPlayer serverPlayer = (ServerPlayer) event.getPlayer();
            HappyHolidaysStats.awardStat(serverPlayer, ChristmasStats.SANTA_ELVES_HELPED);

            ChristmasTriggers.SANTA_ELF_COMPLETE_REQUEST.trigger(serverPlayer);

            if (((SantaElfEvent.CompleteRequest) event).getTimeTaken() <= 6000) {
                ChristmasTriggers.SANTA_ELF_COMPLETE_REQUEST_QUICK.trigger(serverPlayer);
            }

            if (HappyHolidaysStats.valueOf(serverPlayer, ChristmasStats.SANTA_ELVES_HELPED) >= 50) {
                ChristmasTriggers.SANTA_ELF_HELP_CHALLENGE.trigger(serverPlayer);
            }
        }
    }

    @SubscribeEvent
    public static void onGrinchInteract(GrinchEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer)) return;

        if (event instanceof GrinchEvent.Encounter) {
            ChristmasTriggers.GRINCH_ENCOUNTER.trigger((ServerPlayer) event.getPlayer());
        } else if (event instanceof GrinchEvent.Appease) {
            ServerPlayer serverPlayer = (ServerPlayer) event.getPlayer();
            HappyHolidaysStats.awardStat(serverPlayer, ChristmasStats.GRINCHES_APPEASED);

            ChristmasTriggers.GRINCH_APPEASE.trigger(serverPlayer);

            if (HappyHolidaysStats.valueOf(serverPlayer, ChristmasStats.GRINCHES_APPEASED) >= 50) {
                ChristmasTriggers.GRINCH_APPEASE_CHALLENGE.trigger(serverPlayer);
            }
        }
    }

    @SubscribeEvent
    public static void onSantaInteract(SantaEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer)) return;

        if (event instanceof SantaEvent.AngryDie) {
            ServerPlayer serverPlayer = (ServerPlayer) event.getPlayer();
            AngrySantaEntity angrySantaEntity = (AngrySantaEntity) event.getSantaEntity();

            HappyHolidaysStats.awardStat(serverPlayer, ChristmasStats.ANGRY_SANTAS_DEFEATED);
            ChristmasTriggers.SANTA_ANGRY_DIE.trigger(serverPlayer);

            if (!angrySantaEntity.isDamagedByPlayer()) {
                ChristmasTriggers.SANTA_NO_TOUCHY.trigger((ServerPlayer) event.getPlayer());
            }

            if (HappyHolidaysStats.valueOf(serverPlayer, ChristmasStats.ANGRY_SANTAS_DEFEATED) >= 10) {
                ChristmasTriggers.SANTA_ANGRY_CHALLENGE.trigger(serverPlayer);
            }

        } else if (event instanceof SantaEvent.CompleteDropParty) {
            ServerPlayer serverPlayer = (ServerPlayer) event.getPlayer();

            HappyHolidaysStats.awardStat(serverPlayer, ChristmasStats.HAPPY_SANTAS_RECEIVED);
            ChristmasTriggers.SANTA_DROP_PARTY_COMPLETE.trigger(serverPlayer);

            if (HappyHolidaysStats.valueOf(serverPlayer, ChristmasStats.HAPPY_SANTAS_RECEIVED) >= 10) {
                ChristmasTriggers.SANTA_HAPPY_CHALLENGE.trigger(serverPlayer);
            }
        }
    }

    @SubscribeEvent
    public static void onStockingInteract(StockingEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer)) return;

        if (event instanceof StockingEvent.Fill) {
            ChristmasTriggers.STOCKING_FILL.trigger((ServerPlayer) event.getPlayer());
        } else if (event instanceof StockingEvent.Upgrade) {
            ChristmasTriggers.STOCKING_UPGRADE.trigger((ServerPlayer) event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onChristmasStarInteract(ChristmasStarEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer)) return;

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
        if (!(event.getPlayer() instanceof ServerPlayer)) return;

        if (event instanceof MusicBoxEvent.Play) {
            ChristmasTriggers.PLAY_MUSIC_BOX.trigger((ServerPlayer) event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onSnowGlobeInteract(SnowGlobeEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer)) return;

        if (event instanceof SnowGlobeEvent.Use) {
            ServerPlayer serverPlayer = (ServerPlayer) event.getPlayer();

            HappyHolidaysStats.awardStat(serverPlayer, ChristmasStats.USE_SNOW_GLOBE);

            ChristmasTriggers.SNOW_GLOBE_USE.trigger(serverPlayer);

            if (HappyHolidaysStats.valueOf(serverPlayer, ChristmasStats.USE_SNOW_GLOBE) >= 50) {
                ChristmasTriggers.SNOW_GLOBE_USE_CHALLENGE.trigger(serverPlayer);
            }
        }
    }

    @SubscribeEvent
    public static void onPatrolOrdersInteract(PatrolOrdersEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer serverPlayer)) return;

        if (event instanceof PatrolOrdersEvent.Complete) {
            ChristmasTriggers.PATROL_ORDERS_COMPLETE.trigger(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onSwaggerStickInteract(SwaggerStickEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer serverPlayer)) return;

        if (event instanceof SwaggerStickEvent.Held) {
            ChristmasTriggers.SWAGGER_STICK_HELD.trigger(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onNutcrackerInteract(NutcrackerEvent event) {
        if (!((event.getPlayer()) instanceof ServerPlayer serverPlayer)) return;

        if (event instanceof NutcrackerEvent.Encounter) {
            ChristmasTriggers.NUTCRACKER_ENCOUNTER.trigger(serverPlayer);
        } else if (event instanceof NutcrackerEvent.Tame) {
            HappyHolidaysStats.awardStat(serverPlayer, ChristmasStats.NUTCRACKERS_TAMED);

            ChristmasTriggers.NUTCRACKER_TAME.trigger(serverPlayer);

            if (HappyHolidaysStats.valueOf(serverPlayer, ChristmasStats.NUTCRACKERS_TAMED) >= 20) {
                ChristmasTriggers.NUTCRACKER_TAME_CHALLENGE.trigger(serverPlayer);
            }
        } else if (event instanceof NutcrackerEvent.ReceiveCompleteOrders) {
            ChristmasTriggers.NUTCRACKER_RECEIVE_COMPLETE_ORDERS.trigger(serverPlayer);
        } else if (event instanceof NutcrackerEvent.ReceiveSpecialWalnuts) {
            ChristmasTriggers.NUTCRACKER_RECEIVE_SPECIAL_WALNUT.trigger(serverPlayer);
        } else if (event instanceof NutcrackerEvent.ReceiveArmor) {
            ChristmasTriggers.NUTCRACKER_RECEIVE_ARMOR.trigger(serverPlayer);
        } else if (event instanceof NutcrackerEvent.FullOfExplosives) {
            ChristmasTriggers.NUTCRACKER_EXPLOSIVE_INVENTORY.trigger(serverPlayer);
        } else if (event instanceof NutcrackerEvent.HighArmorRating) {
            ChristmasTriggers.NUTCRACKER_HIGH_ARMOR_RATING.trigger(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onNutcrackerKillsMob(LivingDeathEvent event) {
        DamageSource source = event.getSource();
        if (source.getEntity() != null && source.getEntity() instanceof NutcrackerEntity nutcracker) {
            nutcracker.tryDroppingOrnament();

            if (nutcracker.getOwner() instanceof ServerPlayer serverPlayer) {
                HappyHolidaysStats.awardStat(serverPlayer, ChristmasStats.NUTCRACKER_KILLS);
                ChristmasTriggers.NUTCRACKER_KILLS_MOB.trigger(serverPlayer);

                if (HappyHolidaysStats.valueOf(serverPlayer, ChristmasStats.NUTCRACKER_KILLS) >= 500) {
                    ChristmasTriggers.NUTCRACKER_KILL_CHALLENGE.trigger(serverPlayer);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onGiftPickup(PlayerEvent.ItemPickupEvent event) {
        if (event.getStack().is(ChristmasTags.Items.GIFTS)) {
            ServerPlayer serverPlayer = (ServerPlayer) event.getPlayer();
            StatType<?> temp = Stats.CUSTOM;
            HappyHolidaysStats.awardStat(serverPlayer, ChristmasStats.GIFTS_RECEIVED);
        }
    }
}
