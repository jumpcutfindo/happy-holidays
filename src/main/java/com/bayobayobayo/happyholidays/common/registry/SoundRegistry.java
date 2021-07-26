package com.bayobayobayo.happyholidays.common.registry;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
            HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<SoundEvent> CHRISTMAS_MUSIC_SILENT_NIGHT = SOUNDS.register(
            "item.christmas_music_silent_night", () -> new SoundEvent(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                    "item.christmas_music_silent_night"))
    );
}
