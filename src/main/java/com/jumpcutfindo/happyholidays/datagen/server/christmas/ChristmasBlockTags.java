package com.jumpcutfindo.happyholidays.datagen.server.christmas;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ChristmasBlockTags extends BlockTagsProvider {
    public ChristmasBlockTags(DataGenerator p_126511_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, HappyHolidaysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        modTags();
        minecraftTags();
    }

    private void modTags() {
        tag(ChristmasTags.Blocks.BAUBLES)
                .add(ChristmasBlocks.RED_BAUBLE.get())
                .add(ChristmasBlocks.BLUE_BAUBLE.get())
                .add(ChristmasBlocks.YELLOW_BAUBLE.get())
                .add(ChristmasBlocks.GREEN_BAUBLE.get())
                .add(ChristmasBlocks.GOLD_BAUBLE.get())
                .add(ChristmasBlocks.SILVER_BAUBLE.get());

        tag(ChristmasTags.Blocks.BIG_BAUBLES)
                .add(ChristmasBlocks.BIG_RED_BAUBLE.get())
                .add(ChristmasBlocks.BIG_BLUE_BAUBLE.get())
                .add(ChristmasBlocks.BIG_YELLOW_BAUBLE.get())
                .add(ChristmasBlocks.BIG_GREEN_BAUBLE.get())
                .add(ChristmasBlocks.BIG_GOLD_BAUBLE.get())
                .add(ChristmasBlocks.BIG_SILVER_BAUBLE.get());

        tag(ChristmasTags.Blocks.TINSEL)
                .add(ChristmasBlocks.RED_TINSEL.get())
                .add(ChristmasBlocks.BLUE_TINSEL.get())
                .add(ChristmasBlocks.YELLOW_TINSEL.get())
                .add(ChristmasBlocks.GREEN_TINSEL.get())
                .add(ChristmasBlocks.GOLD_TINSEL.get())
                .add(ChristmasBlocks.SILVER_TINSEL.get());

        tag(ChristmasTags.Blocks.CHRISTMAS_LIGHTS)
                .add(ChristmasBlocks.RED_CHRISTMAS_LIGHTS.get())
                .add(ChristmasBlocks.BLUE_CHRISTMAS_LIGHTS.get())
                .add(ChristmasBlocks.YELLOW_CHRISTMAS_LIGHTS.get())
                .add(ChristmasBlocks.GREEN_CHRISTMAS_LIGHTS.get())
                .add(ChristmasBlocks.GOLD_CHRISTMAS_LIGHTS.get())
                .add(ChristmasBlocks.SILVER_CHRISTMAS_LIGHTS.get());

        tag(ChristmasTags.Blocks.CHRISTMAS_BELLS)
                .add(ChristmasBlocks.RED_CHRISTMAS_BELLS.get())
                .add(ChristmasBlocks.BLUE_CHRISTMAS_BELLS.get())
                .add(ChristmasBlocks.YELLOW_CHRISTMAS_BELLS.get())
                .add(ChristmasBlocks.GREEN_CHRISTMAS_BELLS.get())
                .add(ChristmasBlocks.GOLD_CHRISTMAS_BELLS.get())
                .add(ChristmasBlocks.SILVER_CHRISTMAS_BELLS.get());

        tag(ChristmasTags.Blocks.STOCKINGS)
                .add(ChristmasBlocks.RED_STOCKING.get())
                .add(ChristmasBlocks.BLUE_STOCKING.get())
                .add(ChristmasBlocks.YELLOW_STOCKING.get())
                .add(ChristmasBlocks.GREEN_STOCKING.get())
                .add(ChristmasBlocks.GOLD_STOCKING.get())
                .add(ChristmasBlocks.SILVER_STOCKING.get());

        tag(ChristmasTags.Blocks.DECORATIONS)
                .add(ChristmasBlocks.CHRISTMAS_WREATH.get())
                .add(ChristmasBlocks.FROST.get());

        tag(ChristmasTags.Blocks.HEAD_ORNAMENTS)
                .add(ChristmasBlocks.CREEPER_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.SKELETON_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.WITHER_SKELETON_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.ZOMBIE_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.DROWNED_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.BLAZE_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.GHAST_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.PHANTOM_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.PIG_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.COW_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.CHICKEN_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.SHEEP_HEAD_ORNAMENT.get());

        tag(ChristmasTags.Blocks.GINGERBREAD_BLOCKS)
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_STAIRS.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_SLAB.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_WALL.get())

                .add(ChristmasBlocks.GINGERBREAD_BLOCK.get())
                .add(ChristmasBlocks.GINGERBREAD_STAIRS.get())
                .add(ChristmasBlocks.GINGERBREAD_SLAB.get())
                .add(ChristmasBlocks.GINGERBREAD_WALL.get())

                .add(ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_SLAB.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_WALL.get())

                .add(ChristmasBlocks.GINGERBREAD_DOUGH_BRICKS.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_STAIRS.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_SLAB.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_WALL.get())

                .add(ChristmasBlocks.GINGERBREAD_BRICKS.get())
                .add(ChristmasBlocks.GINGERBREAD_BRICK_STAIRS.get())
                .add(ChristmasBlocks.GINGERBREAD_BRICK_SLAB.get())
                .add(ChristmasBlocks.GINGERBREAD_BRICK_WALL.get())

                .add(ChristmasBlocks.SOGGY_GINGERBREAD_BRICKS.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_STAIRS.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_SLAB.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_WALL.get())

                .add(ChristmasBlocks.GINGERBREAD_DOUGH_TILES.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_STAIRS.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_SLAB.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_WALL.get())

                .add(ChristmasBlocks.GINGERBREAD_TILES.get())
                .add(ChristmasBlocks.GINGERBREAD_TILE_STAIRS.get())
                .add(ChristmasBlocks.GINGERBREAD_TILE_SLAB.get())
                .add(ChristmasBlocks.GINGERBREAD_TILE_WALL.get())

                .add(ChristmasBlocks.SOGGY_GINGERBREAD_TILES.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_STAIRS.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_SLAB.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_WALL.get());

        tag(ChristmasTags.Blocks.CANDY_CANE_BLOCKS)
                .add(ChristmasBlocks.CANDY_CANE_BLOCK.get())
                .add(ChristmasBlocks.CANDY_CANE_STAIRS.get())
                .add(ChristmasBlocks.CANDY_CANE_SLAB.get())
                .add(ChristmasBlocks.CANDY_CANE_WALL.get())

                .add(ChristmasBlocks.CANDY_CANE_BRICKS.get())
                .add(ChristmasBlocks.CANDY_CANE_BRICK_STAIRS.get())
                .add(ChristmasBlocks.CANDY_CANE_BRICK_SLAB.get())
                .add(ChristmasBlocks.CANDY_CANE_BRICK_WALL.get())

                .add(ChristmasBlocks.CANDY_CANE_TILES.get())
                .add(ChristmasBlocks.CANDY_CANE_TILE_STAIRS.get())
                .add(ChristmasBlocks.CANDY_CANE_TILE_SLAB.get())
                .add(ChristmasBlocks.CANDY_CANE_TILE_WALL.get())

                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_STAIRS.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_SLAB.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_WALL.get())

                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICKS.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_STAIRS.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_SLAB.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_WALL.get())

                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_TILES.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_STAIRS.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_SLAB.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_WALL.get());

        tag(ChristmasTags.Blocks.STAR_AFFECTED_BLOCKS)
                .add(ChristmasBlocks.CANDY_CANE_BLOCK.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get())
                .add(ChristmasBlocks.BABY_PRESENT.get())
                .add(ChristmasBlocks.ADULT_PRESENT.get())
                .add(ChristmasBlocks.ELDER_PRESENT.get());

        tag(ChristmasTags.Blocks.GINGERBREAD_MEN_SPAWNABLE_ON)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.SAND)
                .add(Blocks.STONE);

        tag(ChristmasTags.Blocks.NUTCRACKER_SPAWNABLE_ON)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.SAND)
                .add(Blocks.STONE)
                .add(Blocks.PODZOL);

        tag(ChristmasTags.Blocks.HEAT_EMITTING_BLOCKS)
                .add(Blocks.FIRE)
                .add(Blocks.SOUL_FIRE)
                .add(Blocks.CAMPFIRE)
                .add(Blocks.SOUL_CAMPFIRE)
                .add(Blocks.MAGMA_BLOCK)
                .add(Blocks.LAVA)
                .add(Blocks.LAVA_CAULDRON);
    }

    private void minecraftTags() {
        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE)
                .add(ChristmasBlocks.GIFT_WRAPPING_STATION.get());

        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_HOE)
                .addTag(ChristmasTags.Blocks.GINGERBREAD_BLOCKS);

        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(ChristmasTags.Blocks.CANDY_CANE_BLOCKS);

        tag(net.minecraft.tags.BlockTags.WALLS)
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_WALL.get())
                .add(ChristmasBlocks.GINGERBREAD_WALL.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_WALL.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_WALL.get())
                .add(ChristmasBlocks.GINGERBREAD_BRICK_WALL.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_WALL.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_WALL.get())
                .add(ChristmasBlocks.GINGERBREAD_TILE_WALL.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_WALL.get())
                .add(ChristmasBlocks.CANDY_CANE_WALL.get())
                .add(ChristmasBlocks.CANDY_CANE_BRICK_WALL.get())
                .add(ChristmasBlocks.CANDY_CANE_TILE_WALL.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_WALL.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_WALL.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_WALL.get());

        tag(net.minecraft.tags.BlockTags.STAIRS)
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_STAIRS.get())
                .add(ChristmasBlocks.GINGERBREAD_STAIRS.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_STAIRS.get())
                .add(ChristmasBlocks.GINGERBREAD_BRICK_STAIRS.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_STAIRS.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_STAIRS.get())
                .add(ChristmasBlocks.GINGERBREAD_TILE_STAIRS.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_STAIRS.get())
                .add(ChristmasBlocks.CANDY_CANE_STAIRS.get())
                .add(ChristmasBlocks.CANDY_CANE_BRICK_STAIRS.get())
                .add(ChristmasBlocks.CANDY_CANE_TILE_STAIRS.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_STAIRS.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_STAIRS.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_STAIRS.get());

        tag(net.minecraft.tags.BlockTags.SLABS)
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_SLAB.get())
                .add(ChristmasBlocks.GINGERBREAD_SLAB.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_SLAB.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_SLAB.get())
                .add(ChristmasBlocks.GINGERBREAD_BRICK_SLAB.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_SLAB.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_SLAB.get())
                .add(ChristmasBlocks.GINGERBREAD_TILE_SLAB.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_SLAB.get())
                .add(ChristmasBlocks.CANDY_CANE_SLAB.get())
                .add(ChristmasBlocks.CANDY_CANE_BRICK_SLAB.get())
                .add(ChristmasBlocks.CANDY_CANE_TILE_SLAB.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_SLAB.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_SLAB.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_SLAB.get());

    }
}
