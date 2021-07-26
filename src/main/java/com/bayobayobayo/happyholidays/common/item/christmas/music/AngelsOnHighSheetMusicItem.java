package com.bayobayobayo.happyholidays.common.item.christmas.music;

public class AngelsOnHighSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_angels_on_high";
    public static final String SOUND_ID = "item.christmas_music_angels_on_high";

    public AngelsOnHighSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.ANGELS_ON_HIGH;
    }
}
