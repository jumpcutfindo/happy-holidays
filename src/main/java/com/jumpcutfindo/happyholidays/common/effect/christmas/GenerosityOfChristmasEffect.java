package com.jumpcutfindo.happyholidays.common.effect.christmas;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class GenerosityOfChristmasEffect extends Effect {
    public static final String EFFECT_ID = "generosity_of_christmas";

    public static final EffectType EFFECT_TYPE = EffectType.NEUTRAL;
    public static final int EFFECT_COLOUR = 16711680;

    public GenerosityOfChristmasEffect() {
        super(EFFECT_TYPE, EFFECT_COLOUR);
    }
}
