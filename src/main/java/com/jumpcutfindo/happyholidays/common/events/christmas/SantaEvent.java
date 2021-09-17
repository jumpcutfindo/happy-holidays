package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.AngrySantaEntity;

import net.minecraft.world.entity.player.Player;

public class SantaEvent extends ChristmasEvent {
    final BaseSantaEntity santaEntity;

    public SantaEvent(BaseSantaEntity santaEntity, Player playerEntity) {
        super(playerEntity);
        this.santaEntity = santaEntity;
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
