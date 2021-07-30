package com.bayobayobayo.happyholidays.common.item.christmas.music;

public class JingleBellRockSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_jingle_bell_rock";
    public static final String SOUND_ID = "item.christmas_music_jingle_bell_rock";

    public static final int LENGTH = 1485;

    public JingleBellRockSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.JINGLE_BELL_ROCK;
    }
}
