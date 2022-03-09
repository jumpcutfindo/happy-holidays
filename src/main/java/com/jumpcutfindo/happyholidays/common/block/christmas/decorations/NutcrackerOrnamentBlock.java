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

public class NutcrackerOrnamentBlock extends DecorationBlock implements ChristmasLike, ChristmasBlock, LegendaryOrnament {
    public static final String BLOCK_ID = "nutcracker_ornament";

    public NutcrackerOrnamentBlock() {
        super();
    }

    @Override
    public @NotNull VoxelShape getFloorShape() {
        return Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
    }

    @Override
    public @NotNull VoxelShape getCeilingShape() {
        return Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
    }

    @Override
    public @NotNull VoxelShape getWallShape() {
        return Block.box(6.0, 0.0, 0.0, 10.0, 16.0, 4.0);
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.LEGENDARY;
    }
}
