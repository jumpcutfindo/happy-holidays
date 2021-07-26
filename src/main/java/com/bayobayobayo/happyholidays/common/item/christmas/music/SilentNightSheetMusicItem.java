package com.bayobayobayo.happyholidays.common.item.christmas.music;

public class SilentNightSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_silent_night";
    public static final String SOUND_ID = "item.christmas_music_silent_night";

    public SilentNightSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.SILENT_NIGHT;
    }
}
