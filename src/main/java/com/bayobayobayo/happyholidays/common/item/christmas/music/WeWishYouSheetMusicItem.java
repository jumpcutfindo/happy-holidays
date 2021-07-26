package com.bayobayobayo.happyholidays.common.item.christmas.music;

public class WeWishYouSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_we_wish_you";
    public static final String SOUND_ID = "item.christmas_music_we_wish_you";

    public WeWishYouSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.WE_WISH_YOU;
    }
}
