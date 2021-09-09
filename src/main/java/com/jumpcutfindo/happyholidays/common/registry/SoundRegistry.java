package com.jumpcutfindo.happyholidays.common.registry;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.AngelsOnHighSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.CarolOfBellsSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.DeckTheHallsSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.FrostySnowmanSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.GodRestGentlemenSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.HereComesSantaSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.JingleBellRockSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.JingleBellsSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.JoyToTheWorldSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.RudolphSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.SilentNightSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.SleighRideSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.TheFirstNoelSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.WeThreeKingsSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.WeWishYouSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.WhiteChristmasSheetMusicItem;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
            HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<SoundEvent> CHRISTMAS_STAR_BLOCK_PLACE = SOUNDS.register(
            "block.christmas_star_place", () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "block.christmas_star_place"))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_STAR_EFFECT_APPLY = SOUNDS.register(
            "block.christmas_star_effect_apply", () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "block.christmas_star_effect_apply"))
    );

    public static final RegistryObject<SoundEvent> SANTA_ELF_BELL = SOUNDS.register(
            "item.santa_elf_bell", () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "item.santa_elf_bell"))
    );

    public static final RegistryObject<SoundEvent> CHRISTMAS_GIFT_BOX_SHAKE = SOUNDS.register(
            "item.christmas_gift_box_shake", () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "item.christmas_gift_box_shake"))
    );

    public static final RegistryObject<SoundEvent> GINGERBREAD_PERSON_PASSIVE = SOUNDS.register(
            "entity.gingerbread_person_passive", () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.gingerbread_person_passive"))
    );
    public static final RegistryObject<SoundEvent> GINGERBREAD_PERSON_HURT = SOUNDS.register(
            "entity.gingerbread_person_hurt", () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.gingerbread_person_hurt"))
    );

    public static final RegistryObject<SoundEvent> SANTA_ELF_ARRIVAL = SOUNDS.register(
            "entity.santa_elf_arrival", () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_elf_arrival"))
    );
    public static final RegistryObject<SoundEvent> SANTA_ELF_PASSIVE = SOUNDS.register(
            "entity.santa_elf_passive", () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_elf_passive"))
    );
    public static final RegistryObject<SoundEvent> SANTA_ELF_HURT = SOUNDS.register(
            "entity.santa_elf_hurt", () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_elf_hurt"))
    );
    public static final RegistryObject<SoundEvent> SANTA_ELF_YES = SOUNDS.register(
            "entity.santa_elf_yes",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_elf_yes"))
    );public static final RegistryObject<SoundEvent> SANTA_ELF_NO = SOUNDS.register(
            "entity.santa_elf_no",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_elf_no"))
    );
    public static final RegistryObject<SoundEvent> SANTA_ELF_REQUEST_SINGLE_SUCCESS = SOUNDS.register(
            "entity.santa_elf_request_single_success",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_elf_request_single_success"))
    );
    public static final RegistryObject<SoundEvent> SANTA_ELF_REQUEST_COMPLETE = SOUNDS.register(
            "entity.santa_elf_request_complete",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_elf_request_complete"))
    );
    public static final RegistryObject<SoundEvent> SANTA_ELF_DESPAWN = SOUNDS.register(
            "entity.santa_elf_despawn",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_elf_despawn"))
    );

    public static final RegistryObject<SoundEvent> GRINCH_PASSIVE = SOUNDS.register(
            "entity.grinch_passive",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.grinch_passive"))
    );
    public static final RegistryObject<SoundEvent> GRINCH_HURT = SOUNDS.register(
            "entity.grinch_hurt",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.grinch_hurt"))
    );
    public static final RegistryObject<SoundEvent> GRINCH_BREAK_BOX = SOUNDS.register(
            "entity.grinch_break_box",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.grinch_break_box"))
    );
    public static final RegistryObject<SoundEvent> GRINCH_DESPAWN = SOUNDS.register(
            "entity.grinch_despawn",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.grinch_despawn"))
    );

    public static final RegistryObject<SoundEvent> SANTA_PASSIVE = SOUNDS.register(
            "entity.santa_passive",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_passive"))
    );
    public static final RegistryObject<SoundEvent> SANTA_ANGRY_PASSIVE = SOUNDS.register(
            "entity.santa_angry_passive",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_angry_passive"))
    );
    public static final RegistryObject<SoundEvent> SANTA_HURT = SOUNDS.register(
            "entity.santa_hurt",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_hurt"))
    );
    public static final RegistryObject<SoundEvent> SANTA_SUMMONING = SOUNDS.register(
            "entity.santa_summoning",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_summoning"))
    );
    public static final RegistryObject<SoundEvent> SANTA_ITEM_APPEAR = SOUNDS.register(
            "entity.santa_item_appear",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_item_appear"))
    );
    public static final RegistryObject<SoundEvent> SANTA_SPAWNING_BAD = SOUNDS.register(
            "entity.santa_spawning_bad",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_spawning_bad"))
    );
    public static final RegistryObject<SoundEvent> SANTA_SPAWNING_GOOD = SOUNDS.register(
            "entity.santa_spawning_good",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_spawning_good"))
    );
    public static final RegistryObject<SoundEvent> SANTA_SPAWN = SOUNDS.register(
            "entity.santa_spawn",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_spawn"))
    );
    public static final RegistryObject<SoundEvent> SANTA_PREPARE_TELEPORT = SOUNDS.register(
            "entity.santa_prepare_teleport",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_prepare_teleport"))
    );
    public static final RegistryObject<SoundEvent> SANTA_TELEPORT = SOUNDS.register(
            "entity.santa_teleport",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_teleport"))
    );
    public static final RegistryObject<SoundEvent> SANTA_SUMMON_SLEIGHS = SOUNDS.register(
            "entity.santa_summon_sleighs",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_summon_sleighs"))
    );
    public static final RegistryObject<SoundEvent> SANTA_FLICK = SOUNDS.register(
            "entity.santa_flick",
            () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_flick"))
    );

    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_ANGELS_ON_HIGH = SOUNDS.register(
            AngelsOnHighSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    AngelsOnHighSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_CAROL_OF_THE_BELLS = SOUNDS.register(
            CarolOfBellsSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    CarolOfBellsSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_DECK_THE_HALLS = SOUNDS.register(
            DeckTheHallsSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    DeckTheHallsSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_FROSTY_THE_SNOWMAN = SOUNDS.register(
            FrostySnowmanSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    FrostySnowmanSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_GOD_REST_GENTLEMEN = SOUNDS.register(
            GodRestGentlemenSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    GodRestGentlemenSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_HERE_COMES_SANTA = SOUNDS.register(
            HereComesSantaSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    HereComesSantaSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_JINGLE_BELL_ROCK = SOUNDS.register(
            JingleBellRockSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    JingleBellRockSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_JINGLE_BELLS = SOUNDS.register(
            JingleBellsSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    JingleBellsSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_JOY_TO_THE_WORLD = SOUNDS.register(
            JoyToTheWorldSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    JoyToTheWorldSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_RUDOLPH = SOUNDS.register(
            RudolphSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    RudolphSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_SILENT_NIGHT = SOUNDS.register(
            SilentNightSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    SilentNightSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_SLEIGH_RIDE = SOUNDS.register(
            SleighRideSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    SleighRideSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_THE_FIRST_NOEL = SOUNDS.register(
            TheFirstNoelSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    TheFirstNoelSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_WE_THREE_KINGS = SOUNDS.register(
            WeThreeKingsSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    WeThreeKingsSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_WE_WISH_YOU = SOUNDS.register(
            WeWishYouSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    WeWishYouSheetMusicItem.SOUND_ID))
    );
    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_WHITE_CHRISTMAS = SOUNDS.register(
            WhiteChristmasSheetMusicItem.SOUND_ID, () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    WhiteChristmasSheetMusicItem.SOUND_ID))
    );
}
