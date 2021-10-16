package com.jumpcutfindo.happyholidays.data.server;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.mojang.datafixers.util.Pair;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public abstract class BaseLootTableProvider extends LootTableProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
    public static Map<ResourceLocation, LootTable> tables = new HashMap<>();
    protected final DataGenerator generator;

    public BaseLootTableProvider(DataGenerator generator) {
        super(generator);

        this.generator = generator;
    }

    // Subclasses can override this to fill the 'lootTables' map.
    protected abstract void addTables();

    private void writeTables(HashCache cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.generator.getOutputFolder();

        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                DataProvider.save(GSON, cache, LootTables.serialize(lootTable), path);
            } catch (IOException e) {
                HappyHolidaysMod.LOGGER.error("Couldn't write loot table {}", path, (Object) e);
            }
        });
    }

    @Override
    public void run(HashCache cache) {
        addTables();

        for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().build());
        }

        writeTables(cache, tables);
    }

    @Override
    public String getName() {
        return "Happy Holidays LootTables";
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return super.getTables();
    }

    public static LootTable.Builder standardBlock(Block block) {
        LootPool.Builder builder =
                LootPool.lootPool()
                    .name(id(block))
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(block))
                    .when(ExplosionCondition.survivesExplosion());
        return LootTable.lootTable().setParamSet(LootContextParamSets.BLOCK).withPool(builder);
    }

    public static LootTable.Builder silkTouchBlock(Block block) {
        LootPool.Builder builder =
                LootPool.lootPool()
                        .name(id(block))
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(block))
                        .when(MatchTool.toolMatches(
                                ItemPredicate.Builder.item()
                                        .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.ANY))
                        ));

        return LootTable.lootTable().setParamSet(LootContextParamSets.BLOCK).withPool(builder);
    }

    public static String id(ItemLike itemLike) {
        return itemLike instanceof Block ? ((Block) itemLike).getRegistryName().getPath() : ((Item) itemLike).getRegistryName().getPath();
    }
}
