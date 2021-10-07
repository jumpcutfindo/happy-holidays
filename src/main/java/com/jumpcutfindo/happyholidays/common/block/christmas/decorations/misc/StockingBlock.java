package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.events.christmas.StockingEvent;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.StockingBlockEntity;
import com.jumpcutfindo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.MinecraftForge;

public class StockingBlock extends WallDecorationBlock implements EntityBlock {
    public static final String RED_STOCKING_ID = "red_stocking";
    public static final String BLUE_STOCKING_ID = "blue_stocking";
    public static final String YELLOW_STOCKING_ID = "yellow_stocking";
    public static final String GREEN_STOCKING_ID = "green_stocking";
    public static final String GOLD_STOCKING_ID = "gold_stocking";
    public static final String SILVER_STOCKING_ID = "silver_stocking";

    public static final BooleanProperty ENCHANTED = BooleanProperty.create("enchanted");
    public static final BooleanProperty FILLED = BooleanProperty.create("filled");

    public static final Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.WOOL)
                    .strength(0.1f)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
                    .noCollission();

    public static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0 ,16.0, 16.0,0.5);

    public StockingBlock() {
        super(BLOCK_PROPERTIES, SHAPE);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(FILLED, false)
                .setValue(ENCHANTED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(ENCHANTED, FILLED);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(FILLED, false).setValue(ENCHANTED, false);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  LevelAccessor world, BlockPos pos1, BlockPos pos2) {
        return this.canSurvive(blockState, world, pos1) ? blockState : Blocks.AIR.defaultBlockState();
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos blockPos, Player playerEntity, InteractionHand hand, BlockHitResult rayTraceResult) {
        BlockEntity blockEntity = world.getBlockEntity(blockPos);

        if (!world.isClientSide() && blockEntity instanceof StockingBlockEntity) {
            StockingBlockEntity stockingBlockEntity = (StockingBlockEntity) blockEntity;

            if (!stockingBlockEntity.isEmpty()) {
                stockingBlockEntity.dropStockingItems();
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ChristmasBlockEntities.STOCKING_ENTITY_TYPE.get().create(pos, state);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return true;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel world, BlockPos blockPos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(blockPos);

        if (blockEntity instanceof StockingBlockEntity) {
            StockingBlockEntity stockingBlockEntity = (StockingBlockEntity) blockEntity;

            if (world.isNight() && !stockingBlockEntity.isDoneForNight() && stockingBlockEntity.isEmpty()) {
                int randInt = random.nextInt(100);
                if (randInt < StockingBlock.getFillChance(world, blockPos)) {
                    stockingBlockEntity.fillStocking();

                    AABB searchBox = new AABB(blockPos).inflate(4.0D);
                    List<Player> playersAround = world.getEntitiesOfClass(Player.class, searchBox);

                    for (Player playerEntity : playersAround) {
                        StockingEvent fillEvent = new StockingEvent.Fill(blockState, blockPos, playerEntity);
                        MinecraftForge.EVENT_BUS.post(fillEvent);
                    }
                }
            }

            if (world.isDay() && stockingBlockEntity.isDoneForNight()) stockingBlockEntity.resetStocking();
        }
    }

    /*
        Calculates the chance at which the stocking will fill with a present. Note that the value returned is out of
        100, and the final chance is calculated / 100.
     */
    public static int getFillChance(Level world, BlockPos blockPos) {
        int chance = 0, baseChance = 10;

        chance += baseChance;

        boolean isCookiesNear = HappyHolidaysUtils.findBlockInRadius(world, blockPos,
                ChristmasBlocks.MILK_AND_COOKIES.get(), 5) != null;

        chance += isCookiesNear ? 20 : 0;

        return chance;
    }
}
