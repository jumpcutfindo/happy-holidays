package com.jumpcutfindo.happyholidays.common.sound.christmas;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;

import net.minecraft.client.audio.TickableSound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;

public class SantaBellSound extends TickableSound {
    public static final int DURATION = 40;
    private int currentTick = 0;

    private PlayerEntity player;

    public SantaBellSound(PlayerEntity player) {
        super(ChristmasSounds.SANTA_ELF_BELL.get(), SoundCategory.PLAYERS);

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
