package com.jumpcutfindo.happyholidays.data;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.data.client.BlockStates;
import com.jumpcutfindo.happyholidays.data.client.ItemModels;
import com.jumpcutfindo.happyholidays.data.server.BlockTags;
import com.jumpcutfindo.happyholidays.data.server.EntityTags;
import com.jumpcutfindo.happyholidays.data.server.ItemTags;
import com.jumpcutfindo.happyholidays.data.server.LootTables;
import com.jumpcutfindo.happyholidays.data.server.Recipes;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    private DataGenerators() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();

        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // Client-side providers
        gen.addProvider(new BlockStates(gen, existingFileHelper));
        gen.addProvider(new ItemModels(gen, existingFileHelper));

        // Server-side providers
        BlockTags blockTags = new BlockTags(gen, existingFileHelper);
        gen.addProvider(blockTags);
        gen.addProvider(new ItemTags(gen, blockTags, existingFileHelper));
        gen.addProvider(new EntityTags(gen, existingFileHelper));
        gen.addProvider(new Recipes(gen));
        gen.addProvider(new LootTables(gen));
    }
}
