package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BaubleOrnamentBlock extends DecorationBlock implements ChristmasLike, ChristmasBlock, BasicOrnament {
    public static final String RED_BAUBLE_ID = "red_bauble";
    public static final String BLUE_BAUBLE_ID = "blue_bauble";
    public static final String YELLOW_BAUBLE_ID = "yellow_bauble";
    public static final String GREEN_BAUBLE_ID = "green_bauble";
    public static final String GOLD_BAUBLE_ID = "gold_bauble";
    public static final String SILVER_BAUBLE_ID = "silver_bauble";

    public BaubleOrnamentBlock() {
        super();
    }

    @Override
    public @NotNull VoxelShape getFloorShape() {
        return Block.box(6.0, 0.0, 6.0, 10.0, 4.0, 10.0);
    }

    @Override
    public @NotNull VoxelShape getCeilingShape() {
        return Block.box(6.0, 9.5, 6.0, 10.0, 13.5, 10.0);
    }

    @Override
    public @NotNull VoxelShape getWallShape() {
        return Block.box(6.0, 0.0, 0.0, 10.0, 6.0, 4.0);
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
