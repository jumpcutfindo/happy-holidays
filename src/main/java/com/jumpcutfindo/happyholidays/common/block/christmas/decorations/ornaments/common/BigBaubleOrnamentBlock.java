package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.BasicOrnament;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BigBaubleOrnamentBlock extends DecorationBlock implements ChristmasLike, ChristmasBlock, BasicOrnament {
    public static final String BIG_RED_BAUBLE_ID = "big_red_bauble";
    public static final String BIG_BLUE_BAUBLE_ID = "big_blue_bauble";
    public static final String BIG_YELLOW_BAUBLE_ID = "big_yellow_bauble";
    public static final String BIG_GREEN_BAUBLE_ID = "big_green_bauble";
    public static final String BIG_GOLD_BAUBLE_ID = "big_gold_bauble";
    public static final String BIG_SILVER_BAUBLE_ID = "big_silver_bauble";

    public static final VoxelShape[][] BIG_BAUBLE_SHAPES = new VoxelShape[][] {
            new VoxelShape[] { Block.box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0) },
            new VoxelShape[] { Block.box(4.0, 3.75, 4.0, 12.0, 11.75, 12.0) },
            new VoxelShape[] { Block.box(4.0, 0.0, 0.0, 12.0, 11.75, 8.0) }
    };

    public BigBaubleOrnamentBlock() {
        super();
    }

    @Override
    public @NotNull VoxelShape getFloorShape() {
        return Block.box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
    }

    @Override
    public @NotNull VoxelShape getCeilingShape() {
        return Block.box(4.0, 3.75, 4.0, 12.0, 11.75, 12.0);
    }

    @Override
    public @NotNull VoxelShape getWallShape() {
        return Block.box(4.0, 0.0, 0.0, 12.0, 11.75, 8.0);
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.COMMON;
    }
}
