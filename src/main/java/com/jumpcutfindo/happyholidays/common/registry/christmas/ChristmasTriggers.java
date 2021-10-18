package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.Arrays;

import com.jumpcutfindo.happyholidays.common.advancements.CustomTrigger;

import net.minecraft.advancements.CriteriaTriggers;

public class ChristmasTriggers {
    // TODO: Fix this so it works with the new Music Box
    public static final CustomTrigger CHRISTMAS_PLAY_MUSIC_BOX = new CustomTrigger("christmas_play_music_box");
    public static final CustomTrigger CHRISTMAS_GINGERBREAD_MAN_TURN_SOGGY = new CustomTrigger("christmas_gingerbread_man_turn_soggy");
    public static final CustomTrigger CHRISTMAS_GINGERBREAD_MAN_TURN_DRY = new CustomTrigger("christmas_gingerbread_man_turn_dry");
    public static final CustomTrigger CHRISTMAS_SANTA_ELF_SUMMON = new CustomTrigger("christmas_santa_elf_summon");
    public static final CustomTrigger CHRISTMAS_SANTA_ELF_TRADE = new CustomTrigger("christmas_santa_elf_trade");
    public static final CustomTrigger CHRISTMAS_SANTA_ELF_COMPLETE_REQUEST = new CustomTrigger("christmas_santa_elf_complete_request");
    public static final CustomTrigger CHRISTMAS_SANTA_ELF_COMPLETE_REQUEST_QUICK = new CustomTrigger("christmas_santa_elf_complete_request_quick");
    public static final CustomTrigger CHRISTMAS_GRINCH_ENCOUNTER = new CustomTrigger("christmas_grinch_encounter");
    public static final CustomTrigger CHRISTMAS_GRINCH_APPEASE = new CustomTrigger("christmas_grinch_appease");
    public static final CustomTrigger CHRISTMAS_STOCKING_FILL = new CustomTrigger("christmas_stocking_fill");
    public static final CustomTrigger CHRISTMAS_STAR_PUT_ORNAMENT = new CustomTrigger("christmas_star_put_ornament");
    public static final CustomTrigger CHRISTMAS_STAR_MAXED_TIER = new CustomTrigger("christmas_star_maxed_tier");
    public static final CustomTrigger CHRISTMAS_STAR_SUMMON_SANTA = new CustomTrigger("christmas_star_summon_santa");
    public static final CustomTrigger CHRISTMAS_STAR_REACH_BONUS = new CustomTrigger("christmas_star_reach_bonus");
    public static final CustomTrigger CHRISTMAS_SANTA_DROP_PARTY_COMPLETE = new CustomTrigger("christmas_santa_drop_party_complete");
    public static final CustomTrigger CHRISTMAS_SANTA_ANGRY_DIE = new CustomTrigger("christmas_santa_angry_die");
    public static final CustomTrigger CHRISTMAS_SANTA_NO_TOUCHY = new CustomTrigger("christmas_santa_no_touchy");

    private static final CustomTrigger[] TRIGGERS = new CustomTrigger[] {
            CHRISTMAS_PLAY_MUSIC_BOX,
            CHRISTMAS_GINGERBREAD_MAN_TURN_SOGGY,
            CHRISTMAS_GINGERBREAD_MAN_TURN_DRY,
            CHRISTMAS_SANTA_ELF_SUMMON,
            CHRISTMAS_SANTA_ELF_TRADE,
            CHRISTMAS_SANTA_ELF_COMPLETE_REQUEST,
            CHRISTMAS_SANTA_ELF_COMPLETE_REQUEST_QUICK,
            CHRISTMAS_GRINCH_ENCOUNTER,
            CHRISTMAS_GRINCH_APPEASE,
            CHRISTMAS_STOCKING_FILL,
            CHRISTMAS_STAR_PUT_ORNAMENT,
            CHRISTMAS_STAR_MAXED_TIER,
            CHRISTMAS_STAR_SUMMON_SANTA,
            CHRISTMAS_STAR_REACH_BONUS,
            CHRISTMAS_SANTA_DROP_PARTY_COMPLETE,
            CHRISTMAS_SANTA_ANGRY_DIE,
            CHRISTMAS_SANTA_NO_TOUCHY
    };

    public static void registerTriggers() {
        Arrays.stream(TRIGGERS).forEach(CriteriaTriggers::register);
    }
}
