package com.jumpcutfindo.happyholidays.data;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.data.client.christmas.ChristmasBlockStates;
import com.jumpcutfindo.happyholidays.data.client.christmas.ChristmasItemModels;
import com.jumpcutfindo.happyholidays.data.server.BaseAdvancementProvider;
import com.jumpcutfindo.happyholidays.data.server.christmas.ChristmasBlockTags;
import com.jumpcutfindo.happyholidays.data.server.christmas.ChristmasEntityTags;
import com.jumpcutfindo.happyholidays.data.server.christmas.ChristmasItemTags;
import com.jumpcutfindo.happyholidays.data.server.christmas.ChristmasLootTables;
import com.jumpcutfindo.happyholidays.data.server.christmas.ChristmasRecipes;

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
