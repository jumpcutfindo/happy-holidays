package com.bayobayobayo.happyholidays.common.item.christmas.music;

public class RudolphSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_rudolph";
    public static final String SOUND_ID = "item.christmas_music_rudolph";

    public RudolphSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.RUDOLPH;
    }
}
