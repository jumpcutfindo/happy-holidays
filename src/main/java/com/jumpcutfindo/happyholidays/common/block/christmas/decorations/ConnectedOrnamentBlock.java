package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ConnectedOrnamentBlock extends MultifaceBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public ConnectedOrnamentBlock(Properties blockProperties, VoxelShape shape) {
        super(blockProperties);
    }

    @Override
    protected boolean isFaceSupported(Direction direction) {
        return direction != Direction.UP && direction != Direction.DOWN;
    }
}
