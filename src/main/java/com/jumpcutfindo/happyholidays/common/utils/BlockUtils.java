package com.jumpcutfindo.happyholidays.common.utils;

import java.util.Collection;

import javax.annotation.Nullable;

import org.apache.commons.compress.utils.Lists;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockUtils {
    public static boolean isWet(BlockState blockState) {
        return blockState.is(Blocks.WATER) || isWaterlogged(blockState);
    }

    public static boolean isWaterlogged(BlockState blockState) {
        return blockState.hasProperty(BlockStateProperties.WATERLOGGED) && blockState.getValue(BlockStateProperties.WATERLOGGED);
    }

    public static VoxelShape rotateShape(VoxelShape shape, Rotation rotation) {
        Collection<VoxelShape> rotatedShapes = Lists.newArrayList();

        shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
            rotatedShapes.add(BlockUtils.rotateBox(minX, minY, minZ, maxX, maxY, maxZ, rotation));
        });

        return Shapes.or(Shapes.empty(), rotatedShapes.toArray(new VoxelShape[0]));
    }

    public static VoxelShape rotateBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, Rotation rotation) {
        return rotateBox(Shapes.box(minX, minY, minZ, maxX, maxY, maxZ), rotation);
    }

    public static VoxelShape rotateBox(final VoxelShape shape, final Rotation rotationDir) {
        double x1 = shape.bounds().minX, x2 = shape.bounds().maxX;
        final double y1 = shape.bounds().minY, y2 = shape.bounds().maxY;
        double z1 = shape.bounds().minZ, z2 = shape.bounds().maxZ;

        if (rotationDir == Rotation.CLOCKWISE_90 || rotationDir == Rotation.COUNTERCLOCKWISE_90) {
            double temp = z1; // ]
            z1 = x1;   // ] x1 <-> z1
            x1 = temp; // ]

            temp = z2; // ]
            z2 = x2;   // ] x2 <-> z2
            x2 = temp; // ]
        }

        if (rotationDir == Rotation.CLOCKWISE_90 || rotationDir == Rotation.CLOCKWISE_180) {
            x1 = 1 - x1; // clockwise
            x2 = 1 - x2;
        }
        if (rotationDir == Rotation.COUNTERCLOCKWISE_90 || rotationDir == Rotation.CLOCKWISE_180) {
            z1 = 1 - z1; // counterclockwise
            z2 = 1 - z2;
        }

        return Shapes.box(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), Math.max(x1, x2), Math.max(y1, y2),
                Math.max(z1, z2));
    }

    public static VoxelShape combineShapes(VoxelShape[] voxelShapes) {
        VoxelShape result = Shapes.empty();

        for (VoxelShape shape : voxelShapes) {
            result = Shapes.join(result, shape, BooleanOp.OR);
        }

        return result;
    }

    public static BlockPos getPosInFront(Direction facingDirection, BlockPos pos, double offset) {
        BlockPos resultPos = pos;
        if (facingDirection == Direction.NORTH || facingDirection == Direction.UP || facingDirection == Direction.DOWN) {
            for (int i = 0; i < offset; i++) resultPos = resultPos.north();
        } else if (facingDirection == Direction.SOUTH) {
            for (int i = 0; i < offset; i++) resultPos = resultPos.south();
        } else if (facingDirection == Direction.EAST) {
            for (int i = 0; i < offset; i++) resultPos = resultPos.east();
        } else {
            for (int i = 0; i < offset; i++) resultPos = resultPos.west();
        }

        return resultPos;
    }

    public static BlockPos findBlockInRadius(LevelAccessor world, BlockPos currPos, Block block, int radius) {
        BlockPos startPos = currPos.offset(-radius, -radius, -radius);

        for (int x = 0; x < radius * 2; x++) {
            for (int y = 0; y < radius * 2; y++) {
                for (int z = 0; z < radius * 2; z++) {
                    BlockPos checkingPos = startPos.offset(x, y, z);
                    if (world.getBlockState(checkingPos).is(block)) return checkingPos;
                }
            }
        }

        return null;
    }

    public static boolean canPlace(BlockPlaceContext blockPlaceContext, BlockState blockState) {
        Player player = blockPlaceContext.getPlayer();
        CollisionContext collisioncontext = player == null ? CollisionContext.empty() : CollisionContext.of(player);
        return blockState.canSurvive(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos()) && blockPlaceContext.getLevel().isUnobstructed(blockState, blockPlaceContext.getClickedPos(), collisioncontext);
    }

    @Nullable
    public static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> p_152133_, BlockEntityType<E> p_152134_, BlockEntityTicker<? super E> p_152135_) {
        return p_152134_ == p_152133_ ? (BlockEntityTicker<A>)p_152135_ : null;
    }
}
