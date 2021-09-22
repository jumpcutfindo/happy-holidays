package com.jumpcutfindo.happyholidays.common.effect.christmas;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SpiritOfChristmasEffect extends MobEffect {
    public static final String EFFECT_ID = "spirit_of_christmas";

    public static final MobEffectCategory EFFECT_TYPE = MobEffectCategory.BENEFICIAL;
    public static final int EFFECT_COLOUR = 32526;

    public SpiritOfChristmasEffect() {
        super(EFFECT_TYPE, EFFECT_COLOUR);

        // Set modifiers
        this.addAttributeModifier(Attributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 2.0D,
                AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.1F,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
