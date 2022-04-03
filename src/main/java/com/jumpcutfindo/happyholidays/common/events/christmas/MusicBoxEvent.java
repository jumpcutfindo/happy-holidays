package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.block.entity.christmas.MusicBoxBlockEntity;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.ChristmasMusic;

import net.minecraft.world.entity.player.Player;

public class MusicBoxEvent extends ChristmasEvent {
    private final MusicBoxBlockEntity musicBox;
    private final ChristmasMusic music;

    public MusicBoxEvent(MusicBoxBlockEntity musicBox, ChristmasMusic music, Player player) {
        super(player);
        this.musicBox = musicBox;
        this.music = music;
    }

    public MusicBoxBlockEntity getMusicBox() {
        return musicBox;
    }

    public ChristmasMusic getMusic() {
        return music;
    }

    public static class Play extends MusicBoxEvent {
        public Play(MusicBoxBlockEntity musicBox, ChristmasMusic music, Player player) {
            super(musicBox, music, player);
        }
    }

    public static class Stop extends MusicBoxEvent {
        public Stop(MusicBoxBlockEntity musicBox, ChristmasMusic music, Player player) {
            super(musicBox, music, player);
        }
    }
}
