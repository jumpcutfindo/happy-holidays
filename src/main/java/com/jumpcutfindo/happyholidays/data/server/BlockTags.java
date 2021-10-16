package com.jumpcutfindo.happyholidays.data.server;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTags extends BlockTagsProvider {
    public BlockTags(DataGenerator p_126511_, @Nullable ExistingFileHelper existingFileHelper) {
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

        tag(ChristmasTags.Blocks.DECORATIONS)
                .add(ChristmasBlocks.CHRISTMAS_WREATH.get());

        tag(ChristmasTags.Blocks.HEAD_ORNAMENTS)
                .add(ChristmasBlocks.CREEPER_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.SKELETON_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.WITHER_SKELETON_HEAD_ORNAMENT.get())
                .add(ChristmasBlocks.SKELETON_HEAD_ORNAMENT.get());

        tag(ChristmasTags.Blocks.GINGERBREAD_BLOCKS)
                .add(ChristmasBlocks.GINGERBREAD_BLOCK.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get())

                .add(ChristmasBlocks.GINGERBREAD_STAIRS.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_STAIRS.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS.get())

                .add(ChristmasBlocks.GINGERBREAD_SLAB.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_SLAB.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_SLAB.get())

                .add(ChristmasBlocks.GINGERBREAD_WALL.get())
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_WALL.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_WALL.get());

        tag(ChristmasTags.Blocks.CANDY_CANE_BLOCKS)
                .add(ChristmasBlocks.CANDY_CANE_BLOCK.get())
                .add(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get());
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
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_WALL.get());

        tag(net.minecraft.tags.BlockTags.STAIRS)
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_STAIRS.get())
                .add(ChristmasBlocks.GINGERBREAD_STAIRS.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS.get());

        tag(net.minecraft.tags.BlockTags.SLABS)
                .add(ChristmasBlocks.GINGERBREAD_DOUGH_SLAB.get())
                .add(ChristmasBlocks.GINGERBREAD_SLAB.get())
                .add(ChristmasBlocks.SOGGY_GINGERBREAD_SLAB.get());

    }
}
