package com.bayobayobayo.happyholidays.common.block.christmas;

import com.bayobayobayo.happyholidays.common.RegistryHandler;
import com.bayobayobayo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class WildPresentBlock extends ChristmasBlock {
    private static final String BLOCK_ID = "wild_present_block";

    private static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.WOOL)
                    .harvestLevel(-1)
                    .strength(0.25f)
                    .sound(SoundType.WOOL)
                    .noOcclusion();

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ItemGroup.TAB_DECORATIONS);

    private static final VoxelShape SHAPE = VoxelShapes.or(
            HappyHolidaysUtils.createShape(2.0, 0.0, 2.0, 14.0, 8.0, 14.0),
            HappyHolidaysUtils.createShape(1.0, 8.0, 1.0, 15.0, 11.0, 15.0)
    );

    public WildPresentBlock() {
        super(BLOCK_ID, BLOCK_PROPERTIES, ITEM_PROPERTIES);
    }

    @Override
    public void registerBlock() {
        blockRegistryObject = RegistryHandler.BLOCKS.register(BLOCK_ID, () -> this);
        blockItemRegistryObject = RegistryHandler.ITEMS.register(
                BLOCK_ID, () -> new BlockItem(blockRegistryObject.get(), ITEM_PROPERTIES));
    }

    @Override
    public void configureBlock() {
        RenderTypeLookup.setRenderLayer(blockRegistryObject.get(), RenderType.translucent());
    }

    @Override
    public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos,
                               ISelectionContext context) {
        return SHAPE;
    }
}
