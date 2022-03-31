package com.jumpcutfindo.happyholidays.common.registry.christmas;

import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ChristmasFeatures {
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> PATCH_WILD_PRESENTS = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(48, 7, 5, () -> {
        return Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ChristmasBlocks.BABY_PRESENT.get()))).onlyWhenEmpty();
    }));
}
