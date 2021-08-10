package com.jumpcutfindo.happyholidays.common.effect.christmas;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class DebuffOfChristmasEffect extends Effect {
    public static final String EFFECT_ID = "debuff_of_christmas";

    public static final EffectType EFFECT_TYPE = EffectType.NEUTRAL;
    public static final int EFFECT_COLOUR = 16711680;

    public DebuffOfChristmasEffect() {
        super(EFFECT_TYPE, EFFECT_COLOUR);
    }
}
