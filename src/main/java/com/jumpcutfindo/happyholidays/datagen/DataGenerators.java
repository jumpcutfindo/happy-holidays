package com.jumpcutfindo.happyholidays.datagen;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.datagen.client.christmas.ChristmasBlockStates;
import com.jumpcutfindo.happyholidays.datagen.client.christmas.ChristmasItemModels;
import com.jumpcutfindo.happyholidays.datagen.client.cny.CNYBlockStates;
import com.jumpcutfindo.happyholidays.datagen.server.BaseAdvancementProvider;
import com.jumpcutfindo.happyholidays.datagen.server.christmas.ChristmasBlockTags;
import com.jumpcutfindo.happyholidays.datagen.server.christmas.ChristmasEntityTags;
import com.jumpcutfindo.happyholidays.datagen.server.christmas.ChristmasItemTags;
import com.jumpcutfindo.happyholidays.datagen.server.christmas.ChristmasLootTables;
import com.jumpcutfindo.happyholidays.datagen.server.christmas.ChristmasRecipes;

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
        gen.addProvider(new ChristmasBlockStates(gen, existingFileHelper));
        gen.addProvider(new ChristmasItemModels(gen, existingFileHelper));

        gen.addProvider(new CNYBlockStates(gen, existingFileHelper));

        // Server-side providers
        ChristmasBlockTags christmasBlockTags = new ChristmasBlockTags(gen, existingFileHelper);
        gen.addProvider(christmasBlockTags);
        gen.addProvider(new ChristmasItemTags(gen, christmasBlockTags, existingFileHelper));
        gen.addProvider(new ChristmasEntityTags(gen, existingFileHelper));
        gen.addProvider(new ChristmasRecipes(gen));
        gen.addProvider(new ChristmasLootTables(gen));

        gen.addProvider(new BaseAdvancementProvider(gen));
    }
}
