package com.jumpcutfindo.happyholidays.data.server;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class LootTables extends BaseLootTableProvider {
    public LootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addTables() {
        lootTables.put(ChristmasBlocks.GINGERBREAD_BLOCK.get(), createStandardTable("test_name", ChristmasBlocks.GINGERBREAD_BLOCK.get()));
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
        return LootTable.lootTable().withPool(builder);
    }
}
