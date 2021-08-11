package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.wall;

import java.util.Random;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.WallOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.handlers.modules.ModuleHandler;
import com.jumpcutfindo.happyholidays.common.registry.BlockRegistry;
import com.jumpcutfindo.happyholidays.common.registry.TileEntityRegistry;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.StockingTileEntity;
import com.jumpcutfindo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class StockingBlock extends WallOrnamentBlock {
    public static final BooleanProperty FILLED = BooleanProperty.create("filled");

    public static final String BLOCK_ID = "stocking_block";

    public static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0 ,16.0, 16.0,0.5);

    public StockingBlock() {
        super(BLOCK_ID, SHAPE);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(FILLED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(FILLED);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context).setValue(FILLED, false);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  IWorld world, BlockPos pos1, BlockPos pos2) {
        return this.canSurvive(blockState, world, pos1) ? blockState : Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canSurvive(BlockState blockState, IWorldReader world, BlockPos position) {
        Direction direction = blockState.getValue(FACING).getOpposite();

        return direction == Direction.NORTH ? !(world.getBlockState(position.north()).isAir())
                : direction == Direction.SOUTH ? !(world.getBlockState(position.south()).isAir())
                : direction == Direction.EAST ? !(world.getBlockState(position.east()).isAir())
                : direction == Direction.WEST && !(world.getBlockState(position.west()).isAir());
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult rayTraceResult) {
        TileEntity tileEntity = world.getBlockEntity(blockPos);

        if (!world.isClientSide() && tileEntity instanceof StockingTileEntity) {
            StockingTileEntity stockingTileEntity = (StockingTileEntity) tileEntity;

            if (!stockingTileEntity.isEmpty()) {
                stockingTileEntity.dropStockingItems();
            }

            return ActionResultType.SUCCESS;
        }

        return ActionResultType.sidedSuccess(world.isClientSide());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityRegistry.STOCKING_ENTITY_TYPE.get().create();
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return true;
    }

    @Override
    public void randomTick(BlockState blockState, ServerWorld world, BlockPos blockPos, Random random) {
        TileEntity tileEntity = world.getBlockEntity(blockPos);

        if (tileEntity instanceof StockingTileEntity) {
            StockingTileEntity stockingTileEntity = (StockingTileEntity) tileEntity;

            if (world.isNight() && !stockingTileEntity.isDoneForNight() && stockingTileEntity.isEmpty()) {
                int randInt = random.nextInt(100);
                if (randInt < StockingBlock.getFillChance(world, blockPos)) {
                    stockingTileEntity.fillStocking();
                }
            }

            if (world.isDay() && stockingTileEntity.isDoneForNight()) stockingTileEntity.resetStocking();
        }
    }

    /*
        Calculates the chance at which the stocking will fill with a present. Note that the value returned is out of
        100, and the final chance is calculated / 100.
     */
    public static int getFillChance(World world, BlockPos blockPos) {
        int chance = 0, baseChance = 10;

        chance += baseChance;

        boolean isCookiesNear = HappyHolidaysUtils.findBlockInRadius(world, blockPos,
                BlockRegistry.MILK_AND_COOKIES_BLOCK.get(), 5) != null;

        chance += isCookiesNear ? 20 : 0;

        return chance;
    }
}
