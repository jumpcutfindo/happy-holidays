package com.jumpcutfindo.happyholidays.common.events.christmas;


import java.util.Optional;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNice;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.INaughtyNiceHandler;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceAction;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;
import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
        PlayerEntity originalPlayer = (PlayerEntity) event.getOriginal();
        if (event.isWasDeath()) {
            PlayerEntity newPlayer = event.getPlayer();

            Optional<INaughtyNiceHandler> oldCapability =
                    originalPlayer.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

            if (oldCapability.isPresent()) {
                Optional<INaughtyNiceHandler> newCapability =
                        newPlayer.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

                if (newCapability.isPresent()) {
                    NaughtyNiceMeter naughtyNiceMeter = (NaughtyNiceMeter) newCapability.get();

                    naughtyNiceMeter.setValue(oldCapability.get().getValue());
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
                if (!(killedEntity instanceof BaseSantaEntity)) NaughtyNiceMeter.evaluateAction(player,
                        NaughtyNiceAction.KILL_HOSTILE_MOB_EVENT);
            }
        }
    }
}
