package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.AngrySantaEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class SantaEvent extends Event {
    final BaseSantaEntity santaEntity;
    final PlayerEntity playerEntity;

    public SantaEvent(BaseSantaEntity santaEntity, PlayerEntity playerEntity) {
        this.santaEntity = santaEntity;
        this.playerEntity = playerEntity;
    }

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public BaseSantaEntity getSantaEntity() {
        return santaEntity;
    }

    public static class CompleteDropParty extends SantaEvent {
        public CompleteDropParty(BaseSantaEntity santaEntity, PlayerEntity playerEntity) {
            super(santaEntity, playerEntity);
        }
    }

    public static class AngryDie extends SantaEvent {
        public AngryDie(AngrySantaEntity santaEntity, PlayerEntity playerEntity) {
            super(santaEntity, playerEntity);
        }
    }
}
