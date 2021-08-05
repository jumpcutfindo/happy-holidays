package com.jumpcutfindo.happyholidays.common.sound.christmas;

import net.minecraft.client.audio.TickableSound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

public class MusicBoxSound extends TickableSound {
    private final int duration;
    private int currentTick = 0;

    public MusicBoxSound(SoundEvent music, int duration, BlockPos playPos) {
        super(music, SoundCategory.RECORDS);
        this.duration = duration;

        this.looping = false;
        this.delay = 2;
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

        if (++currentTick > this.duration) this.stop();
    }
}
