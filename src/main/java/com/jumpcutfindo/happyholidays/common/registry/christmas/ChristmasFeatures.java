package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.List;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ChristmasFeatures {
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> PATCH_WILD_PRESENTS = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(64, 5, 3, () -> {
        return Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ChristmasBlocks.BABY_PRESENT.get()))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlocks(List.of(Blocks.GRASS, Blocks.SAND))));
    }));
}
