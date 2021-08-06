package com.jumpcutfindo.happyholidays.common.block.christmas.food;

import com.jumpcutfindo.happyholidays.common.handlers.modules.ModuleHandler;

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
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class LogCakeBlock extends ChristmasFoodBlock {
    public static final int MAX_BITES = 4;
    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, MAX_BITES - 1);

    public static final String BLOCK_ID = "log_cake_block";

    public static final Properties BLOCK_PROPERTIES =
            Properties
                    .of(Material.WOOL)
                    .harvestLevel(-1)
                    .strength(0.25f)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
                    .randomTicks();

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .stacksTo(4)
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public static final VoxelShape[] SHAPE = { Block.box(1.0, 0.0, 3.0, 15.0, 6.0, 13.0) };

    public LogCakeBlock() {
        super(BLOCK_ID, BLOCK_PROPERTIES, ITEM_PROPERTIES, SHAPE);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(BITES, 0)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(BITES, FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState()
                .setValue(BITES, 0)
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (world.isClientSide) {
            ItemStack itemstack = playerEntity.getItemInHand(hand);
            if (this.eat(world, blockPos, blockState, playerEntity).consumesAction()) {
                return ActionResultType.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return ActionResultType.CONSUME;
            }
        }

        return this.eat(world, blockPos, blockState, playerEntity);
    }

    private ActionResultType eat(IWorld world, BlockPos blockPos, BlockState blockState, PlayerEntity playerEntity) {
        if (!playerEntity.canEat(false)) {
            return ActionResultType.PASS;
        } else {
            playerEntity.getFoodData().eat(2, 0.2F);
            int i = blockState.getValue(BITES);
            if (i < MAX_BITES - 1) {
                world.setBlock(blockPos, blockState.setValue(BITES, i + 1), 3);
                world.playSound(null, blockPos, SoundEvents.GENERIC_EAT, SoundCategory.PLAYERS, 0.0f, 0.0f);
            } else {
                world.removeBlock(blockPos, false);
            }

            return ActionResultType.SUCCESS;
        }
    }
}
