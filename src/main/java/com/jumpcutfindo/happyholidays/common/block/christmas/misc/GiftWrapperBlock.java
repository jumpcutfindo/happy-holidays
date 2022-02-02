package com.jumpcutfindo.happyholidays.common.block.christmas.misc;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.GiftWrapperBlockEntity;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;

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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

public class GiftWrapperBlock extends Block implements EntityBlock, ChristmasBlock, ChristmasLike {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final String BLOCK_ID = "gift_wrapping_station";

    public static final Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.WOOD)
                    .strength(0.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion();

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP);

    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(2.0, 0.0, 2.0, 14.0, 2.0, 14.0),
            Block.box(5.0, 2.0, 5.0, 11.0, 12.0, 11.0),
            Block.box(0.0, 12.0, 0.0, 16.0, 16.0, 16.0)
    );

    public GiftWrapperBlock() {
        super(BLOCK_PROPERTIES);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
        );
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ChristmasBlockEntities.GIFT_WRAPPER_ENTITY_TYPE.get().create(pos, state);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos blockPos,
                                Player playerEntity, InteractionHand hand, BlockHitResult rayTraceResult) {
        if (world.isClientSide()) return InteractionResult.SUCCESS;
        else {
            BlockEntity te = world.getBlockEntity(blockPos);
            if (te instanceof GiftWrapperBlockEntity) {
                NetworkHooks.openGui((ServerPlayer) playerEntity, (GiftWrapperBlockEntity) te, blockPos);
            }
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos blockPos, BlockState blockState,
                                  Player playerEntity) {
        super.playerWillDestroy(world, blockPos, blockState, playerEntity);
        BlockEntity te = world.getBlockEntity(blockPos);
        if (te instanceof GiftWrapperBlockEntity) {
            Containers.dropContents(world, blockPos, (GiftWrapperBlockEntity) te);
        }
    }


    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockReader, BlockPos blockPos,
                               CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutoutMipped());
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.COMMON;
    }
}
