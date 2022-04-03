package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BigBaubleOrnamentBlock extends DecorationBlock implements ChristmasBlock, BasicOrnament {
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
}
