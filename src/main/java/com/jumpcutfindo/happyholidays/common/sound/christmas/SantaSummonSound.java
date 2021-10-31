package com.jumpcutfindo.happyholidays.common.sound.christmas;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SantaSummonSound extends AbstractTickableSoundInstance {
    public SantaSummonSound(BlockPos playPos) {
        super(ChristmasSounds.SANTA_SUMMONING.get(), SoundSource.NEUTRAL);

        this.looping = true;

        this.volume = 4.0F;
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
