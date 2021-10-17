package com.jumpcutfindo.happyholidays.common.block.christmas.candy;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarBlockEntity;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarHelper;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public class BaseCandyCaneBlock extends ChristmasBlock {
    public static final Properties BLOCK_PROPERTIES = BlockBehaviour.Properties
            .of(Material.STONE)
            .strength(1.0f)
            .sound(SoundType.STONE);

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public static final double ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE = 0.005D;

    public BaseCandyCaneBlock(String blockId) {
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

    private enum DestroyReason {
        PLAYER, EXPLOSION, NATURAL_EXPLOSION
    }
}
