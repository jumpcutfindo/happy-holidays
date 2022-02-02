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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MilkAndCookiesBlock extends ChristmasFoodBlock {
    public static final int MAX_BITES = 2;
    public static final int NUTRITION = 1;
    public static final float SATURATION = 0.2F;

    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, MAX_BITES);

    public static final String BLOCK_ID = "milk_and_cookies";

    public static final Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.GLASS)
                    .strength(0.25f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .randomTicks();

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .stacksTo(4)
                    .tab(HappyHolidaysTabs.HAPPY_HOLIDAYS_GROUP);

    public static final VoxelShape[] SHAPE = { Block.box(3.5, 0.0, 3.5, 12.5, 7.0, 12.5) };

    public MilkAndCookiesBlock() {
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
            if (i < MAX_BITES) {
                world.setBlock(blockPos, blockState.setValue(BITES, i + 1), 3);
                world.playSound(null, blockPos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.0f, 0.0f);
            } else {
                world.removeBlock(blockPos, false);
            }

            return InteractionResult.SUCCESS;
        }
    }
}
