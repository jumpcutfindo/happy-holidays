package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GingerbreadManOrnamentBlock extends DecorationBlock implements ChristmasBlock, LegendaryOrnament {
    public GingerbreadManOrnamentBlock() {
        super();
    }

    @Override
    public @NotNull VoxelShape getFloorShape() {
        return Block.box(2.5, 0.0, 2.5, 13.5, 1.0, 13.5);
    }

    @Override
    public @NotNull VoxelShape getCeilingShape() {
        return Block.box(2.5, 3.5, 5.5, 13.5, 14.5, 10.5);
    }

    @Override
    public @NotNull VoxelShape getWallShape() {
        return Block.box(3.0, 0.0, 0.0, 13.0, 10.0, 4.5);
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }
}
