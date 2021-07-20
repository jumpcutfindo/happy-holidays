package com.bayobayobayo.happyholidays.common.block.christmas.presents;

import com.bayobayobayo.happyholidays.common.block.christmas.ChristmasBlock;
import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class PresentBlock extends ChristmasBlock {
    public static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.WOOL)
                    .harvestLevel(-1)
                    .strength(0.25f)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
                    .randomTicks();

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public static final float GROWTH_SPEED = 3.0f;

    private VoxelShape shape;

    public PresentBlock(String blockId, VoxelShape shape) {
        super(blockId, BLOCK_PROPERTIES, ITEM_PROPERTIES);
        this.shape = shape;
    }

    @Override
    public void configureBlock() {
        RenderTypeLookup.setRenderLayer(this, RenderType.translucent());
    }

    @Override
    public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos,
                               ISelectionContext context) {
        return shape;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  IWorld world, BlockPos pos1, BlockPos pos2) {
        return this.canSurvive(blockState, world, pos1) ? blockState : Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canSurvive(BlockState blockState, IWorldReader world, BlockPos position) {
        return world.getBlockState(position.above()).is(BlockTags.LEAVES)
                || world.getBlockState(position.above().above()).is(BlockTags.LEAVES)
                || world.getBlockState(position.above().above().above()).is(BlockTags.LEAVES);
    }
}
