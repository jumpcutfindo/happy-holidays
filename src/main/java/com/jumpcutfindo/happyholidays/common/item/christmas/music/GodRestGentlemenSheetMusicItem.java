package com.jumpcutfindo.happyholidays.common.item.christmas.music;

public class GodRestGentlemenSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_god_rest_gentlemen";
    public static final String SOUND_ID = "item.christmas_music_god_rest_gentlemen";

    public static final int LENGTH = 1840;

    public GodRestGentlemenSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.GOD_REST_GENTLEMEN;
    }
}
