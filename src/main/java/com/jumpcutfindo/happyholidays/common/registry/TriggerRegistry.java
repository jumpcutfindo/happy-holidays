package com.jumpcutfindo.happyholidays.common.registry;

import java.util.Arrays;

import com.jumpcutfindo.happyholidays.common.advancements.CustomTrigger;

import net.minecraft.advancements.CriteriaTriggers;

public class TriggerRegistry {
    public static final CustomTrigger CHRISTMAS_PLAY_MUSIC_BOX = new CustomTrigger("christmas_play_music_box");
    public static final CustomTrigger CHRISTMAS_GINGERBREAD_MAN_TURN_SOGGY = new CustomTrigger(
            "christmas_gingerbread_man_turn_soggy");
    public static final CustomTrigger CHRISTMAS_GINGERBREAD_MAN_TURN_DRY = new CustomTrigger(
            "christmas_gingerbread_man_turn_dry");

    private static final CustomTrigger[] TRIGGERS = new CustomTrigger[] {
        CHRISTMAS_PLAY_MUSIC_BOX,
        CHRISTMAS_GINGERBREAD_MAN_TURN_SOGGY,
        CHRISTMAS_GINGERBREAD_MAN_TURN_DRY
    };

    public static void registerTriggers() {
        Arrays.stream(TRIGGERS).forEach(CriteriaTriggers::register);
    }
}
