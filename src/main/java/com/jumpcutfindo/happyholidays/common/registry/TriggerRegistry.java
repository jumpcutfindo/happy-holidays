package com.jumpcutfindo.happyholidays.common.registry;

import java.util.Arrays;

import com.jumpcutfindo.happyholidays.common.advancements.CustomTrigger;

import net.minecraft.advancements.CriteriaTriggers;

public class TriggerRegistry {
    public static final CustomTrigger CHRISTMAS_PLAY_MUSIC_BOX = new CustomTrigger("christmas_play_music_box");
    public static final CustomTrigger CHRISTMAS_GINGERBREAD_MAN_TURN_SOGGY = new CustomTrigger("christmas_gingerbread_man_turn_soggy");
    public static final CustomTrigger CHRISTMAS_GINGERBREAD_MAN_TURN_DRY = new CustomTrigger("christmas_gingerbread_man_turn_dry");
    public static final CustomTrigger CHRISTMAS_SANTA_ELF_SUMMON = new CustomTrigger("christmas_santa_elf_summon");
    public static final CustomTrigger CHRISTMAS_SANTA_ELF_TRADE = new CustomTrigger("christmas_santa_elf_trade");
    public static final CustomTrigger CHRISTMAS_SANTA_ELF_COMPLETE_REQUEST = new CustomTrigger(
            "christmas_santa_elf_complete_request");
    public static final CustomTrigger CHRISTMAS_SANTA_ELF_COMPLETE_REQUEST_QUICK = new CustomTrigger(
            "christmas_santa_elf_complete_request_quick");

    private static final CustomTrigger[] TRIGGERS = new CustomTrigger[] {
            CHRISTMAS_PLAY_MUSIC_BOX,
            CHRISTMAS_GINGERBREAD_MAN_TURN_SOGGY,
            CHRISTMAS_GINGERBREAD_MAN_TURN_DRY,
            CHRISTMAS_SANTA_ELF_SUMMON,
            CHRISTMAS_SANTA_ELF_TRADE,
            CHRISTMAS_SANTA_ELF_COMPLETE_REQUEST,
            CHRISTMAS_SANTA_ELF_COMPLETE_REQUEST_QUICK
    };

    public static void registerTriggers() {
        Arrays.stream(TRIGGERS).forEach(CriteriaTriggers::register);
    }
}
