package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CandyCaneOrnamentBlock extends DecorationBlock implements ChristmasBlock, LegendaryOrnament {
    public CandyCaneOrnamentBlock() {
        super();
    }

    @Override
    public @NotNull VoxelShape getFloorShape() {
        return Block.box(4.0, 0.0, 3.0, 12.0, 1.0, 13.0);
    }

    @Override
    public @NotNull VoxelShape getCeilingShape() {
        return Block.box(4.0, 2.0, 7.5, 12.0, 14.0, 8.5);
    }

    @Override
    public @NotNull VoxelShape getWallShape() {
        return Block.box(3.75, 0.0, 0.0, 11.0, 11.0, 3.0);
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }
}
