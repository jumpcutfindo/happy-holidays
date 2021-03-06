package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GrinchOrnamentBlock extends DecorationBlock implements ChristmasBlock, LegendaryOrnament {
    public GrinchOrnamentBlock() {
        super();
    }

    @Override
    public @NotNull VoxelShape getFloorShape() {
        return Shapes.or(
                Block.box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0),
                Block.box(4.0, 4.0, 3.0, 12.0, 10.0, 13.0)
        );
    }

    @Override
    public @NotNull VoxelShape getCeilingShape() {
        return Shapes.or(
                Block.box(3.0, 3.75, 3.0, 13.0, 7.75, 13.0),
                Block.box(4.0, 7.75, 3.0, 12.0, 13.75, 13.0)
        );
    }

    @Override
    public @NotNull VoxelShape getWallShape() {
        return Shapes.or(
                Block.box(3.0, 0.0, 0.0, 13.0, 11.0, 10.0)
        );
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }
}
