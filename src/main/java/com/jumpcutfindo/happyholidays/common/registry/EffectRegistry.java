package com.jumpcutfindo.happyholidays.common.registry;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.effect.christmas.DebuffOfChristmasEffect;
import com.jumpcutfindo.happyholidays.common.effect.christmas.SpiritOfChristmasEffect;

import net.minecraft.entity.EntityType;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectRegistry {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(
            ForgeRegistries.POTIONS,
            HappyHolidaysMod.MOD_ID
    );

    public static final RegistryObject<Effect> SPIRIT_OF_CHRISTMAS_EFFECT =
            EFFECTS.register(SpiritOfChristmasEffect.EFFECT_ID, SpiritOfChristmasEffect::new);

    public static final RegistryObject<Effect> DEBUFF_OF_CHRISTMAS_EFFECT =
            EFFECTS.register(DebuffOfChristmasEffect.EFFECT_ID, DebuffOfChristmasEffect::new);
}
