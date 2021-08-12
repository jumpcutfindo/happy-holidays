package com.jumpcutfindo.happyholidays.common.block.christmas.candy;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.registry.ItemRegistry;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        super(blockId, BLOCK_PROPERTIES, ITEM_PROPERTIES);
    }

    @Override
    public void playerDestroy(World world, PlayerEntity playerEntity, BlockPos blockPos, BlockState blockState, @Nullable TileEntity tileEntity, ItemStack itemStack) {
        playerEntity.awardStat(Stats.BLOCK_MINED.get(this));
        playerEntity.causeFoodExhaustion(0.005F);

        if (!world.isClientSide()) {
            LootContext.Builder lootContextBuilder =
                    (new LootContext.Builder((ServerWorld) world)).withRandom(world.random).withParameter(LootParameters.ORIGIN,
                            Vector3d.atCenterOf(blockPos)).withParameter(LootParameters.TOOL, playerEntity.getMainHandItem()).withOptionalParameter(LootParameters.BLOCK_ENTITY, tileEntity);

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

            // Normal candy cane drop
            if (starTileEntity != null && starTileEntity.isBonusActive()) {
                drops.get(0).setCount(4);
            }

            // Enchanted candy cane drop
            double chance = 0.0D;

            if (starTileEntity == null) {
                chance = ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE;
            } else if (!starTileEntity.isBonusActive()) {
                chance = ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE + ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE * starTileEntity.getCurrentTier() * 0.2;
            } else {
                chance = ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE * 3;
            }

            if (RANDOM.nextDouble() <= chance) drops.add(ItemRegistry.ENCHANTED_CANDY_CANE.get().getDefaultInstance());

            return drops;
        }
    }
}
