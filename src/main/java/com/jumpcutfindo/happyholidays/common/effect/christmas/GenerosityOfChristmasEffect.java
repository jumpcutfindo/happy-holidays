package com.jumpcutfindo.happyholidays.common.effect.christmas;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class GenerosityOfChristmasEffect extends MobEffect {
    public static final String EFFECT_ID = "generosity_of_christmas";

    public static final MobEffectCategory EFFECT_TYPE = MobEffectCategory.NEUTRAL;
    public static final int EFFECT_COLOUR = 16711680;

    public GenerosityOfChristmasEffect() {
        super(EFFECT_TYPE, EFFECT_COLOUR);
    }
}
