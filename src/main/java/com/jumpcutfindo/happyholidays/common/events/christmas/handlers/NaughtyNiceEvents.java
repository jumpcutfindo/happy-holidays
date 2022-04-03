package com.jumpcutfindo.happyholidays.common.events.christmas.handlers;


import java.util.Optional;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNice;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.INaughtyNiceHandler;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceAction;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;
import com.jumpcutfindo.happyholidays.common.entity.christmas.ChristmasEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.GingerbreadConversionEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.GrinchEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.NutcrackerEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaElfEvent;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NaughtyNiceEvents {
    /**
     * Replicates the naughty-nice meter levels on player's death.
     * @param event Player death event.
     */
    @SubscribeEvent
    public static void onPlayerDeath(PlayerEvent.Clone event) {
        Player originalPlayer = (Player) event.getOriginal();

        if (event.isWasDeath()) {
            originalPlayer.reviveCaps();
            Player newPlayer = event.getPlayer();

            Optional<INaughtyNiceHandler> oldCapability =
                    originalPlayer.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

            if (oldCapability.isPresent()) {
                Optional<INaughtyNiceHandler> newCapability =
                        newPlayer.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

                if (newCapability.isPresent()) {
                    NaughtyNiceMeter naughtyNiceMeter = (NaughtyNiceMeter) newCapability.get();

                    naughtyNiceMeter.setValue(oldCapability.get().getValue());
                }

                originalPlayer.invalidateCaps();
            }

        }
    }

    @SubscribeEvent
    public static void onKillEntity(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) event.getSource().getEntity();
            LivingEntity killedEntity = event.getEntityLiving();

            MobCategory mobCategory = killedEntity.getClassification(false);

            switch (mobCategory) {
            case CREATURE -> {
                // Killed a non-hostile creature, give naughty points
                if (killedEntity instanceof NutcrackerEntity) {
                    NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.KILL_NUTCRACKER_EVENT);
                    return;
                }
                NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.KILL_PASSIVE_MOB_EVENT);
            }
            case MONSTER -> {
                // Killed a hostile mob, give nice points
                NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.KILL_HOSTILE_MOB_EVENT);
            }
            case MISC -> {
                // Handle Christmas related mobs
                if (killedEntity instanceof SantaElfEntity) {
                    NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.KILL_SANTA_ELF_EVENT);
                    return;
                }
                if (killedEntity instanceof BaseSantaEntity) {
                    // No penalty for killing Santa, because the meter is supposed to reset after the kill
                    return;
                }
                if (killedEntity instanceof ChristmasEntity) {
                    // Penalty for killing any Christmas mobs
                    NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.KILL_CHRISTMAS_MOB_EVENT);
                }
            }
            }
        }
    }

    @SubscribeEvent
    public static void onNutcrackerTame(NutcrackerEvent.Tame tameEvent) {
        if (tameEvent.getLevel().isClientSide()) return;
        Player player = tameEvent.getPlayer();
        NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.TAME_NUTCRACKER_EVENT);
    }

    @SubscribeEvent
    public static void onGrinchAppease(GrinchEvent.Appease appeaseEvent) {
        if (appeaseEvent.getLevel().isClientSide()) return;
        Player player = appeaseEvent.getPlayer();
        NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.APPEASE_GRINCH_EVENT);
    }

    @SubscribeEvent
    public static void onHelpSantaElf(SantaElfEvent.CompleteRequest completeRequestEvent) {
        if (completeRequestEvent.getLevel().isClientSide()) return;
        Player player = completeRequestEvent.getPlayer();
        NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.HELP_SANTA_ELF_EVENT);
    }

    @SubscribeEvent
    public static void onDryGingerbreadMan(GingerbreadConversionEvent.ToDry dryEvent) {
        if (dryEvent.getLevel().isClientSide()) return;
        Player player = dryEvent.getPlayer();
        NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.DRY_GINGERBREAD_MAN_EVENT);
    }

    @SubscribeEvent
    public static void onTameCreature(AnimalTameEvent tameEvent) {
        Player player = tameEvent.getTamer();
        NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.TAME_MOB_EVENT);
    }

    @SubscribeEvent
    public static void onHealCreature(VanillaGameEvent event) {
        if (event.getVanillaEvent().equals(GameEvent.MOB_INTERACT)) {
            Entity causeEntity = event.getCause();
            Player nearestPlayer = event.getLevel().getNearestPlayer(causeEntity, 5.0D);

            if (causeEntity instanceof ZombieVillager) {
                NaughtyNiceMeter.evaluateAction(nearestPlayer, NaughtyNiceAction.CURE_VILLAGER_EVENT);
            } else if (causeEntity.getType().getCategory().isFriendly()) {
                NaughtyNiceMeter.evaluateAction(nearestPlayer, NaughtyNiceAction.HEAL_MOB_EVENT);
            }
        }
    }
}
