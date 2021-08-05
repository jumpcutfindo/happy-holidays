package com.jumpcutfindo.happyholidays.common.item.christmas.music;

public class WhiteChristmasSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_white_christmas";
    public static final String SOUND_ID = "item.christmas_music_white_christmas";

    public static final int LENGTH = 1674;

    public WhiteChristmasSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.WHITE_CHRISTMAS;
    }
}
