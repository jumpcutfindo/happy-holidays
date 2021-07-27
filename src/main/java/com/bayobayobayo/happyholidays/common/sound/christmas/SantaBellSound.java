package com.bayobayobayo.happyholidays.common.sound.christmas;

import com.bayobayobayo.happyholidays.common.registry.SoundRegistry;

import net.minecraft.client.audio.TickableSound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class SantaBellSound extends TickableSound {
    public static final int DURATION = 40;
    private int currentTick = 0;

    private PlayerEntity player;

    public SantaBellSound(PlayerEntity player) {
        super(SoundRegistry.SANTA_ELF_BELL.get(), SoundCategory.PLAYERS);

        this.player = player;

        this.looping = false;
        this.volume = 1.0F;

        this.x = player.blockPosition().getX();
        this.y = player.blockPosition().getY();
        this.z = player.blockPosition().getZ();
    }

    @Override
    public void tick() {
        if (++currentTick > DURATION) {
            this.stop();
        } else {
            this.volume = 1.0F;
            this.x = player.blockPosition().getX();
            this.y = player.blockPosition().getY();
            this.z = player.blockPosition().getZ();
        }
    }

    public void stopSound() {
        this.stop();
    }
}
