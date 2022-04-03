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
    public static final CustomTrigger GINGERBREAD_MAN_DRY_CHALLENGE = christmasTrigger("gingerbread_man_dry_challenge");

    // Santa's Elf triggers
    public static final CustomTrigger SANTA_ELF_SUMMON = christmasTrigger("santa_elf_summon");
    public static final CustomTrigger SANTA_ELF_TRADE = christmasTrigger("santa_elf_trade");
    public static final CustomTrigger SANTA_ELF_COMPLETE_REQUEST = christmasTrigger("santa_elf_complete_request");
    public static final CustomTrigger SANTA_ELF_COMPLETE_REQUEST_QUICK = christmasTrigger("santa_elf_complete_request_quick");
    public static final CustomTrigger SANTA_ELF_HELP_CHALLENGE = christmasTrigger("santa_elf_help_challenge");

    // Grinch triggers
    public static final CustomTrigger GRINCH_ENCOUNTER = christmasTrigger("grinch_encounter");
    public static final CustomTrigger GRINCH_APPEASE = christmasTrigger("grinch_appease");
    public static final CustomTrigger GRINCH_APPEASE_CHALLENGE = christmasTrigger("grinch_appease_challenge");

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
    public static final CustomTrigger SANTA_ANGRY_CHALLENGE = christmasTrigger("santa_angry_challenge");
    public static final CustomTrigger SANTA_HAPPY_CHALLENGE = christmasTrigger("santa_happy_challenge");

    // Snow Globe triggers
    public static final CustomTrigger SNOW_GLOBE_USE = christmasTrigger("snow_globe_use");
    public static final CustomTrigger SNOW_GLOBE_USE_CHALLENGE = christmasTrigger("snow_globe_use_challenge");

    // Patrol Order triggers
    public static final CustomTrigger PATROL_ORDERS_COMPLETE = christmasTrigger("patrol_orders_complete");

    // Swagger Stick triggers
    public static final CustomTrigger SWAGGER_STICK_HELD = christmasTrigger("swagger_stick_held");

    // Nutcracker triggers
    public static final CustomTrigger NUTCRACKER_ENCOUNTER = christmasTrigger("nutcracker_encounter");
    public static final CustomTrigger NUTCRACKER_TAME = christmasTrigger("nutcracker_tame");
    public static final CustomTrigger NUTCRACKER_TAME_CHALLENGE = christmasTrigger("nutcracker_tame_challenge");
    public static final CustomTrigger NUTCRACKER_RECEIVE_COMPLETE_ORDERS = christmasTrigger("nutcracker_receive_complete_orders");
    public static final CustomTrigger NUTCRACKER_RECEIVE_SPECIAL_WALNUT = christmasTrigger("nutcracker_receive_special_walnut");
    public static final CustomTrigger NUTCRACKER_RECEIVE_ARMOR = christmasTrigger("nutcracker_receive_armor");
    public static final CustomTrigger NUTCRACKER_EXPLOSIVE_INVENTORY = christmasTrigger("nutcracker_explosive_inventory");
    public static final CustomTrigger NUTCRACKER_HIGH_ARMOR_RATING = christmasTrigger("nutcracker_high_armor_rating");
    public static final CustomTrigger NUTCRACKER_KILLS_MOB = christmasTrigger("nutcracker_kills_mob");
    public static final CustomTrigger NUTCRACKER_KILL_CHALLENGE = christmasTrigger("nutcracker_kill_challenge");

    private static CustomTrigger christmasTrigger(String id) {
        CustomTrigger customTrigger = new CustomTrigger("christmas/" + id);

        TRIGGERS.add(customTrigger);

        return customTrigger;
    }

    public static void registerTriggers() {
        TRIGGERS.forEach(CriteriaTriggers::register);
    }
}
