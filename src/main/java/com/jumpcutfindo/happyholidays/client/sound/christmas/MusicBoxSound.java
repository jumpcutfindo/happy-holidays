package com.jumpcutfindo.happyholidays.client.sound.christmas;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MusicBoxSound extends AbstractTickableSoundInstance {
    private final int duration;
    private int currentTick = 0;

    public MusicBoxSound(SoundEvent music, int duration, BlockPos playPos) {
        super(music, SoundSource.RECORDS);
        this.duration = duration;

        this.looping = false;
        this.delay = 2;
        this.volume = 4.0F;
        this.x = (double)((float) playPos.getX());
        this.y = (double)((float) playPos.getY());
        this.z = (double)((float) playPos.getZ());

        this.attenuation = Attenuation.LINEAR;
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
