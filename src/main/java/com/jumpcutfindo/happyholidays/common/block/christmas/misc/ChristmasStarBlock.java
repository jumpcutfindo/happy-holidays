package com.jumpcutfindo.happyholidays.common.block.christmas.misc;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarBlockEntity;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarHelper;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;
import com.jumpcutfindo.happyholidays.common.utils.BlockUtils;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

public class ChristmasStarBlock extends Block implements EntityBlock, ChristmasBlock, ChristmasLike {
    public static final EnumProperty<ChristmasStarTier> STAR_TIER = EnumProperty.create("christmas_star_tier",
            ChristmasStarTier.class);
    public static final EnumProperty<Direction.Axis> HORIZONTAL_AXIS = BlockStateProperties.HORIZONTAL_AXIS;

    public static final String BLOCK_ID = "christmas_star";

    public static final Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.DECORATION)
                    .strength(1.0f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .noCollission()
                    .lightLevel(blockState -> {
                        ChristmasStarTier tier = blockState.getValue(STAR_TIER);
                        switch (tier) {
                        case TIER_1: return 4;
                        case TIER_2: return 6;
                        case TIER_3: return 8;
                        case TIER_4: return 10;
                        case TIER_5: return 14;
                        default: return 0;
                        }
                    });

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP);

    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(4.5, 0.0, 4.5, 11.5, 16.0, 11.5)
    );

    public ChristmasStarBlock() {
        super(BLOCK_PROPERTIES);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(STAR_TIER, ChristmasStarTier.TIER_0)
                .setValue(HORIZONTAL_AXIS, Direction.Axis.X)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(STAR_TIER, HORIZONTAL_AXIS);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ChristmasBlockEntities.CHRISTMAS_STAR_ENTITY_TYPE.get().create(pos, state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(STAR_TIER, ChristmasStarTier.TIER_0)
                .setValue(HORIZONTAL_AXIS, context.getHorizontalDirection().getAxis());
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  LevelAccessor world, BlockPos pos1, BlockPos pos2) {
        ChristmasStarTier tier = blockState.getValue(STAR_TIER);
        Direction.Axis axis = blockState.getValue(HORIZONTAL_AXIS);

        switch (tier) {
        case TIER_1: return this.defaultBlockState().setValue(STAR_TIER, ChristmasStarTier.TIER_1).setValue(HORIZONTAL_AXIS,
                axis);
        case TIER_2: return this.defaultBlockState().setValue(STAR_TIER, ChristmasStarTier.TIER_2).setValue(HORIZONTAL_AXIS,
                axis);
        case TIER_3: return this.defaultBlockState().setValue(STAR_TIER, ChristmasStarTier.TIER_3).setValue(HORIZONTAL_AXIS,
                axis);
        case TIER_4: return this.defaultBlockState().setValue(STAR_TIER, ChristmasStarTier.TIER_4).setValue(HORIZONTAL_AXIS,
                axis);
        case TIER_5: return this.defaultBlockState().setValue(STAR_TIER, ChristmasStarTier.TIER_5).setValue(HORIZONTAL_AXIS,
                axis);
        default: return this.defaultBlockState().setValue(STAR_TIER, ChristmasStarTier.TIER_0).setValue(HORIZONTAL_AXIS,
                axis);
        }
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockReader, BlockPos blockPos,
                               CollisionContext context) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos blockPos,
                                Player playerEntity, InteractionHand hand, BlockHitResult rayTraceResult) {
        if (world.isClientSide()) return InteractionResult.SUCCESS;
        else {
            BlockEntity te = world.getBlockEntity(blockPos);
            if (te instanceof ChristmasStarBlockEntity) {
                NetworkHooks.openGui((ServerPlayer) playerEntity, (ChristmasStarBlockEntity) te, blockPos);
            }
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos blockPos, BlockState blockState,
                                  Player playerEntity) {
        super.playerWillDestroy(world, blockPos, blockState, playerEntity);

        BlockEntity te = world.getBlockEntity(blockPos);
        if (te instanceof ChristmasStarBlockEntity) {
            Containers.dropContents(world, blockPos, (ChristmasStarBlockEntity) te);

            ChristmasStarHelper.removeStarLocation(blockPos);
        }
    }

    @Override
    public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockReader, BlockPos blockPos) {
        return blockState.getFluidState().isEmpty();
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
                                                                  BlockEntityType<T> blockEntityType) {
        return createStarTicker(level, blockEntityType, ChristmasBlockEntities.CHRISTMAS_STAR_ENTITY_TYPE.get());
    }

    protected static <T extends BlockEntity> BlockEntityTicker<T> createStarTicker(Level level, BlockEntityType<T> blockEntityType, BlockEntityType<? extends ChristmasStarBlockEntity> otherEntityType) {
        return level.isClientSide() ? null : BlockUtils.createTickerHelper(blockEntityType, otherEntityType, ChristmasStarBlockEntity::serverTick);
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.COMMON;
    }
}
