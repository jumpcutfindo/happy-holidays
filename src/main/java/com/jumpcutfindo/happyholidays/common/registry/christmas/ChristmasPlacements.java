package com.jumpcutfindo.happyholidays.common.registry.christmas;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class ChristmasPlacements {
    public static final PlacedFeature PATCH_WILD_PRESENTS =
            ChristmasFeatures.PATCH_WILD_PRESENTS.placed(
                    RarityFilter.onAverageOnceEvery(100),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()
            );
    public static final PlacedFeature PATCH_WILD_PRESENTS_COLD =
            ChristmasFeatures.PATCH_WILD_PRESENTS.placed(
                    RarityFilter.onAverageOnceEvery(30),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()
            );
}
