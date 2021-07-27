package com.bayobayobayo.happyholidays.common.item.christmas.music;

public class TheFirstNoelSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_the_first_noel";
    public static final String SOUND_ID = "item.christmas_music_the_first_noel";

    public static final int LENGTH = 1101;

    public TheFirstNoelSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.THE_FIRST_NOEL;
    }
}
