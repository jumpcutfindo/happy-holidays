package com.jumpcutfindo.happyholidays.data.server;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.StockingBlock;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class LootTables extends BaseLootTableProvider {
    public LootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addTables() {
        registerBlocks();
    }

    private void registerBlocks() {
        addStandardBlock(ChristmasBlocks.GINGERBREAD_BLOCK.get());
        addStandardBlock(ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK.get());
        addStandardBlock(ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get());

        addStandardBlock(ChristmasBlocks.GINGERBREAD_STAIRS.get());
        addStandardBlock(ChristmasBlocks.GINGERBREAD_DOUGH_STAIRS.get());
        addStandardBlock(ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS.get());

        addStandardBlock(ChristmasBlocks.GINGERBREAD_SLAB.get());
        addStandardBlock(ChristmasBlocks.GINGERBREAD_DOUGH_SLAB.get());
        addStandardBlock(ChristmasBlocks.SOGGY_GINGERBREAD_SLAB.get());

        addStandardBlock(ChristmasBlocks.GINGERBREAD_WALL.get());
        addStandardBlock(ChristmasBlocks.GINGERBREAD_DOUGH_WALL.get());
        addStandardBlock(ChristmasBlocks.SOGGY_GINGERBREAD_WALL.get());

        addStandardBlock(ChristmasBlocks.BABY_PRESENT_ORNAMENT.get());
        addStandardBlock(ChristmasBlocks.ADULT_PRESENT_ORNAMENT.get());
        addStandardBlock(ChristmasBlocks.ELDER_PRESENT_ORNAMENT.get());
        addStandardBlock(ChristmasBlocks.CANDY_CANE_ORNAMENT.get());
        addStandardBlock(ChristmasBlocks.GRINCH_ORNAMENT.get());
        addStandardBlock(ChristmasBlocks.SANTA_ELF_ORNAMENT.get());
        addStandardBlock(ChristmasBlocks.GINGERBREAD_MAN_ORNAMENT.get());

        addStandardBlock(ChristmasBlocks.RED_BAUBLE.get());
        addStandardBlock(ChristmasBlocks.BLUE_BAUBLE.get());
        addStandardBlock(ChristmasBlocks.YELLOW_BAUBLE.get());
        addStandardBlock(ChristmasBlocks.GREEN_BAUBLE.get());
        addStandardBlock(ChristmasBlocks.GOLD_BAUBLE.get());
        addStandardBlock(ChristmasBlocks.SILVER_BAUBLE.get());

        addStandardBlock(ChristmasBlocks.BIG_RED_BAUBLE.get());
        addStandardBlock(ChristmasBlocks.BIG_BLUE_BAUBLE.get());
        addStandardBlock(ChristmasBlocks.BIG_YELLOW_BAUBLE.get());
        addStandardBlock(ChristmasBlocks.BIG_GREEN_BAUBLE.get());
        addStandardBlock(ChristmasBlocks.BIG_GOLD_BAUBLE.get());
        addStandardBlock(ChristmasBlocks.BIG_SILVER_BAUBLE.get());

        addStandardBlock(ChristmasBlocks.RED_TINSEL.get());
        addStandardBlock(ChristmasBlocks.BLUE_TINSEL.get());
        addStandardBlock(ChristmasBlocks.YELLOW_TINSEL.get());
        addStandardBlock(ChristmasBlocks.GREEN_TINSEL.get());
        addStandardBlock(ChristmasBlocks.GOLD_TINSEL.get());
        addStandardBlock(ChristmasBlocks.SILVER_TINSEL.get());

        addStandardBlock(ChristmasBlocks.RED_CHRISTMAS_LIGHTS.get());
        addStandardBlock(ChristmasBlocks.BLUE_CHRISTMAS_LIGHTS.get());
        addStandardBlock(ChristmasBlocks.YELLOW_CHRISTMAS_LIGHTS.get());
        addStandardBlock(ChristmasBlocks.GREEN_CHRISTMAS_LIGHTS.get());
        addStandardBlock(ChristmasBlocks.GOLD_CHRISTMAS_LIGHTS.get());
        addStandardBlock(ChristmasBlocks.SILVER_CHRISTMAS_LIGHTS.get());

        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_A.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_B.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_C.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_D.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_E.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_F.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_G.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_H.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_I.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_J.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_K.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_L.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_M.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_N.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_O.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_P.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_Q.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_R.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_S.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_T.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_U.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_V.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_W.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_X.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_Y.get());
        addStandardBlock(ChristmasBlocks.ALPHABET_ORNAMENT_Z.get());

        addStandardBlock(ChristmasBlocks.CHRISTMAS_STAR.get());
        addStandardBlock(ChristmasBlocks.MUSIC_BOX.get());
        addStandardBlock(ChristmasBlocks.CHRISTMAS_WREATH.get());
        addStandardBlock(ChristmasBlocks.GIFT_WRAPPING_STATION.get());
        addStandardBlock(ChristmasBlocks.SANTA_LIST.get());

        addStandardBlock(ChristmasBlocks.CREEPER_HEAD_ORNAMENT.get());
        addStandardBlock(ChristmasBlocks.ZOMBIE_HEAD_ORNAMENT.get());
        addStandardBlock(ChristmasBlocks.SKELETON_HEAD_ORNAMENT.get());
        addStandardBlock(ChristmasBlocks.WITHER_SKELETON_HEAD_ORNAMENT.get());

        addStockingBlock(ChristmasBlocks.RED_STOCKING.get());
        addStockingBlock(ChristmasBlocks.BLUE_STOCKING.get());
        addStockingBlock(ChristmasBlocks.YELLOW_STOCKING.get());
        addStockingBlock(ChristmasBlocks.GREEN_STOCKING.get());
        addStockingBlock(ChristmasBlocks.GOLD_STOCKING.get());
        addStockingBlock(ChristmasBlocks.SILVER_STOCKING.get());
    }

    private void addStandardBlock(Block block) {
        lootTables.put(block, standardBlock(block));
    }

    private void addStockingBlock(Block block) {
        LootPool.Builder enchantedPool = LootPool.lootPool()
                .name(id(block))
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ChristmasItems.ENCHANTED_THREAD.get()))
                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS)))
                .when(LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(block)
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(StockingBlock.ENCHANTED, true)
                        )
                );

        lootTables.put(block, standardBlock(block).withPool(enchantedPool));
    }

    protected LootTable.Builder createStandardTable(String name, Block block) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block)
                        .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                        .apply(SetContainerContents.setContents()
                                .withEntry(LootItem.lootTableItem(Items.STRING)))
                );
        return LootTable.lootTable().setParamSet(LootContextParamSets.BLOCK).withPool(builder);
    }
}
