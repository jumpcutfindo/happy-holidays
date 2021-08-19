package com.jumpcutfindo.happyholidays.common.events.christmas;


import java.util.Optional;
import java.util.UUID;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNiceHandler;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.INaughtyNiceHandler;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceAction;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceProvider;
import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;
import com.jumpcutfindo.happyholidays.common.registry.EntityRegistry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NaughtyNiceEvents {
    public static final int NICE_KILL_HOSTILE_MOB_POINTS = 10;
    public static final int NICE_HELP_SANTA_ELF_POINTS = 20;
    public static final int NICE_HELP_GRINCH_POINTS = 20;

    public static final int NAUGHTY_KILL_PASSIVE_MOB_POINTS = 5;
    public static final int NAUGHTY_KILL_SANTA_ELF_POINTS = 200;

    /**
     * Replicates the naughty-nice meter levels on player's death.
     * @param event Player death event.
     */
    @SubscribeEvent
    public static void onPlayerDeath(PlayerEvent.Clone event) {
        PlayerEntity originalPlayer = (PlayerEntity) event.getOriginal();
        if (event.isWasDeath()) {
            PlayerEntity newPlayer = event.getPlayer();

            Optional<INaughtyNiceHandler> oldCapability =
                    originalPlayer.getCapability(CapabilityNaughtyNiceHandler.NAUGHTY_NICE_CAPABILITY).resolve();

            if (oldCapability.isPresent()) {
                Optional<INaughtyNiceHandler> newCapability =
                        newPlayer.getCapability(CapabilityNaughtyNiceHandler.NAUGHTY_NICE_CAPABILITY).resolve();

                if (newCapability.isPresent()) {
                    INaughtyNiceHandler naughtyNiceHandler = newCapability.get();

                    naughtyNiceHandler.addNaughty(oldCapability.get().getNaughty());
                    naughtyNiceHandler.addNice(oldCapability.get().getNice());
                }
            }

        }
    }

    @SubscribeEvent
    public static void onKillEntity(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getSource().getEntity();
            LivingEntity killedEntity = event.getEntityLiving();

            if (killedEntity.getClassification(false) != EntityClassification.MONSTER) {
                // Killed a non-hostile mob, give naughty points
                if (killedEntity instanceof SantaElfEntity) NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.KILL_SANTA_ELF_EVENT);
                else NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.KILL_PASSIVE_MOB_EVENT);
            } else {
                NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.KILL_HOSTILE_MOB_EVENT);
            }
        }
    }
}
