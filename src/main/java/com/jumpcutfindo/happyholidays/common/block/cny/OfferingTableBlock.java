package com.jumpcutfindo.happyholidays.common.block.cny;

import java.util.Map;
import java.util.Random;

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
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
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
import net.minecraft.world.phys.Vec3;
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

    private static final Map<Direction, Vec3> SMALL_CANDLE_POS = Util.make(Maps.newHashMap(), map -> {
        Vec3 initialPos = new Vec3(0.195D, 1.35D, 0.73D);

        map.put(Direction.NORTH, initialPos);
        map.put(Direction.SOUTH, new Vec3(1.0D - initialPos.x, initialPos.y, 1.0D - initialPos.z));
        map.put(Direction.EAST, new Vec3(1.0D - initialPos.z, initialPos.y, initialPos.x));
        map.put(Direction.WEST, new Vec3(initialPos.z, initialPos.y, 1.0D - initialPos.x));
    });

    private static final Map<Direction, Vec3> MEDIUM_CANDLE_POS = Util.make(Maps.newHashMap(), map -> {
        Vec3 initialPos = new Vec3(0.12D, 1.48D, 0.77D);

        map.put(Direction.NORTH, initialPos);
        map.put(Direction.SOUTH, new Vec3(1.0D - initialPos.x, initialPos.y, 1.0D - initialPos.z));
        map.put(Direction.EAST, new Vec3(1.0D - initialPos.z, initialPos.y, initialPos.x));
        map.put(Direction.WEST, new Vec3(initialPos.z, initialPos.y, 1.0D - initialPos.x));
    });

    private static final Map<Direction, Vec3> LARGE_CANDLE_POS = Util.make(Maps.newHashMap(), map -> {
        Vec3 initialPos = new Vec3(0.18D, 1.5D, 0.84D);

        map.put(Direction.NORTH, initialPos);
        map.put(Direction.SOUTH, new Vec3(1.0D - initialPos.x, initialPos.y, 1.0D - initialPos.z));
        map.put(Direction.EAST, new Vec3(1.0D - initialPos.z, initialPos.y, initialPos.x));
        map.put(Direction.WEST, new Vec3(initialPos.z, initialPos.y, 1.0D - initialPos.x));
    });

    private static final Map<Direction, Vec3> JOSS_STICK_POS = Util.make(Maps.newHashMap(), map -> {
        Vec3 initialPos = new Vec3(0.85D, 1.5D, 0.75D);

        map.put(Direction.NORTH, initialPos);
        map.put(Direction.SOUTH, new Vec3(1.0D - initialPos.x, initialPos.y, 1.0D - initialPos.z));
        map.put(Direction.EAST, new Vec3(1.0D - initialPos.z, initialPos.y, initialPos.x));
        map.put(Direction.WEST, new Vec3(initialPos.z, initialPos.y, 1.0D - initialPos.x));
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
                && level.getBlockState(leftPos).canBeReplaced(context) && level.getWorldBorder().isWithinBounds(leftPos)
                && level.getBlockState(blockpos.above()).canBeReplaced(context) && level.getBlockState(leftPos.above()).canBeReplaced(context)
                && level.getBlockState(rightPos.above()).canBeReplaced(context);

        if (canPlace) {
            level.setBlock(rightPos, this.defaultBlockState().setValue(PART, OfferingTablePart.RIGHT).setValue(FACING, facingDirection), 2);
            level.setBlock(leftPos, this.defaultBlockState().setValue(PART, OfferingTablePart.LEFT).setValue(FACING, facingDirection), 2);
        }

        return canPlace ? super.getStateForPlacement(context).setValue(PART, OfferingTablePart.CENTER).setValue(FACING, facingDirection) : null;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        Direction direction = blockState.getValue(FACING);
        VoxelShape resultShape =  SHAPES.get(blockState.getValue(PART));

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
            if (!player.isCrouching() && player.getItemInHand(hand).is(Items.FLINT_AND_STEEL)) {
                blockEntity = (OfferingTableBlockEntity) level.getBlockEntity(getCentrePos(blockPos, blockState.getValue(FACING), blockState.getValue(PART)));
                if (blockEntity == null) return InteractionResult.FAIL;

                ItemStack flintSteel = player.getItemInHand(hand);

                blockEntity.lightTable(player,20);
                return InteractionResult.SUCCESS;
            }

            if (blockEntity.hasItem()) {
                ItemStack itemOfBlock = blockEntity.removeItem();
                ItemEntity itemEntity = new ItemEntity(level, blockPos.getX() + 0.5D, blockPos.getY() + 1.1D, blockPos.getZ() + 0.5D, itemOfBlock);
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
                level.playSound(player, blockPos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
            }

            if (!player.getItemInHand(hand).isEmpty() && !blockEntity.hasItem()) {
                // Take an item from the stack in the hand and create a copy
                ItemStack handItem = player.getItemInHand(hand);
                ItemStack itemCopy = handItem.copy();
                itemCopy.setCount(1);
                handItem.shrink(1);

                // Set it as the item on the block
                blockEntity.setItem(itemCopy);

                level.playSound(player, blockPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);

                return InteractionResult.SUCCESS;
            }

            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        return super.use(blockState, level, blockPos, player, hand, hitResult);
    }

    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, Random random) {
        if (blockState.getValue(PART) != OfferingTablePart.CENTER) return;

        if (level.getBlockEntity(blockPos) instanceof OfferingTableBlockEntity blockEntity && blockEntity.isTableLit()) {
            Direction direction = blockState.getValue(FACING);
            Vec3 blockPosition = new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ());

            Vec3 smallerCandleFlamePos = blockPosition.add(SMALL_CANDLE_POS.get(direction));
            Vec3 mediumCandleFlamePos = blockPosition.add(MEDIUM_CANDLE_POS.get(direction));
            Vec3 largeCandleFlamePos = blockPosition.add(LARGE_CANDLE_POS.get(direction));
            Vec3 jossSticksPos = blockPosition.add(JOSS_STICK_POS.get(direction));

            level.addParticle(ParticleTypes.SMALL_FLAME, smallerCandleFlamePos.x(), smallerCandleFlamePos.y(), smallerCandleFlamePos.z(), 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.SMALL_FLAME, mediumCandleFlamePos.x(), mediumCandleFlamePos.y(), mediumCandleFlamePos.z(), 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.SMALL_FLAME, largeCandleFlamePos.x(), largeCandleFlamePos.y(), largeCandleFlamePos.z(), 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.SMOKE, mediumCandleFlamePos.x(), mediumCandleFlamePos.y(), mediumCandleFlamePos.z(), 0.0D, 0.01D, 0.0D);

            level.addParticle(ParticleTypes.SMOKE, jossSticksPos.x(), jossSticksPos.y(), jossSticksPos.z(), 0.0D, 0.01D, 0.0D);
        }
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
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
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

    private static BlockPos getCentrePos(BlockPos pos, Direction facingDirection, OfferingTablePart part) {
        switch (part) {
        case LEFT -> {
            return pos.relative(facingDirection.getCounterClockWise());
        }
        case RIGHT -> {
            return pos.relative(facingDirection.getClockWise());
        }
        default -> {
            return pos;
        }
        }
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
