package com.bayobayobayo.happyholidays.common.item.christmas.music;

public class WeThreeKingsSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_we_three_kings";
    public static final String SOUND_ID = "item.christmas_music_we_three_kings";

    public static final int LENGTH = 1460;

    public WeThreeKingsSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.WE_THREE_KINGS;
    }
}
