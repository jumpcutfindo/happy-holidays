package com.jumpcutfindo.happyholidays.data.server;

import com.jumpcutfindo.happyholidays.common.block.christmas.candy.BaseCandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.StockingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.AdultPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.BabyPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.ElderPresentBlock;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadPersonEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
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
        registerEntities();
        registerAdditional();
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

        addCandyCaneBlock(ChristmasBlocks.CANDY_CANE_BLOCK.get(), ChristmasItems.CANDY_CANE.get());
        addCandyCaneBlock(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get(), ChristmasItems.FESTIVE_CANDY_CANE.get());
    }

    private void registerEntities() {
        addGingerbreadMan(ChristmasEntities.GINGERBREAD_MAN.get());
        addGingerbreadMan(ChristmasEntities.SOGGY_GINGERBREAD_MAN.get());

        entityLootTables.put(ChristmasEntities.GRINCH.get(), LootTable.lootTable());
        entityLootTables.put(ChristmasEntities.SANTA_ELF.get(), LootTable.lootTable());
        entityLootTables.put(ChristmasEntities.ANGRY_SANTA.get(), LootTable.lootTable());
        entityLootTables.put(ChristmasEntities.HAPPY_SANTA.get(), LootTable.lootTable());
    }

    private void registerAdditional() {
        addStockingPresents();
        addSantaGiftRewards();
    }

    private void addStandardBlock(Block block) {
        blockLootTables.put(block, standardBlock(block));
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

        blockLootTables.put(block, standardBlock(block).withPool(enchantedPool));
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

        blockLootTables.put(presentBlock, silkTouchBlock(presentBlock).withPool(scrapsPool).withPool(presentOrnamentPool).withPool(presentsPool));
    }

    private void addCandyCaneBlock(Block candyCaneBlock, ItemLike component) {
        int minCount = 2;
        int maxCount = 4;

        LootItemCondition.Builder silkTouchCondition = MatchTool.toolMatches(
                ItemPredicate.Builder.item()
                        .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.ANY))
        );

        LootPool.Builder pool = LootPool.lootPool()
                        .name(id(candyCaneBlock))
                        .setRolls(ConstantValue.exactly(1))
                        .add(AlternativesEntry.alternatives(LootItem.lootTableItem(candyCaneBlock).when(silkTouchCondition))
                            .otherwise(LootItem.lootTableItem(component).apply(SetItemCountFunction.setCount(ConstantValue.exactly(maxCount))).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(ChristmasTags.Entities.CANDY_CANE_EXPLODERS).build())))
                            .otherwise(LootItem.lootTableItem(component).apply(SetItemCountFunction.setCount(UniformGenerator.between(minCount, maxCount))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)).apply(LimitCount.limitCount(IntRange.range(maxCount - 2, maxCount)))));

        LootPool.Builder enchantedPool = LootPool.lootPool()
                        .name(id(ChristmasItems.ENCHANTED_CANDY_CANE.get()))
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ChristmasItems.ENCHANTED_CANDY_CANE.get()))
                        .when(LootItemRandomChanceCondition.randomChance((float) BaseCandyCaneBlock.ENCHANTED_CANDY_CANE_DROP_BASE_CHANCE));

        blockLootTables.put(candyCaneBlock, LootTable.lootTable().withPool(pool).withPool(enchantedPool));
    }

    private void addStockingPresents() {
        // Normal stocking presents
        LootPool.Builder normalStockingPool = LootPool.lootPool()
                .name("stocking_presents")
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(Items.COAL).setWeight(200).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.COPPER_INGOT).setWeight(200).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(100).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(50).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(10).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.EMERALD).setWeight(100).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.COOKIE).setWeight(200).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.SUGAR).setWeight(300).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.POTATO).setWeight(200).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.CARROT).setWeight(200).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.BEETROOT_SEEDS).setWeight(200).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.WHEAT_SEEDS).setWeight(200).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.MELON_SEEDS).setWeight(200).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.PUMPKIN_SEEDS).setWeight(200).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.GLOWSTONE_DUST).setWeight(150)).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                .add(LootItem.lootTableItem(Items.CLAY_BALL).setWeight(300).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.REDSTONE).setWeight(150).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.STICK).setWeight(300).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.QUARTZ).setWeight(100).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(ChristmasItems.PRESENT_SCRAPS.get()).setWeight(50).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));

        LootPool.Builder enchantedStockingPool = LootPool.lootPool()
                .name("enchanted_stocking_presents")
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(Items.COAL).setWeight(300).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                .add(LootItem.lootTableItem(Items.COPPER_INGOT).setWeight(200).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8))))
                .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(200).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(200).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))))
                .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP).setWeight(10).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.EMERALD).setWeight(100).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                .add(LootItem.lootTableItem(Items.COOKIE).setWeight(100).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))))
                .add(LootItem.lootTableItem(Items.SUGAR).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                .add(LootItem.lootTableItem(Items.POTATO).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                .add(LootItem.lootTableItem(Items.CARROT).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                .add(LootItem.lootTableItem(Items.BEETROOT_SEEDS).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                .add(LootItem.lootTableItem(Items.WHEAT_SEEDS).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                .add(LootItem.lootTableItem(Items.MELON_SEEDS).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                .add(LootItem.lootTableItem(Items.PUMPKIN_SEEDS).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                .add(LootItem.lootTableItem(Items.GLOWSTONE_DUST).setWeight(50)).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 12)))
                .add(LootItem.lootTableItem(Items.CLAY_BALL).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 16))))
                .add(LootItem.lootTableItem(Items.REDSTONE).setWeight(200).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 12))))
                .add(LootItem.lootTableItem(Items.STICK).setWeight(50).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 24))))
                .add(LootItem.lootTableItem(Items.QUARTZ).setWeight(100).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))))
                .add(LootItem.lootTableItem(ChristmasItems.PRESENT_SCRAPS.get()).setWeight(100).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))));

        additionalLootTables.put(christmasResource("stocking_presents"), LootTable.lootTable().withPool(normalStockingPool));
        additionalLootTables.put(christmasResource("enchanted_stocking_presents"), LootTable.lootTable().withPool(enchantedStockingPool));
    }

    private void addSantaGiftRewards() {
        LootPool.Builder basicGiftPool = LootPool.lootPool()
                .name("santa_basic_gifts")
                .setRolls(UniformGenerator.between(1, 2))
                .add(LootItem.lootTableItem(ChristmasItems.FESTIVE_CANDY_CANE_BLOCK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(ChristmasItems.CANDY_CANE_BLOCK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(ChristmasItems.GINGERBREAD_BLOCK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.TERRACOTTA).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.COBBLESTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.STONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.DIORITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.ANDESITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.GRANITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.TUFF).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.DEEPSLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.COBBLED_DEEPSLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.ACACIA_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.BIRCH_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.DARK_OAK_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.JUNGLE_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.OAK_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.SPRUCE_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.COAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 12))))
                .add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 12))))
                .add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8))))
                .add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8))))
                .add(LootItem.lootTableItem(Items.EMERALD).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8))))
                .add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8))))
                .add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8))))
                .add(LootItem.lootTableItem(Items.TNT).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))));

        LootPool.Builder rareGiftPool = LootPool.lootPool()
                .name("santa_rare_gifts")
                .setRolls(UniformGenerator.between(1, 2))
                .add(TagEntry.expandTag(ChristmasTags.Items.RARE_ORNAMENTS))
                .add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(EnchantRandomlyFunction.randomEnchantment()))
                .add(LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                .add(LootItem.lootTableItem(Items.CRIMSON_STEM).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8))))
                .add(LootItem.lootTableItem(Items.WARPED_STEM).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8))))
                .add(LootItem.lootTableItem(Items.BLAZE_ROD).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                .add(LootItem.lootTableItem(Items.PRISMARINE_CRYSTALS).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.PRISMARINE_SHARD).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.CHORUS_FRUIT).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
                .add(LootItem.lootTableItem(Items.END_ROD).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                .add(LootItem.lootTableItem(Items.SPONGE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))));

        LootPool.Builder legendaryGiftPool = LootPool.lootPool()
                .name("santa_legendary_gifts")
                .setRolls(ConstantValue.exactly(1))
                .add(TagEntry.expandTag(ChristmasTags.Items.LEGENDARY_ORNAMENTS))
                .add(LootItem.lootTableItem(Items.IRON_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 12))))
                .add(LootItem.lootTableItem(Items.GOLD_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 10))))
                .add(LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.DIAMOND_SWORD).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(10, 30))))
                .add(LootItem.lootTableItem(Items.DIAMOND_HOE).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(10, 30))))
                .add(LootItem.lootTableItem(Items.DIAMOND_SHOVEL).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(10, 30))))
                .add(LootItem.lootTableItem(Items.DIAMOND_PICKAXE).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(10, 30))))
                .add(LootItem.lootTableItem(Items.DIAMOND_AXE).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(10, 30))))
                .add(LootItem.lootTableItem(Items.DIAMOND_HELMET).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(10, 30))))
                .add(LootItem.lootTableItem(Items.DIAMOND_CHESTPLATE).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(10, 30))))
                .add(LootItem.lootTableItem(Items.DIAMOND_LEGGINGS).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(10, 30))))
                .add(LootItem.lootTableItem(Items.DIAMOND_BOOTS).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(10, 30))));

        additionalLootTables.put(christmasResource("santa_basic_gifts"), LootTable.lootTable().withPool(basicGiftPool));
        additionalLootTables.put(christmasResource("santa_rare_gifts"), LootTable.lootTable().withPool(rareGiftPool));
        additionalLootTables.put(christmasResource("santa_legendary_gifts"), LootTable.lootTable().withPool(legendaryGiftPool));
    }

    private void addGingerbreadMan(EntityType<? extends GingerbreadPersonEntity> gingerbreadEntity) {
        LootPool.Builder gingerbreadManPool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ChristmasItems.RAW_GINGERBREAD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1, 2))));

        LootPool.Builder gingerbreadCookiePool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ChristmasItems.GINGERBREAD_COOKIE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceCondition.randomChance(0.01f)));

        LootPool.Builder gingerbreadOrnamentPool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ChristmasItems.GINGERBREAD_MAN_ORNAMENT.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceCondition.randomChance(0.005f)));

        entityLootTables.put(gingerbreadEntity, LootTable.lootTable().withPool(gingerbreadManPool).withPool(gingerbreadCookiePool).withPool(gingerbreadOrnamentPool));
    }


    private static ResourceLocation christmasResource(String id) {
        return resourceOf("christmas/" + id);
    }
}
