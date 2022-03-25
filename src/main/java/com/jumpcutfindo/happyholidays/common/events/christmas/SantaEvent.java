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

    public static class SoundChange extends SantaEvent {
        private final boolean isPlaying;

        public SoundChange(BaseSantaEntity santaEntity, Player playerEntity, boolean isPlaying) {
            super(santaEntity, playerEntity);
            this.isPlaying = isPlaying;
        }

        public boolean isPlaying() {
            return isPlaying;
        }
    }

    public static class StartDropParty extends SantaEvent {
        public StartDropParty(BaseSantaEntity santaEntity, Player playerEntity) {
            super(santaEntity, playerEntity);
        }
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
