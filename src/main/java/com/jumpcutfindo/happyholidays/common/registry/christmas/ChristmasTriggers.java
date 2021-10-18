package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.Arrays;

import com.jumpcutfindo.happyholidays.common.advancements.CustomTrigger;

import net.minecraft.advancements.CriteriaTriggers;

public class ChristmasTriggers {
    // Music Box triggers
    public static final CustomTrigger PLAY_MUSIC_BOX = christmasTrigger("play_music_box");

    // Gingerbread Men triggers
    public static final CustomTrigger GINGERBREAD_MAN_TURN_SOGGY = christmasTrigger("gingerbread_man_turn_soggy");
    public static final CustomTrigger GINGERBREAD_MAN_TURN_DRY = christmasTrigger("gingerbread_man_turn_dry");

    // Santa's Elf triggers
    public static final CustomTrigger SANTA_ELF_SUMMON = christmasTrigger("santa_elf_summon");
    public static final CustomTrigger SANTA_ELF_TRADE = christmasTrigger("santa_elf_trade");
    public static final CustomTrigger SANTA_ELF_COMPLETE_REQUEST = christmasTrigger("santa_elf_complete_request");
    public static final CustomTrigger SANTA_ELF_COMPLETE_REQUEST_QUICK = christmasTrigger("santa_elf_complete_request_quick");

    // Grinch triggers
    public static final CustomTrigger GRINCH_ENCOUNTER = christmasTrigger("grinch_encounter");
    public static final CustomTrigger GRINCH_APPEASE = christmasTrigger("grinch_appease");

    // Stocking triggers
    public static final CustomTrigger STOCKING_FILL = christmasTrigger("stocking_fill");

    // Christmas Star triggers
    public static final CustomTrigger STAR_PUT_ORNAMENT = christmasTrigger("star_put_ornament");
    public static final CustomTrigger STAR_MAXED_TIER = christmasTrigger("star_maxed_tier");
    public static final CustomTrigger STAR_SUMMON_SANTA = christmasTrigger("star_summon_santa");
    public static final CustomTrigger STAR_REACH_BONUS = christmasTrigger("star_reach_bonus");

    // Santa triggers
    public static final CustomTrigger SANTA_DROP_PARTY_COMPLETE = christmasTrigger("santa_drop_party_complete");
    public static final CustomTrigger SANTA_ANGRY_DIE = christmasTrigger("santa_angry_die");
    public static final CustomTrigger SANTA_NO_TOUCHY = christmasTrigger("santa_no_touchy");

    private static final CustomTrigger[] TRIGGERS = new CustomTrigger[] {
            PLAY_MUSIC_BOX,
            GINGERBREAD_MAN_TURN_SOGGY,
            GINGERBREAD_MAN_TURN_DRY,
            SANTA_ELF_SUMMON,
            SANTA_ELF_TRADE,
            SANTA_ELF_COMPLETE_REQUEST,
            SANTA_ELF_COMPLETE_REQUEST_QUICK,
            GRINCH_ENCOUNTER,
            GRINCH_APPEASE,
            STOCKING_FILL,
            STAR_PUT_ORNAMENT,
            STAR_MAXED_TIER,
            STAR_SUMMON_SANTA,
            STAR_REACH_BONUS,
            SANTA_DROP_PARTY_COMPLETE,
            SANTA_ANGRY_DIE,
            SANTA_NO_TOUCHY
    };

    private static CustomTrigger christmasTrigger(String id) {
        return new CustomTrigger("christmas/" + id);
    }

    public static void registerTriggers() {
        Arrays.stream(TRIGGERS).forEach(CriteriaTriggers::register);
    }
}
