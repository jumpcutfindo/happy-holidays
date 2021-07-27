package com.bayobayobayo.happyholidays.common.item.christmas.music;

public class FrostySnowmanSheetMusicItem extends SheetMusicItem {
    public static final String ITEM_ID = "christmas_sheet_music_frosty_the_snowman";
    public static final String SOUND_ID = "item.christmas_music_frosty_the_snowman";

    public static final int LENGTH = 1162;

    public FrostySnowmanSheetMusicItem() {
        super(ITEM_ID);

        this.music = ChristmasMusic.FROSTY_THE_SNOWMAN;
    }
}
