package com.jumpcutfindo.happyholidays.common.block.cny.offering;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EffectReward extends OfferingReward {
    private final MobEffect effect;
    private final int effectLevel;
    private final int duration;

    private EffectReward(MobEffect effect, int effectLevel, int duration) {
        this.effect = effect;
        this.effectLevel = effectLevel;
        this.duration = duration;
    }

    @Override
    public void execute(Level level, Player player, BlockPos blockPos) {
        player.addEffect(new MobEffectInstance(effect, duration, effectLevel));
    }

    public static EffectReward withEffect(MobEffect effect, int effectLevel, int duration) {
        return new EffectReward(effect, effectLevel, duration);
    }
}
