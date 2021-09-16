package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.AngrySantaEntity;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Event;

public class SantaEvent extends Event {
    final BaseSantaEntity santaEntity;
    final Player playerEntity;

    public SantaEvent(BaseSantaEntity santaEntity, Player playerEntity) {
        this.santaEntity = santaEntity;
        this.playerEntity = playerEntity;
    }

    public Player getPlayerEntity() {
        return playerEntity;
    }

    public BaseSantaEntity getSantaEntity() {
        return santaEntity;
    }

    public static class CompleteDropParty extends SantaEvent {
        public CompleteDropParty(BaseSantaEntity santaEntity, Player playerEntity) {
            super(santaEntity, playerEntity);
        }
    }

    public static class AngryDie extends SantaEvent {
        public AngryDie(AngrySantaEntity santaEntity, Player playerEntity) {
            super(santaEntity, playerEntity);
        }
    }
}
