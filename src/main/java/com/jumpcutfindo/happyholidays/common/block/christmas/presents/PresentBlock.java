package com.jumpcutfindo.happyholidays.common.block.christmas.presents;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.registry.BlockRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ItemRegistry;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class PresentBlock extends ChristmasBlock implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.WOOL)
                    .harvestLevel(-1)
                    .strength(0.25f)
                    .sound(SoundType.WOOL)
                    .randomTicks();

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .stacksTo(64)
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public static final float GROWTH_PROBABILITY = 1.0f / 64.0f;

    private VoxelShape shape;

    public PresentBlock(String blockId, VoxelShape shape) {
        super(blockId, BLOCK_PROPERTIES, ITEM_PROPERTIES);
        this.shape = shape;

        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(WATERLOGGED);
    }

    @Override
    public void configureBlock() {
        RenderTypeLookup.setRenderLayer(this, RenderType.cutout());
    }

    @Override
    public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos,
                               ISelectionContext context) {
        return shape;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_,
                                        ISelectionContext p_220071_4_) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    }

    @Override
    public VoxelShape getVisualShape(BlockState p_230322_1_, IBlockReader p_230322_2_, BlockPos p_230322_3_,
                                     ISelectionContext p_230322_4_) {
        return shape;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  IWorld world, BlockPos pos1, BlockPos pos2) {
        if (blockState.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return this.canSurvive(blockState, world, pos1) ? blockState : Blocks.AIR.defaultBlockState();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean isWaterlogged = fluidstate.getType() == Fluids.WATER;

        BlockState blockState = this.defaultBlockState().setValue(WATERLOGGED, isWaterlogged);

        return canSurvive(blockState, context.getLevel(), context.getClickedPos()) ? blockState : null;
    }

    @Override
    public boolean canSurvive(BlockState blockState, IWorldReader world, BlockPos position) {
        return canSupportCenter(world, position.relative(Direction.DOWN), Direction.UP);
    }

    @Override
    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }

    public float getGrowthProbability(World world, BlockPos pos) {
        ChristmasStarTileEntity starTileEntity = ChristmasStarTileEntity.getStarInfluencingBlock(world, pos);

        if (starTileEntity == null) {
            return GROWTH_PROBABILITY;
        } else {
            return GROWTH_PROBABILITY + GROWTH_PROBABILITY * starTileEntity.getCurrentTier() * 0.2f;
        }
    }

    @Override
    public void playerDestroy(World world, PlayerEntity playerEntity, BlockPos blockPos, BlockState blockState, @Nullable TileEntity tileEntity, ItemStack itemStack) {
        playerEntity.awardStat(Stats.BLOCK_MINED.get(this));
        playerEntity.causeFoodExhaustion(0.005F);

        if (!world.isClientSide()) {
            LootContext.Builder lootContextBuilder =
                    (new LootContext.Builder((ServerWorld) world)).withRandom(world.random).withParameter(LootParameters.ORIGIN,
                            Vector3d.atCenterOf(blockPos)).withParameter(LootParameters.TOOL, playerEntity.getMainHandItem())
                            .withOptionalParameter(LootParameters.BLOCK_ENTITY, tileEntity);

            List<ItemStack> drops = this.getCustomDrops(blockState, blockPos, lootContextBuilder);
            for (ItemStack drop : drops) popResource(world, blockPos, drop);
            blockState.spawnAfterBreak((ServerWorld) world, blockPos, itemStack);
        }
    }

    public List<ItemStack> getCustomDrops(BlockState blockState, BlockPos blockPos, LootContext.Builder builder) {
        ResourceLocation resourceLocation = this.getLootTable();
        if (resourceLocation == LootTables.EMPTY) {
            return Collections.emptyList();
        } else {
            LootContext lootContext = builder.withParameter(LootParameters.BLOCK_STATE, blockState).create(LootParameterSets.BLOCK);

            ServerWorld serverWorld = lootContext.getLevel();
            LootTable lootTable = serverWorld.getServer().getLootTables().get(resourceLocation);

            ChristmasStarTileEntity starTileEntity = ChristmasStarTileEntity.getStarInfluencingBlock(serverWorld, blockPos);
            List<ItemStack> drops = lootTable.getRandomItems(lootContext);

            // Normal processing of drops (in lieu of item tag bug)
            for (ItemStack drop : drops) {
                if (ChristmasItem.isBasicOrnamentItem(drop)) {
                    if (blockState.is(BlockRegistry.BABY_PRESENT_BLOCK.get())) {
                        drop.setCount((RANDOM.nextInt(2 - 1) + 1) + 1);
                    } else if (blockState.is(BlockRegistry.ADULT_PRESENT_BLOCK.get())) {
                        drop.setCount((RANDOM.nextInt(4 - 2) + 1) + 2);
                    } else {
                        drop.setCount((RANDOM.nextInt(5 - 3) + 1) + 3);
                    }
                }
            }

            // Double drops if there is a bonus star nearby
            if (starTileEntity != null && starTileEntity.isBonusActive()) {
                for (ItemStack drop : drops) {
                    if (!ItemStack.isSame(drop, ItemRegistry.PRESENT_SCRAPS.get().getDefaultInstance()) && !ChristmasItem.isPresentItem(drop)) {
                        drop.setCount(drop.getCount() * 2);
                    }
                }
            }

            return drops;
        }
    }

    public static boolean canGrow(IWorld world, BlockState blockState, BlockPos blockPos) {
        return !world.getBlockState(blockPos.above()).is(BlockTags.LEAVES) && !blockState.isFaceSturdy(world, blockPos,
                Direction.UP);
    }
}
