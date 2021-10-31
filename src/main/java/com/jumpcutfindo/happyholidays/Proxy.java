package com.jumpcutfindo.happyholidays;

import com.jumpcutfindo.happyholidays.common.item.christmas.music.ChristmasMusic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;

public interface Proxy {
    void initClient();

    void openGuideGUI(ItemStack itemStack);

    void playChristmasMusic(LevelAccessor level, BlockPos blockPos, ChristmasMusic christmasMusic);

    void stopChristmasMusic(LevelAccessor level, BlockPos blockPos);

    void playHappySantaSummoningSound(BlockPos blockPos);

    void stopHappySantaSummoningSound(BlockPos blockPos);
}
