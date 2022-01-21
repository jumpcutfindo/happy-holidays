package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.SheetMusicItem;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
            HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<SoundEvent> GINGERBREAD_SOGGIFIED = registerSound("block.gingerbread_soggified");

    public static final RegistryObject<SoundEvent> CHRISTMAS_BELL_RING = registerSound("block.christmas_bell_ring");

    public static final RegistryObject<SoundEvent> CHRISTMAS_STAR_BLOCK_PLACE = registerSound("block.christmas_star_place");
    public static final RegistryObject<SoundEvent> CHRISTMAS_STAR_EFFECT_APPLY = registerSound("block.christmas_star_effect_apply");

    public static final RegistryObject<SoundEvent> SANTA_ELF_BELL = registerSound("item.santa_elf_bell");

    public static final RegistryObject<SoundEvent> CHRISTMAS_GIFT_BOX_SHAKE = registerSound("item.christmas_gift_box_shake");

    public static final RegistryObject<SoundEvent> SNOW_GLOBE_FILL = registerSound("item.snow_globe_fill");
    public static final RegistryObject<SoundEvent> SNOW_GLOBE_USING = registerSound("item.snow_globe_using");
    public static final RegistryObject<SoundEvent> SNOW_GLOBE_SUCCESS = registerSound("item.snow_globe_success");

    public static final RegistryObject<SoundEvent> GINGERBREAD_PERSON_PASSIVE = registerSound("entity.gingerbread_person_passive");
    public static final RegistryObject<SoundEvent> GINGERBREAD_PERSON_HURT = registerSound("entity.gingerbread_person_hurt");

    public static final RegistryObject<SoundEvent> SANTA_ELF_ARRIVAL = registerSound("entity.santa_elf_arrival");
    public static final RegistryObject<SoundEvent> SANTA_ELF_PASSIVE = registerSound("entity.santa_elf_passive");
    public static final RegistryObject<SoundEvent> SANTA_ELF_HURT = registerSound("entity.santa_elf_hurt");
    public static final RegistryObject<SoundEvent> SANTA_ELF_YES = registerSound("entity.santa_elf_yes");
    public static final RegistryObject<SoundEvent> SANTA_ELF_NO = registerSound("entity.santa_elf_no");
    public static final RegistryObject<SoundEvent> SANTA_ELF_REQUEST_SINGLE_SUCCESS = registerSound("entity.santa_elf_request_single_success");
    public static final RegistryObject<SoundEvent> SANTA_ELF_REQUEST_COMPLETE = registerSound("entity.santa_elf_request_complete");
    public static final RegistryObject<SoundEvent> SANTA_ELF_DESPAWN = registerSound("entity.santa_elf_despawn");

    public static final RegistryObject<SoundEvent> GRINCH_PASSIVE = registerSound("entity.grinch_passive");
    public static final RegistryObject<SoundEvent> GRINCH_HURT = registerSound("entity.grinch_hurt");
    public static final RegistryObject<SoundEvent> GRINCH_BREAK_BOX = registerSound("entity.grinch_break_box");
    public static final RegistryObject<SoundEvent> GRINCH_DESPAWN = registerSound("entity.grinch_despawn");

    public static final RegistryObject<SoundEvent> SANTA_PASSIVE = registerSound("entity.santa_passive");
    public static final RegistryObject<SoundEvent> SANTA_ANGRY_PASSIVE = registerSound("entity.santa_angry_passive");
    public static final RegistryObject<SoundEvent> SANTA_HURT = registerSound("entity.santa_hurt");
    public static final RegistryObject<SoundEvent> SANTA_SUMMONING = registerSound("entity.santa_summoning");
    public static final RegistryObject<SoundEvent> SANTA_ITEM_APPEAR = registerSound("entity.santa_item_appear");
    public static final RegistryObject<SoundEvent> SANTA_SPAWNING_BAD = registerSound("entity.santa_spawning_bad");
    public static final RegistryObject<SoundEvent> SANTA_SPAWNING_GOOD = registerSound("entity.santa_spawning_good");
    public static final RegistryObject<SoundEvent> SANTA_SPAWN = registerSound("entity.santa_spawn");
    public static final RegistryObject<SoundEvent> SANTA_PREPARE_TELEPORT = registerSound("entity.santa_prepare_teleport");
    public static final RegistryObject<SoundEvent> SANTA_TELEPORT = registerSound("entity.santa_teleport");
    public static final RegistryObject<SoundEvent> SANTA_SUMMON_SLEIGHS = registerSound("entity.santa_summon_sleighs");
    public static final RegistryObject<SoundEvent> SANTA_FLICK = registerSound("entity.santa_flick");

    public static final RegistryObject<SoundEvent> NUTCRACKER_PASSIVE = registerSound("entity.nutcracker_passive");
    public static final RegistryObject<SoundEvent> NUTCRACKER_HURT = registerSound("entity.nutcracker_hurt");
    public static final RegistryObject<SoundEvent> NUTCRACKER_REPAIR = registerSound("entity.nutcracker_repair");
    public static final RegistryObject<SoundEvent> NUTCRACKER_TARGET_ACQUIRED = registerSound("entity.nutcracker_target_acquired");
    public static final RegistryObject<SoundEvent> NUTCRACKER_SHOOT = registerSound("entity.nutcracker_shoot");

    public static final RegistryObject<SoundEvent> PATROL_ORDERS_ACTION_SUCCESS = registerSound("item.patrol_orders.action_success");
    public static final RegistryObject<SoundEvent> PATROL_ORDERS_ACTION_FAIL = registerSound("item.patrol_orders.action_fail");
    public static final RegistryObject<SoundEvent> PATROL_ORDERS_ROUTE_COMPLETE = registerSound("item.patrol_orders.route_complete");

    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_ANGELS_ON_HIGH = registerSound(SheetMusicItem.ANGELS_ON_HIGH_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_CAROL_OF_THE_BELLS = registerSound(SheetMusicItem.CAROL_OF_BELLS_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_DECK_THE_HALLS = registerSound(SheetMusicItem.DECK_THE_HALLS_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_FROSTY_THE_SNOWMAN = registerSound(SheetMusicItem.FROSTY_THE_SNOWMAN_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_GOD_REST_GENTLEMEN = registerSound(SheetMusicItem.GOD_REST_GENTLEMEN_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_HERE_COMES_SANTA = registerSound(SheetMusicItem.HERE_COMES_SANTA_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_JINGLE_BELL_ROCK = registerSound(SheetMusicItem.JINGLE_BELL_ROCK_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_JINGLE_BELLS = registerSound(SheetMusicItem.JINGLE_BELLS_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_JOY_TO_THE_WORLD = registerSound(SheetMusicItem.JOY_TO_THE_WORLD_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_RUDOLPH = registerSound(SheetMusicItem.RUDOLPH_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_SILENT_NIGHT = registerSound(SheetMusicItem.SILENT_NIGHT_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_SLEIGH_RIDE = registerSound(SheetMusicItem.SLEIGH_RIDE_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_THE_FIRST_NOEL = registerSound(SheetMusicItem.THE_FIRST_NOEL_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_WE_THREE_KINGS = registerSound(SheetMusicItem.WE_THREE_KINGS_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_WE_WISH_YOU = registerSound(SheetMusicItem.WE_WISH_YOU_SOUND_ID);
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_WHITE_CHRISTMAS = registerSound(SheetMusicItem.WHITE_CHRISTMAS_SOUND_ID);

    public static RegistryObject<SoundEvent> registerSound(String soundId) {
        return SOUNDS.register(soundId, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID, soundId)));
    }
}
