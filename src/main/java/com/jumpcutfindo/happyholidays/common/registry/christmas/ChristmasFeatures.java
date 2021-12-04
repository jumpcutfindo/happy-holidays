package com.jumpcutfindo.happyholidays.common.registry.christmas;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ChristmasFeatures {
    public static final RandomPatchConfiguration WILD_PRESENT_GENERATION_CONFIG =
            new RandomPatchConfiguration(16, 4, 4, () -> {
                return Feature.SIMPLE_BLOCK.configured(
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ChristmasBlocks.BABY_PRESENT.get())))
                        .filtered(
                                BlockPredicate.allOf(
                                        BlockPredicate.replaceable(),
                                        BlockPredicate.matchesBlock(Blocks.GRASS_BLOCK, new BlockPos(0, -1, 0)),
                                        BlockPredicate.matchesBlock(Blocks.SAND, new BlockPos(0, -1, 0))
                                )
                        );
            });

    public static final ConfiguredFeature<?, ?> PATCH_WILD_PRESENT =
            Feature.RANDOM_PATCH.configured(WILD_PRESENT_GENERATION_CONFIG);
}
