package com.jumpcutfindo.happyholidays;

import com.jumpcutfindo.happyholidays.common.item.christmas.music.ChristmasMusic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;

public class CommonProxy implements Proxy {
    public CommonProxy() {
    }

    @Override
    public void initClient() {

    }

    public void openGuideGUI(ItemStack itemStack) {
    }

    @Override
    public void playChristmasMusic(LevelAccessor level, BlockPos blockPos, ChristmasMusic christmasMusic) {

    }

    @Override
    public void stopChristmasMusic(LevelAccessor level, BlockPos blockPos) {

    }
}
