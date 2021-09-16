package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.google.common.collect.ImmutableSet;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;

public class ChristmasFeatures {
    public static final RandomPatchConfiguration WILD_PRESENT_GENERATION_CONFIG =
            new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(ChristmasBlocks.BABY_PRESENT.get().defaultBlockState()), new SimpleBlockPlacer())
                    .tries(16)
                    .whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK))
                    .xspread(4)
                    .zspread(4)
                    .build();

    public static final ConfiguredFeature<?, ?> PATCH_WILD_PRESENT =
            Feature.RANDOM_PATCH.configured(WILD_PRESENT_GENERATION_CONFIG).rarity(96);
}
