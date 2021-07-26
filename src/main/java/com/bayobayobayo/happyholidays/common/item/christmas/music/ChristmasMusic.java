package com.bayobayobayo.happyholidays.common.item.christmas.music;

import com.bayobayobayo.happyholidays.common.registry.SoundRegistry;

import net.minecraft.client.audio.Sound;
import net.minecraft.util.SoundEvent;

public enum ChristmasMusic {
    ANGELS_ON_HIGH(0),
    CAROL_OF_THE_BELLS(1),
    DECK_THE_HALLS(2),
    FROSTY_THE_SNOWMAN(3),
    GOD_REST_GENTLEMEN(4),
    HERE_COMES_SANTA(5),
    JINGLE_BELL_ROCK(6),
    JINGLE_BELLS(7),
    JOY_TO_THE_WORLD(8),
    RUDOLPH(9),
    SILENT_NIGHT(10),
    SLEIGH_RIDE(11),
    THE_FIRST_NOEL(12),
    WE_THREE_KINGS(13),
    WE_WISH_YOU(14),
    WHITE_CHRISTMAS(15);

    private int id;

    private ChristmasMusic(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ChristmasMusic getMusic(int id) {
        return ChristmasMusic.values()[id];
    }

    public static SoundEvent getSound(ChristmasMusic music) {
        switch (music) {
        case ANGELS_ON_HIGH: return SoundRegistry.CHRISTMAS_MUSIC_ANGELS_ON_HIGH.get();
        case CAROL_OF_THE_BELLS: return SoundRegistry.CHRISTMAS_MUSIC_CAROL_OF_THE_BELLS.get();
        case DECK_THE_HALLS: return SoundRegistry.CHRISTMAS_MUSIC_DECK_THE_HALLS.get();
        case FROSTY_THE_SNOWMAN: return SoundRegistry.CHRISTMAS_MUSIC_FROSTY_THE_SNOWMAN.get();
        case GOD_REST_GENTLEMEN: return SoundRegistry.CHRISTMAS_MUSIC_GOD_REST_GENTLEMEN.get();
        case HERE_COMES_SANTA: return SoundRegistry.CHRISTMAS_MUSIC_HERE_COMES_SANTA.get();
        case JINGLE_BELL_ROCK: return SoundRegistry.CHRISTMAS_MUSIC_JINGLE_BELL_ROCK.get();
        case JINGLE_BELLS: return SoundRegistry.CHRISTMAS_MUSIC_JINGLE_BELLS.get();
        case JOY_TO_THE_WORLD: return SoundRegistry.CHRISTMAS_MUSIC_JOY_TO_THE_WORLD.get();
        case RUDOLPH: return SoundRegistry.CHRISTMAS_MUSIC_RUDOLPH.get();
        case SILENT_NIGHT: return SoundRegistry.CHRISTMAS_MUSIC_SILENT_NIGHT.get();
        case SLEIGH_RIDE: return SoundRegistry.CHRISTMAS_MUSIC_SLEIGH_RIDE.get();
        case THE_FIRST_NOEL: return SoundRegistry.CHRISTMAS_MUSIC_THE_FIRST_NOEL.get();
        case WE_THREE_KINGS: return SoundRegistry.CHRISTMAS_MUSIC_WE_THREE_KINGS.get();
        case WE_WISH_YOU: return SoundRegistry.CHRISTMAS_MUSIC_WE_WISH_YOU.get();
        default: return SoundRegistry.CHRISTMAS_MUSIC_WHITE_CHRISTMAS.get();
        }
    }
}
