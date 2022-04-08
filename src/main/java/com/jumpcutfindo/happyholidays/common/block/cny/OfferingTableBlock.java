package com.jumpcutfindo.happyholidays.common.block.cny;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Maps;
import com.jumpcutfindo.happyholidays.common.block.entity.cny.OfferingTableBlockEntity;
import com.jumpcutfindo.happyholidays.common.registry.cny.CNYBlockEntities;
import com.jumpcutfindo.happyholidays.common.utils.BlockUtils;

import net.minecraft.Util;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class OfferingTableBlock extends HorizontalDirectionalBlock implements EntityBlock, CNYBlock {
    public static final EnumProperty<OfferingTablePart> PART = EnumProperty.create("part", OfferingTablePart.class);

    public static final Properties BLOCK_PROPERTIES = BlockBehaviour.Properties
            .of(Material.WOOD)
            .strength(1.0f)
            .sound(SoundType.WOOD);

    public static final Map<OfferingTablePart, VoxelShape> SHAPES = Util.make(Maps.newHashMap(), map -> {
        map.put(OfferingTablePart.LEFT, Shapes.or(
                Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                Block.box(1.0D, 0.0D, 14.0D, 3.0D, 14.0D, 16.0D),
                Block.box(1.0D, 0.0D, 1.0D, 3.0D, 14.0D, 3.0D)
        ));

        map.put(OfferingTablePart.CENTER, Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D));

        map.put(OfferingTablePart.RIGHT, Shapes.or(
                Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                Block.box(13.0D, 0.0D, 1.0D, 15.0D, 14.0D, 3.0D),
                Block.box(13.0D, 0.0D, 13.0D, 15.0D, 14.0D, 15.0D)
        ));
    });

    public OfferingTableBlock() {
        super(BLOCK_PROPERTIES);

        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutoutMipped());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinition) {
        stateDefinition.add(FACING, PART);
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
    public VoxelShape getShape(BlockState blockState, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPES.get(blockState.getValue(PART));
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
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(blockPos) instanceof OfferingTableBlockEntity blockEntity) {
            if (!player.getItemInHand(hand).isEmpty() && !blockEntity.hasItem()) {
                // Take an item from the stack in the hand and create a copy
                ItemStack handItem = player.getItemInHand(hand);
                ItemStack itemCopy = handItem.copy();
                itemCopy.setCount(1);
                handItem.shrink(1);

                // Set it as the item on the block
                blockEntity.setItem(itemCopy);
                return InteractionResult.SUCCESS;
            } else if (player.getItemInHand(hand).isEmpty() && blockEntity.hasItem()) {
                // Remove item from block and give player
                ItemStack itemOfBlock = blockEntity.removeItem();
                player.setItemInHand(hand, itemOfBlock);
            }

            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        return super.use(blockState, level, blockPos, player, hand, hitResult);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        super.playerWillDestroy(level, blockPos, blockState, player);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState p_60584_) {
        return PushReaction.DESTROY;
    }

    public boolean isPathfindable(BlockState p_49510_, BlockGetter p_49511_, BlockPos p_49512_, PathComputationType p_49513_) {
        return false;
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutoutMipped());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return CNYBlockEntities.OFFERING_TABLE.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createTableTicker(level, blockEntityType, CNYBlockEntities.OFFERING_TABLE.get());
    }

    protected static <T extends BlockEntity> BlockEntityTicker<T> createTableTicker(Level level, BlockEntityType<T> blockEntityType, BlockEntityType<? extends OfferingTableBlockEntity> otherEntityType) {
        return level.isClientSide() ? null : BlockUtils.createTickerHelper(blockEntityType, otherEntityType, OfferingTableBlockEntity::serverTick);
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
