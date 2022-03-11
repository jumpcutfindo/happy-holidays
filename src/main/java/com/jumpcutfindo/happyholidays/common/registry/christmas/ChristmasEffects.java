package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.effect.HappyHolidaysEffect;
import com.jumpcutfindo.happyholidays.common.utils.ColourUtils;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(
            ForgeRegistries.MOB_EFFECTS,
            HappyHolidaysMod.MOD_ID
    );

    public static final RegistryObject<MobEffect> SPIRIT_OF_CHRISTMAS =
            registerEffect("spirit_of_christmas", () -> new HappyHolidaysEffect(MobEffectCategory.BENEFICIAL, ColourUtils.rgbToInt(0, 127, 14))
                    .addAttributeModifier(Attributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 2.0D, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.1F, AttributeModifier.Operation.MULTIPLY_TOTAL)
            );

    public static final RegistryObject<MobEffect> GENEROSITY_OF_CHRISTMAS =
            registerEffect("generosity_of_christmas", () -> new HappyHolidaysEffect(MobEffectCategory.NEUTRAL, ColourUtils.rgbToInt(255, 0, 0)));

    public static RegistryObject<MobEffect> registerEffect(String effectId, Supplier<MobEffect> mobEffect) {
        return EFFECTS.register(effectId, mobEffect);
    }
}
