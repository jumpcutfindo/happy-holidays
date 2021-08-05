package com.jumpcutfindo.happyholidays.common.item.christmas.music;

public class HereComesSantaSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_here_comes_santa";
    public static final String SOUND_ID = "item.christmas_music_here_comes_santa";

    public static final int LENGTH = 2155;

    public HereComesSantaSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.HERE_COMES_SANTA;
    }
}
