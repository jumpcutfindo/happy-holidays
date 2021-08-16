package com.jumpcutfindo.happyholidays.common.registry;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

public class FeatureRegistry {
    public static final BlockClusterFeatureConfig WILD_PRESENT_GENERATION_CONFIG =
            new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.BABY_PRESENT_BLOCK.get().defaultBlockState()), new SimpleBlockPlacer())
                    .tries(16)
                    .whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock()))
                    .xspread(4)
                    .zspread(4)
                    .build();

    public static final ConfiguredFeature<?, ?> PATCH_WILD_PRESENT =
            Feature.RANDOM_PATCH.configured(WILD_PRESENT_GENERATION_CONFIG).chance(96);
}
