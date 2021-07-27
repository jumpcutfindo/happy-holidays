package com.bayobayobayo.happyholidays.common.item.christmas.music;

public class DeckTheHallsSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_deck_the_halls";
    public static final String SOUND_ID = "item.christmas_music_deck_the_halls";

    public static final int LENGTH = 1817;

    public DeckTheHallsSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.DECK_THE_HALLS;
    }
}
