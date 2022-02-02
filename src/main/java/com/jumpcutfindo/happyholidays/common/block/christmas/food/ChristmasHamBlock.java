package com.jumpcutfindo.happyholidays.common.block.christmas.food;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChristmasHamBlock extends ChristmasFoodBlock {
    public static final int MAX_BITES = 4;
    public static final int NUTRITION = 2;
    public static final float SATURATION = 0.2F;

    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, MAX_BITES - 1);

    public static final String BLOCK_ID = "christmas_ham";

    public static final Properties BLOCK_PROPERTIES =
            Properties
                    .of(Material.WOOL)
                    .strength(0.25f)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
                    .randomTicks();

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .stacksTo(4)
                    .tab(HappyHolidaysTabs.CHRISTMAS_GROUP);

    public static final VoxelShape[] SHAPE = { Block.box(1.0, 0.0, 1.0, 15.0, 10.0, 15.0) };

    public ChristmasHamBlock() {
        super(BLOCK_PROPERTIES, SHAPE, NUTRITION, SATURATION);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(BITES, 0)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(BITES, FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(BITES, 0)
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
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

    private InteractionResult eat(LevelAccessor world, BlockPos blockPos, BlockState blockState, Player playerEntity) {
        if (!playerEntity.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            playerEntity.getFoodData().eat(NUTRITION, SATURATION);
            int i = blockState.getValue(BITES);
            if (i < MAX_BITES - 1) {
                world.setBlock(blockPos, blockState.setValue(BITES, i + 1), 3);
                world.playSound(null, blockPos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.0f, 0.0f);
            } else {
                world.removeBlock(blockPos, false);
            }

            return InteractionResult.SUCCESS;
        }
    }
}
