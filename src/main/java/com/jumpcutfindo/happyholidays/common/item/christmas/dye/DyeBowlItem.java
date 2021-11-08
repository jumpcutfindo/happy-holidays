package com.jumpcutfindo.happyholidays.common.item.christmas.dye;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

public class DyeBowlItem extends ChristmasItem {
    public static final String DYE_BOWL_ID = "dye_bowl";

    public static final String RED_DYE_ID = "red_christmas_dye";
    public static final String BLUE_DYE_ID = "blue_christmas_dye";
    public static final String YELLOW_DYE_ID = "yellow_christmas_dye";
    public static final String GREEN_DYE_ID = "green_christmas_dye";
    public static final String GOLD_DYE_ID = "gold_christmas_dye";
    public static final String SILVER_DYE_ID = "silver_christmas_dye";

    private static final Properties ITEM_PROPERTIES =
            new Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public DyeBowlItem() {
        super(ITEM_PROPERTIES);
    }
}
