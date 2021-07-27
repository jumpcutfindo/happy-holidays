package com.bayobayobayo.happyholidays.common.item.christmas.music;

public class JingleBellsSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_jingle_bells";
    public static final String SOUND_ID = "item.christmas_music_jingle_bells";

    public static final int LENGTH = 1485;

    public JingleBellsSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.JINGLE_BELLS;
    }
}
