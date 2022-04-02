package com.jumpcutfindo.happyholidays.common.registry.christmas;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class ChristmasPlacements {
    public static final Holder<PlacedFeature> PATCH_WILD_PRESENTS =
            PlacementUtils.register("patch_wild_presents",
                    ChristmasFeatures.PATCH_WILD_PRESENTS,
                    RarityFilter.onAverageOnceEvery(100),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome());

    public static final Holder<PlacedFeature> PATCH_WILD_PRESENTS_COLD =
            PlacementUtils.register("patch_wild_presents_cold",
                    ChristmasFeatures.PATCH_WILD_PRESENTS,
                    RarityFilter.onAverageOnceEvery(30),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome());
}
