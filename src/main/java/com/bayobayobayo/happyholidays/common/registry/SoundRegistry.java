package com.bayobayobayo.happyholidays.common.registry;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.item.christmas.music.AngelsOnHighSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.CarolOfBellsSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.DeckTheHallsSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.FrostySnowmanSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.GodRestGentlemenSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.HereComesSantaSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.JingleBellRockSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.JingleBellsSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.JoyToTheWorldSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.RudolphSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.SilentNightSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.SleighRideSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.TheFirstNoelSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.WeThreeKingsSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.WeWishYouSheetMusicItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.WhiteChristmasSheetMusicItem;

import net.minecraft.client.audio.Sound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
            HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<SoundEvent> SANTA_ELF_BELL = SOUNDS.register(
            "item.santa_elf_bell", () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "item.santa_elf_bell"))
    );

    public static final RegistryObject<SoundEvent> CHRISTMAS_GIFT_BOX_SHAKE = SOUNDS.register(
            "item.christmas_gift_box_shake", () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "item.christmas_gift_box_shake"))
    );

    public static final RegistryObject<SoundEvent> SANTA_ELF_ARRIVAL = SOUNDS.register(
            "entity.santa_elf_arrival", () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "entity.santa_elf_arrival"))
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
