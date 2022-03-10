package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.Holiday;
import com.jumpcutfindo.happyholidays.common.block.WallDecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.StockingBlockEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.StockingEvent;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.utils.BlockUtils;
import com.jumpcutfindo.happyholidays.server.data.HolidayAvailabilityData;
import com.jumpcutfindo.happyholidays.server.data.structs.Availability;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.minecraftforge.common.Tags;

public class StockingBlock extends WallDecorationBlock implements ChristmasBlock, EntityBlock {
    public static final BooleanProperty ENCHANTED = BooleanProperty.create("enchanted");
    public static final BooleanProperty FILLED = BooleanProperty.create("filled");

    public static final Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.WOOL)
                    .strength(0.1f)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
                    .noCollission();

    public StockingBlock() {
        super(BLOCK_PROPERTIES);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(FILLED, false)
                .setValue(ENCHANTED, false)
        );
    }

    @Override
    public @NotNull VoxelShape getVoxelShape() {
        return Block.box(0.0, 0.0, 0.0 ,16.0, 16.0,0.5);
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
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player playerEntity, InteractionHand hand, BlockHitResult rayTraceResult) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);

        if (!level.isClientSide() && blockEntity instanceof StockingBlockEntity) {
            StockingBlockEntity stockingBlockEntity = (StockingBlockEntity) blockEntity;

            if (blockState.getValue(ENCHANTED) && playerEntity.getItemInHand(hand).is(Tags.Items.SHEARS)) {
                // Drop the Enchanted Thread and revert to normal
                this.cutStocking((ServerLevel) level, blockPos, blockState, playerEntity, playerEntity.getItemInHand(hand));
            }

            if (!blockState.getValue(ENCHANTED) && playerEntity.getItemInHand(hand).is(ChristmasItems.ENCHANTED_THREAD.get())) {
                // Upgrade the stocking to its enchanted state
                this.upgradeStocking((ServerLevel) level, blockPos, blockState, playerEntity, playerEntity.getItemInHand(hand));
                StockingEvent.Upgrade upgradeEvent = new StockingEvent.Upgrade(blockState, blockPos, playerEntity);
                MinecraftForge.EVENT_BUS.post(upgradeEvent);
            }

            if (!stockingBlockEntity.isEmpty()) {
                stockingBlockEntity.dropStockingItems(blockState.getValue(ENCHANTED));
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    public void cutStocking(ServerLevel level, BlockPos blockPos, BlockState blockState, Player playerEntity, ItemStack shears) {
        level.setBlock(blockPos, blockState.setValue(ENCHANTED, false), 0);
        level.playSound(null, blockPos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0f, 1.0f);

        Block.popResource(level, blockPos, ChristmasItems.ENCHANTED_THREAD.get().getDefaultInstance());

        shears.hurtAndBreak(1, playerEntity, null);
    }

    public void upgradeStocking(ServerLevel level, BlockPos blockPos, BlockState blockState, Player playerEntity, ItemStack enchantedThreads) {
        // Apply enchanted effect
        level.setBlock(blockPos, blockState.setValue(ENCHANTED, true), 0);
        level.playSound(null, blockPos, SoundEvents.WOOL_BREAK, SoundSource.BLOCKS, 1.0f, 1.0f);

        level.sendParticles(ParticleTypes.COMPOSTER, blockPos.getX() + 0.5D, blockPos.getY() + 0.5D, blockPos.getZ() + 0.5D,
                5, 0.25D, 0.25D, 0.25D, 0.0D);

        if (!playerEntity.isCreative()) enchantedThreads.shrink(1);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ChristmasBlockEntities.STOCKING.get().create(pos, state);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return true;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel world, BlockPos blockPos, Random random) {
        if (!Availability.isAvailable(world, Holiday.CHRISTMAS, HolidayAvailabilityData.CHRISTMAS_STOCKINGS_FILL)) return;

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

        boolean isCookiesNear = BlockUtils.findBlockInRadius(world, blockPos,
                ChristmasBlocks.MILK_AND_COOKIES.get(), 5) != null;

        chance += isCookiesNear ? 20 : 0;

        return chance;
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }
}
