package com.jumpcutfindo.happyholidays.common.block.christmas.candy;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

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
    public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

        LootContext.Builder lootContextBuilder =
                (new LootContext.Builder((ServerLevel) world)).withRandom(world.random)
                        .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                        .withParameter(LootContextParams.TOOL, Items.WOODEN_HOE.getDefaultInstance());
        List<ItemStack> drops;

        if (explosion.getSourceMob() != null) {
            drops = this.getCustomDrops(state, pos, lootContextBuilder, DestroyReason.NATURAL_EXPLOSION);
        } else {
            drops = this.getCustomDrops(state, pos, lootContextBuilder, DestroyReason.EXPLOSION);
        }

        for (ItemStack drop : drops) popResource(world, pos, drop);
    }

    @Override
    public void playerDestroy(Level world, Player playerEntity, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity tileEntity, ItemStack itemStack) {
        super.playerDestroy(world, playerEntity, blockPos, blockState, tileEntity, itemStack);

        if (!world.isClientSide()) {
            LootContext.Builder lootContextBuilder =
                    (new LootContext.Builder((ServerLevel) world)).withRandom(world.random).withParameter(LootContextParams.ORIGIN,
                            Vec3.atCenterOf(blockPos)).withParameter(LootContextParams.TOOL, playerEntity.getMainHandItem()).withOptionalParameter(LootContextParams.BLOCK_ENTITY, tileEntity);

            List<ItemStack> drops = this.getCustomDrops(blockState, blockPos, lootContextBuilder, DestroyReason.PLAYER);
            for (ItemStack drop : drops) popResource(world, blockPos, drop);
            blockState.spawnAfterBreak((ServerLevel) world, blockPos, itemStack);
        }
    }

    public List<ItemStack> getCustomDrops(BlockState blockState, BlockPos blockPos, LootContext.Builder builder, DestroyReason destroyReason) {
        ResourceLocation resourceLocation = this.getLootTable();
        if (resourceLocation == BuiltInLootTables.EMPTY) {
            return Collections.emptyList();
        } else {
            LootContext lootContext = builder.withParameter(LootContextParams.BLOCK_STATE, blockState).create(LootContextParamSets.BLOCK);
            ServerLevel serverWorld = lootContext.getLevel();

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
