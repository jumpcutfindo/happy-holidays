package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BaubleOrnamentBlock extends DecorationBlock implements ChristmasBlock, BasicOrnament {
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
}
