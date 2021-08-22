package com.jumpcutfindo.happyholidays.common.sound.christmas;

import com.jumpcutfindo.happyholidays.common.registry.SoundRegistry;

import net.minecraft.client.audio.TickableSound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

public class SantaSummonSound extends TickableSound {
    public SantaSummonSound(BlockPos playPos) {
        super(SoundRegistry.SANTA_SUMMONING.get(), SoundCategory.NEUTRAL);

        this.looping = true;

        this.volume = 1.0F;
        this.x = (double)((float) playPos.getX());
        this.y = (double)((float) playPos.getY());
        this.z = (double)((float) playPos.getZ());
    }

    public void stopTrack() {
        this.stop();
    }

    @Override
    public void tick() {

    }
}
