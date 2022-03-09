package com.jumpcutfindo.happyholidays.proxies.client;

import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.MusicBoxBlockEntity;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.ChristmasMusic;
import com.jumpcutfindo.happyholidays.common.sound.christmas.MusicBoxSound;
import com.jumpcutfindo.happyholidays.common.sound.christmas.SantaSummonSound;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

public class ChristmasProxy {
    private final Map<BlockPos, MusicBoxSound> christmasMusicMap = Maps.newHashMap();
    private final Map<UUID, SantaSummonSound> happySantaSummonSoundMap = Maps.newHashMap();

    public ChristmasProxy() {
    }

    public void playChristmasMusic(LevelAccessor level, BlockPos blockPos, ChristmasMusic christmasMusic) {
        if (level.getBlockEntity(blockPos) instanceof MusicBoxBlockEntity) {
            MusicBoxSound musicSound = new MusicBoxSound(ChristmasMusic.getSound(christmasMusic), ChristmasMusic.getSoundDuration(christmasMusic), blockPos);
            Minecraft.getInstance().getSoundManager().play(musicSound);

            christmasMusicMap.put(blockPos, musicSound);
        }
    }

    public void stopChristmasMusic(LevelAccessor level, BlockPos blockPos) {
        MusicBoxSound musicSound = christmasMusicMap.get(blockPos);
        if (musicSound != null) {
            musicSound.stopTrack();
            christmasMusicMap.remove(blockPos);
        }
    }

    public void playHappySantaSummoningSound(UUID santaUUID, BlockPos pos) {
        if (happySantaSummonSoundMap.containsKey(santaUUID)) return;

        SantaSummonSound summonSound = new SantaSummonSound(pos);
        happySantaSummonSoundMap.put(santaUUID, summonSound);
        Minecraft.getInstance().getSoundManager().play(summonSound);
    }

    public void stopHappySantaSummoningSound(UUID santaUUID) {
        SantaSummonSound summonSound = happySantaSummonSoundMap.get(santaUUID);
        if (summonSound != null) {
            summonSound.stopTrack();
            happySantaSummonSoundMap.remove(santaUUID);
        }
    }
}
