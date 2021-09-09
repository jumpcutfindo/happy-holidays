package com.jumpcutfindo.happyholidays.common.block.christmas.misc;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.registry.TileEntityRegistry;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ChristmasStarBlock extends ChristmasBlock {
    public static final EnumProperty<ChristmasStarTier> STAR_TIER = EnumProperty.create("christmas_star_tier",
            ChristmasStarTier.class);
    public static final EnumProperty<Direction.Axis> HORIZONTAL_AXIS = BlockStateProperties.HORIZONTAL_AXIS;

    public static final String BLOCK_ID = "christmas_star";

    public static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.DECORATION)
                    .harvestLevel(-1)
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
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public static final VoxelShape SHAPE = VoxelShapes.or(
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
    public void configureBlock() {
        RenderTypeLookup.setRenderLayer(this, RenderType.cutout());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(STAR_TIER, HORIZONTAL_AXIS);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityRegistry.CHRISTMAS_STAR_ENTITY_TYPE.get().create();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState()
                .setValue(STAR_TIER, ChristmasStarTier.TIER_0)
                .setValue(HORIZONTAL_AXIS, context.getHorizontalDirection().getAxis());
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  IWorld world, BlockPos pos1, BlockPos pos2) {
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
    public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos,
                               ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos,
                                PlayerEntity playerEntity, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (world.isClientSide()) return ActionResultType.SUCCESS;
        else {
            TileEntity te = world.getBlockEntity(blockPos);
            if (te instanceof ChristmasStarTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) playerEntity, (ChristmasStarTileEntity) te, blockPos);
            }
            return ActionResultType.CONSUME;
        }
    }

    @Override
    public void playerWillDestroy(World world, BlockPos blockPos, BlockState blockState,
                                  PlayerEntity playerEntity) {
        super.playerWillDestroy(world, blockPos, blockState, playerEntity);
        TileEntity te = world.getBlockEntity(blockPos);
        if (te instanceof ChristmasStarTileEntity) {
            InventoryHelper.dropContents(world, blockPos, (ChristmasStarTileEntity) te);
        }
    }

    @Override
    public boolean propagatesSkylightDown(BlockState blockState, IBlockReader blockReader, BlockPos blockPos) {
        return blockState.getFluidState().isEmpty();
    }
}
