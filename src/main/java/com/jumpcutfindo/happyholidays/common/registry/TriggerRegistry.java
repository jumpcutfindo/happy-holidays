package com.jumpcutfindo.happyholidays.common.registry;

import java.util.Arrays;

import com.jumpcutfindo.happyholidays.common.advancements.CustomTrigger;

import net.minecraft.advancements.CriteriaTriggers;

public class TriggerRegistry {
    public static final CustomTrigger CHRISTMAS_PLAY_MUSIC_BOX = new CustomTrigger("christmas_play_music_box");

    private static final CustomTrigger[] TRIGGERS = new CustomTrigger[] {
        CHRISTMAS_PLAY_MUSIC_BOX
    };

    public static void registerTriggers() {
        Arrays.stream(TRIGGERS).forEach(CriteriaTriggers::register);
    }
}
