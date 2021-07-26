package com.bayobayobayo.happyholidays.common.item.christmas.music;

public class JoyToTheWorldSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_joy_to_the_world";
    public static final String SOUND_ID = "item.christmas_music_joy_to_the_world";

    public JoyToTheWorldSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.JOY_TO_THE_WORLD;
    }
}
