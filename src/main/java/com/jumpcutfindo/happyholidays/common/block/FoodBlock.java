package com.jumpcutfindo.happyholidays.common.block;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.utils.BlockUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class FoodBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public FoodBlock(Properties properties) {
        super(properties);
    }

    @NotNull
    public abstract VoxelShape getShape();
    public abstract int getNutrition();
    public abstract float getSaturation();
    public abstract IntegerProperty getBitesProperty();
    public abstract int getBites(BlockState blockState);
    public abstract int getMaxBites();

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockReader, BlockPos blockPos,
                               CollisionContext context) {
        Direction direction = blockState.getValue(FACING);

        VoxelShape resultShape = this.getShape();

        if (direction == Direction.SOUTH) {
            return resultShape;
        } else if (direction == Direction.NORTH) {
            return BlockUtils.rotateShape(resultShape, Rotation.CLOCKWISE_180);
        } else if (direction == Direction.WEST) {
            return BlockUtils.rotateShape(resultShape, Rotation.CLOCKWISE_90);
        } else {
            return BlockUtils.rotateShape(resultShape, Rotation.COUNTERCLOCKWISE_90);
        }
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader world, BlockPos position) {
        return Block.canSupportCenter(world, position.relative(Direction.DOWN), Direction.UP);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos blockPos, Player playerEntity, InteractionHand hand, BlockHitResult rayTraceResult) {
        if (world.isClientSide) {
            ItemStack itemstack = playerEntity.getItemInHand(hand);
            if (this.eat(world, blockPos, blockState, playerEntity).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return this.eat(world, blockPos, blockState, playerEntity);
    }

    protected InteractionResult eat(LevelAccessor level, BlockPos blockPos, BlockState blockState, Player playerEntity) {
        if (!playerEntity.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            playerEntity.getFoodData().eat(this.getNutrition(), this.getSaturation());
            int i = this.getBites(blockState);
            if (i < this.getMaxBites() - 1) {
                level.setBlock(blockPos, blockState.setValue(this.getBitesProperty(), i + 1), 3);
                level.playSound(null, blockPos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.0f, 0.0f);
            } else {
                level.removeBlock(blockPos, false);
            }

            return InteractionResult.SUCCESS;
        }
    }
}
