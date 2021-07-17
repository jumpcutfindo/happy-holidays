package com.bayobayobayo.happyholidays.common.block.christmas.presents;

import java.util.Random;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.RegistryHandler;
import com.bayobayobayo.happyholidays.common.block.christmas.ChristmasBlock;
import com.bayobayobayo.happyholidays.common.handlers.ChristmasHandler;
import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;
import com.bayobayobayo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

public class PresentBlock extends ChristmasBlock {
    private static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.WOOL)
                    .harvestLevel(-1)
                    .strength(0.25f)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
                    .randomTicks();

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public static final float GROWTH_SPEED = 3.0f;

    private String blockId;
    private VoxelShape shape;


    public PresentBlock(String blockId, VoxelShape shape) {
        super(blockId, BLOCK_PROPERTIES, ITEM_PROPERTIES);

        this.blockId = blockId;
        this.shape = shape;
    }

    @Override
    public void registerBlock() {
        blockRegistryObject = RegistryHandler.BLOCKS.register(blockId, () -> this);
        blockItemRegistryObject = RegistryHandler.ITEMS.register(
                blockId, () -> new BlockItem(blockRegistryObject.get(), ITEM_PROPERTIES));
    }

    @Override
    public void configureBlock() {
        RenderTypeLookup.setRenderLayer(blockRegistryObject.get(), RenderType.translucent());
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
