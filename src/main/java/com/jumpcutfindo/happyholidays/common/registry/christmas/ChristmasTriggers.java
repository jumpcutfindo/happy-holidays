package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.Set;

import com.google.common.collect.Sets;
import com.jumpcutfindo.happyholidays.common.advancements.CustomTrigger;

import net.minecraft.advancements.CriteriaTriggers;

public class ChristmasTriggers {
    public static final Set<CustomTrigger> TRIGGERS = Sets.newHashSet();

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
    public static final CustomTrigger STOCKING_UPGRADE = christmasTrigger("stocking_upgrade");

    // Christmas Star triggers
    public static final CustomTrigger STAR_PUT_ORNAMENT = christmasTrigger("star_put_ornament");
    public static final CustomTrigger STAR_MAXED_TIER = christmasTrigger("star_maxed_tier");
    public static final CustomTrigger STAR_SUMMON_SANTA = christmasTrigger("star_summon_santa");
    public static final CustomTrigger STAR_REACH_BONUS = christmasTrigger("star_reach_bonus");

    // Santa triggers
    public static final CustomTrigger SANTA_DROP_PARTY_COMPLETE = christmasTrigger("santa_drop_party_complete");
    public static final CustomTrigger SANTA_ANGRY_DIE = christmasTrigger("santa_angry_die");
    public static final CustomTrigger SANTA_NO_TOUCHY = christmasTrigger("santa_no_touchy");

    private static CustomTrigger christmasTrigger(String id) {
        CustomTrigger customTrigger = new CustomTrigger("christmas/" + id);

        TRIGGERS.add(customTrigger);

        return customTrigger;
    }

    public static void registerTriggers() {
        TRIGGERS.forEach(CriteriaTriggers::register);
    }
}
