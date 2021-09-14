package com.jumpcutfindo.happyholidays.common.block.christmas.candy;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class BaseCandyCaneBlock extends ChristmasBlock {
    public static final Properties BLOCK_PROPERTIES = AbstractBlock.Properties
            .of(Material.STONE)
            .harvestLevel(-1)
            .strength(1.0f)
            .sound(SoundType.STONE)
            .harvestTool(ToolType.PICKAXE);

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public static final double ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE = 0.005D;

    public BaseCandyCaneBlock(String blockId) {
        super(BLOCK_PROPERTIES);
    }


    @Override
    public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion) {
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

        LootContext.Builder lootContextBuilder =
                (new LootContext.Builder((ServerWorld) world)).withRandom(world.random)
                        .withParameter(LootParameters.ORIGIN, Vector3d.atCenterOf(pos))
                        .withParameter(LootParameters.TOOL, Items.WOODEN_HOE.getDefaultInstance());
        List<ItemStack> drops;

        if (explosion.getSourceMob() != null) {
            drops = this.getCustomDrops(state, pos, lootContextBuilder, DestroyReason.NATURAL_EXPLOSION);
        } else {
            drops = this.getCustomDrops(state, pos, lootContextBuilder, DestroyReason.EXPLOSION);
        }

        for (ItemStack drop : drops) popResource(world, pos, drop);
    }

    @Override
    public void playerDestroy(World world, PlayerEntity playerEntity, BlockPos blockPos, BlockState blockState, @Nullable TileEntity tileEntity, ItemStack itemStack) {
        super.playerDestroy(world, playerEntity, blockPos, blockState, tileEntity, itemStack);

        if (!world.isClientSide()) {
            LootContext.Builder lootContextBuilder =
                    (new LootContext.Builder((ServerWorld) world)).withRandom(world.random).withParameter(LootParameters.ORIGIN,
                            Vector3d.atCenterOf(blockPos)).withParameter(LootParameters.TOOL, playerEntity.getMainHandItem()).withOptionalParameter(LootParameters.BLOCK_ENTITY, tileEntity);

            List<ItemStack> drops = this.getCustomDrops(blockState, blockPos, lootContextBuilder, DestroyReason.PLAYER);
            for (ItemStack drop : drops) popResource(world, blockPos, drop);
            blockState.spawnAfterBreak((ServerWorld) world, blockPos, itemStack);
        }
    }

    public List<ItemStack> getCustomDrops(BlockState blockState, BlockPos blockPos, LootContext.Builder builder, DestroyReason destroyReason) {
        ResourceLocation resourceLocation = this.getLootTable();
        if (resourceLocation == LootTables.EMPTY) {
            return Collections.emptyList();
        } else {
            LootContext lootContext = builder.withParameter(LootParameters.BLOCK_STATE, blockState).create(LootParameterSets.BLOCK);
            ServerWorld serverWorld = lootContext.getLevel();

            ChristmasStarTileEntity starTileEntity = ChristmasStarTileEntity.getStarInfluencingBlock(serverWorld, blockPos);
            List<ItemStack> drops = Lists.newArrayList();

            // Enchanted candy cane drop
            double chance = 0.0D;

            if (starTileEntity == null) {
                chance = ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE;
            } else if (!starTileEntity.isBonusActive()) {
                chance = ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE + ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE * starTileEntity.getCurrentTier() * 0.2;
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
