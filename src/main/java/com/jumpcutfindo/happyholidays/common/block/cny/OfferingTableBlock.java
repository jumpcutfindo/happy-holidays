package com.jumpcutfindo.happyholidays.common.block.cny;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;

public class OfferingTableBlock extends HorizontalDirectionalBlock {
    public static final EnumProperty<OfferingTablePart> PART = EnumProperty.create("part", OfferingTablePart.class);

    public static final Properties BLOCK_PROPERTIES = BlockBehaviour.Properties
            .of(Material.WOOD)
            .strength(1.0f)
            .sound(SoundType.WOOD);

    public OfferingTableBlock() {
        super(BLOCK_PROPERTIES);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();

        Direction direction = context.getHorizontalDirection();
        Direction facingDirection = direction.getOpposite();
        BlockPos blockpos = context.getClickedPos();

        BlockPos rightPos = blockpos.relative(direction.getClockWise());
        BlockPos leftPos = blockpos.relative(direction.getCounterClockWise());

        boolean canPlace = level.getBlockState(rightPos).canBeReplaced(context) && level.getWorldBorder().isWithinBounds(rightPos)
                && level.getBlockState(leftPos).canBeReplaced(context) && level.getWorldBorder().isWithinBounds(leftPos);

        if (canPlace) {
            level.setBlock(rightPos, this.defaultBlockState().setValue(PART, OfferingTablePart.RIGHT).setValue(FACING, facingDirection), 2);
            level.setBlock(leftPos, this.defaultBlockState().setValue(PART, OfferingTablePart.LEFT).setValue(FACING, facingDirection), 2);
        }

        return canPlace ? super.getStateForPlacement(context).setValue(PART, OfferingTablePart.CENTER).setValue(FACING, facingDirection) : null;
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        super.playerWillDestroy(level, blockPos, blockState, player);
    }

    @Override
    public BlockState updateShape(@NotNull BlockState blockState, Direction direction, BlockState otherBlockState, LevelAccessor level, BlockPos otherPos, BlockPos thisPos) {
        Direction facingDirection = blockState.getValue(FACING);

        switch (blockState.getValue(PART)) {
        case LEFT -> {
            BlockPos centre = thisPos.relative(facingDirection.getClockWise());
            if (centre.equals(otherPos) && otherBlockState.isAir()) return Blocks.AIR.defaultBlockState();
        }
        case RIGHT -> {
            BlockPos centre = thisPos.relative(facingDirection.getCounterClockWise());
            if (centre.equals(otherPos) && otherBlockState.isAir()) return Blocks.AIR.defaultBlockState();
        }
        case CENTER -> {
            BlockPos left = thisPos.relative(facingDirection.getCounterClockWise());
            BlockPos right = thisPos.relative(facingDirection.getClockWise());
            if ((left.equals(otherPos) || right.equals(otherPos)) && otherBlockState.isAir()) return Blocks.AIR.defaultBlockState();
        }
        }

        return super.updateShape(blockState, direction, blockState, level, thisPos, otherPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinition) {
        stateDefinition.add(FACING, PART);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState p_60584_) {
        return PushReaction.DESTROY;
    }

    public boolean isPathfindable(BlockState p_49510_, BlockGetter p_49511_, BlockPos p_49512_, PathComputationType p_49513_) {
        return false;
    }

    public enum OfferingTablePart implements StringRepresentable {
        LEFT("left"), CENTER("center"), RIGHT("right");

        private final String name;

        OfferingTablePart(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}
