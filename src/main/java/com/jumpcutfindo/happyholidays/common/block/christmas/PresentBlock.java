package com.jumpcutfindo.happyholidays.common.block.christmas;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.Holiday;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.ChristmasStarBlockEntity;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.ChristmasStarHelper;
import com.jumpcutfindo.happyholidays.common.entity.christmas.grinch.GrinchEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.server.data.HolidayAvailabilityData;
import com.jumpcutfindo.happyholidays.server.data.structs.Availability;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

public class PresentBlock extends Block implements SimpleWaterloggedBlock, ChristmasBlock {
    // Common information
    public static final Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.WOOL)
                    .strength(0.25f)
                    .sound(SoundType.WOOL)
                    .randomTicks();

    public static final float GROWTH_PROBABILITY = 1.0f / 64.0f;

    // Baby Present information
    public static final VoxelShape BABY_SHAPE = Shapes.or(
            Block.box(5.0, 0.0, 5.0, 11.0, 4.0, 11.0),
            Block.box(4.5, 4.0, 4.5, 11.5, 5.5 ,11.5)
    );

    // Adult Present information
    public static final VoxelShape ADULT_SHAPE = Shapes.or(
            box(4.0, 0.0, 4.0, 12.0, 6.0, 12.0),
            box(3.0, 6.0, 3.0, 13.0, 8.0, 13.0)
    );

    // Elder Present information
    public static final VoxelShape ELDER_SHAPE = Shapes.or(
            box(2.0, 0.0, 2.0, 14.0, 8.0, 14.0),
            box(1.0, 8.0, 1.0, 15.0, 11.0, 15.0)
    );

    // Block properties
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private final VoxelShape shape;
    public PresentBlock(VoxelShape shape) {
        super(BLOCK_PROPERTIES);
        this.shape = shape;

        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockReader, BlockPos blockPos,
                               CollisionContext context) {
        return shape;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, BlockGetter p_220071_2_, BlockPos p_220071_3_,
                                        CollisionContext p_220071_4_) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    }

    @Override
    public VoxelShape getVisualShape(BlockState p_230322_1_, BlockGetter p_230322_2_, BlockPos p_230322_3_,
                                     CollisionContext p_230322_4_) {
        return shape;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  LevelAccessor world, BlockPos pos1, BlockPos pos2) {
        if (blockState.getValue(WATERLOGGED)) {
            world.scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return this.canSurvive(blockState, world, pos1) ? blockState : Blocks.AIR.defaultBlockState();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean isWaterlogged = fluidstate.getType() == Fluids.WATER;

        BlockState blockState = this.defaultBlockState().setValue(WATERLOGGED, isWaterlogged);

        return canSurvive(blockState, context.getLevel(), context.getClickedPos()) ? blockState : null;
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader world, BlockPos position) {
        return canSupportCenter(world, position.relative(Direction.DOWN), Direction.UP);
    }

    @Override
    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    public boolean isPathfindable(BlockState p_196266_1_, BlockGetter p_196266_2_, BlockPos p_196266_3_, PathComputationType p_196266_4_) {
        return false;
    }

    public float getGrowthProbability(Level world, BlockPos pos) {
        ChristmasStarBlockEntity starBlockEntity = ChristmasStarHelper.getStarInfluencingBlock(world, pos);

        if (starBlockEntity == null) {
            return GROWTH_PROBABILITY;
        } else {
            return GROWTH_PROBABILITY + GROWTH_PROBABILITY * starBlockEntity.getCurrentTier() * 0.2f;
        }
    }

    @Override
    public void playerDestroy(Level world, Player playerEntity, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
        playerEntity.awardStat(Stats.BLOCK_MINED.get(this));
        playerEntity.causeFoodExhaustion(0.005F);

        if (!world.isClientSide()) {
            LootContext.Builder lootContextBuilder =
                    (new LootContext.Builder((ServerLevel) world)).withRandom(world.random).withParameter(LootContextParams.ORIGIN,
                            Vec3.atCenterOf(blockPos)).withParameter(LootContextParams.TOOL, playerEntity.getMainHandItem())
                            .withOptionalParameter(LootContextParams.BLOCK_ENTITY, blockEntity);

            List<ItemStack> drops = this.getCustomDrops(blockState, blockPos, lootContextBuilder);
            for (ItemStack drop : drops) popResource(world, blockPos, drop);
            blockState.spawnAfterBreak((ServerLevel) world, blockPos, itemStack);
        }
    }

    public List<ItemStack> getCustomDrops(BlockState blockState, BlockPos blockPos, LootContext.Builder builder) {
        ResourceLocation resourceLocation = this.getLootTable();
        if (resourceLocation == BuiltInLootTables.EMPTY) {
            return Collections.emptyList();
        } else {
            LootContext lootContext = builder.withParameter(LootContextParams.BLOCK_STATE, blockState).create(LootContextParamSets.BLOCK);

            ServerLevel serverWorld = lootContext.getLevel();
            LootTable lootTable = serverWorld.getServer().getLootTables().get(resourceLocation);

            ChristmasStarBlockEntity starBlockEntity = ChristmasStarHelper.getStarInfluencingBlock(serverWorld, blockPos);
            List<ItemStack> drops = lootTable.getRandomItems(lootContext);

            // Normal processing of drops (in lieu of item tag bug)
            for (ItemStack drop : drops) {
                if (ChristmasItems.isBasicOrnamentItem(drop)) {
                    if (blockState.is(ChristmasBlocks.BABY_PRESENT.get())) {
                        drop.setCount((RANDOM.nextInt(2 - 1) + 1) + 1);
                    } else if (blockState.is(ChristmasBlocks.ADULT_PRESENT.get())) {
                        drop.setCount((RANDOM.nextInt(4 - 2) + 1) + 2);
                    } else {
                        drop.setCount((RANDOM.nextInt(5 - 3) + 1) + 3);
                    }
                }
            }

            // Double drops if there is a bonus star nearby
            if (starBlockEntity != null && starBlockEntity.isBonusActive()) {
                for (ItemStack drop : drops) {
                    if (!ItemStack.isSame(drop, ChristmasItems.PRESENT_SCRAPS.get().getDefaultInstance()) && !ChristmasItems.isPresentItem(drop)) {
                        drop.setCount(drop.getCount() * 2);
                    }
                }
            }

            return drops;
        }
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverWorld, BlockPos blockPos, Random random) {
        super.randomTick(blockState, serverWorld, blockPos, random);

        if (!Availability.isAvailable(serverWorld, Holiday.CHRISTMAS, HolidayAvailabilityData.CHRISTMAS_PRESENTS_GROW)) return;

        // Handle growing for baby / adult presents
        if (PresentBlock.isGrowable(blockState)) {
            // Checks whether the block can grow on the current block and whether our randomised values allow it to grow
            boolean isGrow = PresentBlock.canGrow(serverWorld, blockState, blockPos)
                    && ForgeHooks.onCropsGrowPre(serverWorld, blockPos, blockState,
                    random.nextInt((int)(1.0 / this.getGrowthProbability(serverWorld, blockPos))) == 0);

            if (isGrow) PresentBlock.grow(blockState, serverWorld, blockPos, random);
        }

        // Handle Grinch spawning around the present
        double grinchSpawnChance = random.nextDouble();
        if (grinchSpawnChance <= GrinchEntity.GRINCH_SPAWN_CHANCE && GrinchEntity.canSpawnInArea(blockPos, serverWorld))
            GrinchEntity.spawnGrinchAround(blockPos, serverWorld, random);
    }

    public static void grow(BlockState blockState, ServerLevel serverWorld, BlockPos blockPos, Random random) {
        // Determine the next block state
        BlockState nextBlockState = blockState.is(ChristmasBlocks.BABY_PRESENT.get())
                ? ChristmasBlocks.ADULT_PRESENT.get().defaultBlockState()
                : ChristmasBlocks.ELDER_PRESENT.get().defaultBlockState();

        // Update the server world by replacing the block
        serverWorld.setBlock(blockPos, nextBlockState, 2);
        serverWorld.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.WOOL_BREAK,
                SoundSource.BLOCKS, 1.0F, 1.0F);
        serverWorld.sendParticles(ParticleTypes.CLOUD, blockPos.getX() + random.nextDouble(), blockPos.getY() + random.nextDouble(),
                blockPos.getZ() + random.nextDouble(), 1, 0D, 0D, 0D, 0.0D);
        ForgeHooks.onCropsGrowPost(serverWorld, blockPos, blockState);
    }

    public static boolean canGrow(LevelAccessor world, BlockState blockState, BlockPos blockPos) {
        return !world.getBlockState(blockPos.above()).is(BlockTags.LEAVES) && !blockState.isFaceSturdy(world, blockPos,
                Direction.UP);
    }

    public static boolean isGrowable(BlockState blockState) {
        return blockState.is(ChristmasBlocks.BABY_PRESENT.get()) || blockState.is(ChristmasBlocks.ADULT_PRESENT.get());
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }
}
