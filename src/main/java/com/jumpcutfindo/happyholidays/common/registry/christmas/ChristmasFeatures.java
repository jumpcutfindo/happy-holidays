package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.List;

import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ChristmasFeatures {
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> PATCH_WILD_PRESENTS = Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ChristmasBlocks.BABY_PRESENT.get()))), List.of(Blocks.GRASS_BLOCK, Blocks.SAND)));
}
