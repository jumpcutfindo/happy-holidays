package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.effect.christmas.GenerosityOfChristmasEffect;
import com.jumpcutfindo.happyholidays.common.effect.christmas.SpiritOfChristmasEffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ChristmasEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(
            ForgeRegistries.MOB_EFFECTS,
            HappyHolidaysMod.MOD_ID
    );

    public static final RegistryObject<MobEffect> SPIRIT_OF_CHRISTMAS_EFFECT =
            EFFECTS.register(SpiritOfChristmasEffect.EFFECT_ID, SpiritOfChristmasEffect::new);

    public static final RegistryObject<MobEffect> DEBUFF_OF_CHRISTMAS_EFFECT =
            EFFECTS.register(GenerosityOfChristmasEffect.EFFECT_ID, GenerosityOfChristmasEffect::new);
}
