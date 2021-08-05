package com.jumpcutfindo.happyholidays.common.item.christmas.music;

public class CarolOfBellsSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_carol_of_the_bells";
    public static final String SOUND_ID = "item.christmas_music_carol_of_the_bells";

    public static final int LENGTH = 1824;

    public CarolOfBellsSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.CAROL_OF_THE_BELLS;
    }
}
