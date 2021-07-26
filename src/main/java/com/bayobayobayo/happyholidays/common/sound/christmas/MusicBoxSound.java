package com.bayobayobayo.happyholidays.common.sound.christmas;

import com.bayobayobayo.happyholidays.common.registry.SoundRegistry;

import net.minecraft.client.audio.TickableSound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

public class MusicBoxSound extends TickableSound {

    public MusicBoxSound(SoundEvent music, BlockPos playPos) {
        super(music, SoundCategory.RECORDS);
        this.looping = false;
        this.delay = 0;
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
        this.volume = 1.0f;
        this.pitch = 1.0f;
    }
}
