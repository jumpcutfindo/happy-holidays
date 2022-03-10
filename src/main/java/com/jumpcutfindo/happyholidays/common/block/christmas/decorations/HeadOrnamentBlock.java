package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HeadOrnamentBlock extends DecorationBlock implements ChristmasBlock, RareOrnament {
    public HeadOrnamentBlock() {
        super();
    }

    @Override
    public @NotNull VoxelShape getFloorShape() {
        return Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
    }

    @Override
    public @NotNull VoxelShape getCeilingShape() {
        return Block.box(4.0D, 4.5D, 4.0D, 12.0D, 12.5D, 12.0D);
    }

    @Override
    public @NotNull VoxelShape getWallShape() {
        return Block.box(4.0D, 0.0D, 0.0D, 12.0D, 10.0D, 8.0D);
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }
}
