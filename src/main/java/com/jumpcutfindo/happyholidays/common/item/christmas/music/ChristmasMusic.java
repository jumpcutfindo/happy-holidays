package com.jumpcutfindo.happyholidays.common.item.christmas.music;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;

import net.minecraft.sounds.SoundEvent;

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
        case ANGELS_ON_HIGH: return ChristmasSounds.CHRISTMAS_MUSIC_ANGELS_ON_HIGH.get();
        case CAROL_OF_THE_BELLS: return ChristmasSounds.CHRISTMAS_MUSIC_CAROL_OF_THE_BELLS.get();
        case DECK_THE_HALLS: return ChristmasSounds.CHRISTMAS_MUSIC_DECK_THE_HALLS.get();
        case FROSTY_THE_SNOWMAN: return ChristmasSounds.CHRISTMAS_MUSIC_FROSTY_THE_SNOWMAN.get();
        case GOD_REST_GENTLEMEN: return ChristmasSounds.CHRISTMAS_MUSIC_GOD_REST_GENTLEMEN.get();
        case HERE_COMES_SANTA: return ChristmasSounds.CHRISTMAS_MUSIC_HERE_COMES_SANTA.get();
        case JINGLE_BELL_ROCK: return ChristmasSounds.CHRISTMAS_MUSIC_JINGLE_BELL_ROCK.get();
        case JINGLE_BELLS: return ChristmasSounds.CHRISTMAS_MUSIC_JINGLE_BELLS.get();
        case JOY_TO_THE_WORLD: return ChristmasSounds.CHRISTMAS_MUSIC_JOY_TO_THE_WORLD.get();
        case RUDOLPH: return ChristmasSounds.CHRISTMAS_MUSIC_RUDOLPH.get();
        case SILENT_NIGHT: return ChristmasSounds.CHRISTMAS_MUSIC_SILENT_NIGHT.get();
        case SLEIGH_RIDE: return ChristmasSounds.CHRISTMAS_MUSIC_SLEIGH_RIDE.get();
        case THE_FIRST_NOEL: return ChristmasSounds.CHRISTMAS_MUSIC_THE_FIRST_NOEL.get();
        case WE_THREE_KINGS: return ChristmasSounds.CHRISTMAS_MUSIC_WE_THREE_KINGS.get();
        case WE_WISH_YOU: return ChristmasSounds.CHRISTMAS_MUSIC_WE_WISH_YOU.get();
        default: return ChristmasSounds.CHRISTMAS_MUSIC_WHITE_CHRISTMAS.get();
        }
    }

    public static int getSoundDuration(ChristmasMusic music) {
        switch (music) {
        case ANGELS_ON_HIGH: return 1221;
        case CAROL_OF_THE_BELLS: return 1824;
        case DECK_THE_HALLS: return 1817;
        case FROSTY_THE_SNOWMAN: return 1162;
        case GOD_REST_GENTLEMEN: return 1840;
        case HERE_COMES_SANTA: return 2155;
        case JINGLE_BELL_ROCK: return 1485;
        case JINGLE_BELLS: return 2191;
        case JOY_TO_THE_WORLD: return 2089;
        case RUDOLPH: return 1789;
        case SILENT_NIGHT: return 2037;
        case SLEIGH_RIDE: return 1399;
        case THE_FIRST_NOEL: return 1101;
        case WE_THREE_KINGS: return 1460;
        case WE_WISH_YOU: return 1977;
        case WHITE_CHRISTMAS: return 1674;
        default: return 0;
        }
    }
}
