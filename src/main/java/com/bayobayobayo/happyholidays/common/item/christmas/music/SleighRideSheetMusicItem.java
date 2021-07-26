package com.bayobayobayo.happyholidays.common.item.christmas.music;

public class SleighRideSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_sleigh_ride";
    public static final String SOUND_ID = "item.christmas_music_sleigh_ride";

    public SleighRideSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.SLEIGH_RIDE;
    }
}
