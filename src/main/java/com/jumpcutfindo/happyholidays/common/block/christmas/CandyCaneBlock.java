package com.jumpcutfindo.happyholidays.common.block.christmas;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.ChristmasStarBlockEntity;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.ChristmasStarHelper;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.utils.MathUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public class CandyCaneBlock extends Block implements ChristmasBlock {
    public static final Properties BLOCK_PROPERTIES = BlockBehaviour.Properties
            .of(Material.STONE)
            .strength(1.0f)
            .sound(SoundType.STONE);

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP);

    public static final String BLOCK_ID = "candy_cane_block";
    public static final String BRICKS_ID = "candy_cane_bricks";
    public static final String TILES_ID = "candy_cane_tiles";

    public static final double ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE = 0.005D;

    public CandyCaneBlock() {
        super(BLOCK_PROPERTIES);
    }

    @Override
    public void onBlockExploded(BlockState state, Level level, BlockPos pos, Explosion explosion) {
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

        List<ItemStack> drops;

        if (explosion.getSourceMob() != null) {
            drops = this.getCustomDrops(state, pos, level, DestroyReason.NATURAL_EXPLOSION);
        } else {
            drops = this.getCustomDrops(state, pos, level, DestroyReason.EXPLOSION);
        }

        for (ItemStack drop : drops) popResource(level, pos, drop);
    }

    @Override
    public void playerDestroy(Level world, Player playerEntity, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
        super.playerDestroy(world, playerEntity, blockPos, blockState, blockEntity, itemStack);
    }

    public List<ItemStack> getCustomDrops(BlockState blockState, BlockPos blockPos, Level level, DestroyReason destroyReason) {
        ResourceLocation resourceLocation = this.getLootTable();
        if (resourceLocation == BuiltInLootTables.EMPTY) {
            return Collections.emptyList();
        } else {
            ServerLevel serverLevel = (ServerLevel) level;

            ChristmasStarBlockEntity starBlockEntity = ChristmasStarHelper.getStarInfluencingBlock(serverLevel,
                    blockPos);
            List<ItemStack> drops = Lists.newArrayList();

            // Enchanted candy cane drop
            double chance = 0.0D;

            if (starBlockEntity == null) {
                chance = ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE;
            } else if (!starBlockEntity.isBonusActive()) {
                chance = ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE + ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE * starBlockEntity.getCurrentTier() * 0.2;
            } else {
                chance = ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE * 3;
            }

            switch (destroyReason) {
                case EXPLOSION: {
                    chance *= 2.0D;
                    break;
                }
                case NATURAL_EXPLOSION: {
                    chance *= 5.0D;
                    break;
                }
            }

            if (RANDOM.nextDouble() <= chance) drops.add(ChristmasItems.ENCHANTED_CANDY_CANE.get().getDefaultInstance());

            return drops;
        }
    }

    @Override
    public void configure() {
    }

    public static BlockState updateFestiveBlockForPlacement(BlockPlaceContext blockPlaceContext, BlockState expectedBlockState) {
        if (expectedBlockState == null) {
            return expectedBlockState;
        } else if (!expectedBlockState.hasProperty(FestiveCandyCaneBlock.CANDY_SHAPE)) {
            HappyHolidaysMod.LOGGER.debug("Non-festive block was passed into this method!");
            return expectedBlockState;
        }

        boolean shouldChange = false;
        BlockState resultantBlockState = expectedBlockState.setValue(FestiveCandyCaneBlock.CANDY_SHAPE, FestiveCandyShape.O_X);

        BlockPos blockPos = blockPlaceContext.getClickedPos();

        if (MathUtils.isEven(blockPos.getX()) && MathUtils.isEven(blockPos.getZ()) || MathUtils.isOdd(blockPos.getX()) && MathUtils.isOdd(blockPos.getZ())) {
            shouldChange = true;
        }

        if (MathUtils.isOdd(blockPos.getY())) shouldChange = !shouldChange;

        if (shouldChange) resultantBlockState = resultantBlockState.setValue(FestiveCandyCaneBlock.CANDY_SHAPE, FestiveCandyShape.X_O);

        return resultantBlockState;
    }

    private enum DestroyReason {
        PLAYER, EXPLOSION, NATURAL_EXPLOSION
    }
}
