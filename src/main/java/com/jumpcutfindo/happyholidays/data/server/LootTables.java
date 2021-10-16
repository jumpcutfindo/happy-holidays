package com.jumpcutfindo.happyholidays.data.server;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.StockingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.AdultPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.BabyPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.ElderPresentBlock;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

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

        addPresentBlock(ChristmasBlocks.BABY_PRESENT.get());
        addPresentBlock(ChristmasBlocks.ADULT_PRESENT.get());
        addPresentBlock(ChristmasBlocks.ELDER_PRESENT.get());
    }

    private void addStandardBlock(Block block) {
        lootTables.put(block, standardBlock(block));
    }

    private void addStockingBlock(Block block) {
        LootPool.Builder enchantedPool = LootPool.lootPool()
                .name(id(block) + "_enchanted")
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

    private void addPresentBlock(Block presentBlock) {
        int scrapCount = 0;
        Item ornamentItem = null;
        LootPool.Builder presentsPool = null;

        LootItemCondition.Builder silkTouchCondition = MatchTool.toolMatches(
                ItemPredicate.Builder.item()
                        .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.ANY))
        );

        if (presentBlock instanceof BabyPresentBlock) {
            scrapCount = 1;
            ornamentItem = ChristmasItems.BABY_PRESENT_ORNAMENT.get();
            presentsPool = LootPool.lootPool()
                    .name("presents")
                    .setRolls(ConstantValue.exactly(1))
                    .add(TagEntry.expandTag(ChristmasTags.Items.BASIC_ORNAMENTS).setWeight(200).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                    .add(TagEntry.expandTag(ChristmasTags.Items.SHEET_MUSIC).setWeight(10).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(TagEntry.expandTag(ChristmasTags.Items.RARE_ORNAMENTS).setWeight(50).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(ChristmasItems.ENCHANTED_THREAD.get()).setWeight(50).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(Items.COPPER_INGOT).setWeight(100).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                    .add(LootItem.lootTableItem(Items.COAL).setWeight(100).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                    .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                    .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(40).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                    .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(20).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP).setWeight(10).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(ChristmasItems.SNOW_GLOBE.get()).setWeight(10).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).apply(SetItemDamageFunction.setDamage(ConstantValue.exactly(0))))
                    .when(InvertedLootItemCondition.invert(silkTouchCondition));
        } else if (presentBlock instanceof AdultPresentBlock) {
            scrapCount = 2;
            ornamentItem = ChristmasItems.ADULT_PRESENT_ORNAMENT.get();
            presentsPool = LootPool.lootPool()
                    .name("presents")
                    .setRolls(ConstantValue.exactly(1))
                    .add(TagEntry.expandTag(ChristmasTags.Items.BASIC_ORNAMENTS).setWeight(100).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                    .add(TagEntry.expandTag(ChristmasTags.Items.SHEET_MUSIC).setWeight(10).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(TagEntry.expandTag(ChristmasTags.Items.RARE_ORNAMENTS).setWeight(50).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(ChristmasItems.ENCHANTED_THREAD.get()).setWeight(50).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(Items.COAL).setWeight(100).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                    .add(LootItem.lootTableItem(Items.COPPER_INGOT).setWeight(100).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                    .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                    .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(40).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                    .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(20).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP).setWeight(10).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(ChristmasItems.SNOW_GLOBE.get()).setWeight(20).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).apply(SetItemDamageFunction.setDamage(ConstantValue.exactly(0))))
                    .when(InvertedLootItemCondition.invert(silkTouchCondition));
        } else if (presentBlock instanceof ElderPresentBlock) {
            scrapCount = 3;
            ornamentItem = ChristmasItems.ELDER_PRESENT_ORNAMENT.get();
            presentsPool = LootPool.lootPool()
                    .name("presents")
                    .setRolls(ConstantValue.exactly(1))
                    .add(TagEntry.expandTag(ChristmasTags.Items.BASIC_ORNAMENTS).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                    .add(TagEntry.expandTag(ChristmasTags.Items.SHEET_MUSIC).setWeight(20).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(TagEntry.expandTag(ChristmasTags.Items.RARE_ORNAMENTS).setWeight(100).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(ChristmasItems.ENCHANTED_THREAD.get()).setWeight(100).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(Items.COAL).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                    .add(LootItem.lootTableItem(Items.COPPER_INGOT).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                    .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(100).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                    .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(100).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                    .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(50).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP).setWeight(20).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(ChristmasItems.SNOW_GLOBE.get()).setWeight(30).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).apply(SetItemDamageFunction.setDamage(ConstantValue.exactly(0))))
                    .when(InvertedLootItemCondition.invert(silkTouchCondition));
        }

        LootPool.Builder scrapsPool = LootPool.lootPool()
                .name("scraps")
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ChristmasItems.PRESENT_SCRAPS.get()))
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(scrapCount)))
                .when(InvertedLootItemCondition.invert(silkTouchCondition));

        LootPool.Builder presentOrnamentPool = LootPool.lootPool()
                .name("present_ornament")
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ornamentItem))
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                .when(LootItemRandomChanceCondition.randomChance(0.02f))
                .when(InvertedLootItemCondition.invert(silkTouchCondition));

        lootTables.put(presentBlock, silkTouchBlock(presentBlock).withPool(scrapsPool).withPool(presentOrnamentPool).withPool(presentsPool));
    }
}
