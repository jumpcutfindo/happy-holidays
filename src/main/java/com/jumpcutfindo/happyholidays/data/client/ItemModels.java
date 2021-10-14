package com.jumpcutfindo.happyholidays.data.client;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemModels extends ItemModelProvider {
    public static final String ITEM_GENERATED = "item/generated";

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, HappyHolidaysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Retrieve all registered items in the mod
        Set<Item> allItems = ForgeRegistries.ITEMS.getValues().stream().filter(item -> HappyHolidaysMod.MOD_ID.equals(ForgeRegistries.ITEMS.getKey(item).getNamespace()))
                .collect(Collectors.toSet());

        // Filter out specific cases of items before registering everything else
        allItems.removeAll(registerBlocksWithItemTextures());

    }

    private Set<Item> registerBlocksWithItemTextures() {
        Set<Item> blockItems = Sets.newHashSet(
                ChristmasItems.BABY_PRESENT_ORNAMENT.get(),
                ChristmasItems.ADULT_PRESENT_ORNAMENT.get(),
                ChristmasItems.ELDER_PRESENT_ORNAMENT.get(),
                ChristmasItems.CANDY_CANE_ORNAMENT.get(),
                ChristmasItems.GINGERBREAD_MAN_ORNAMENT.get(),

                ChristmasItems.RED_CHRISTMAS_LIGHTS.get(),
                ChristmasItems.BLUE_CHRISTMAS_LIGHTS.get(),
                ChristmasItems.YELLOW_CHRISTMAS_LIGHTS.get(),
                ChristmasItems.GREEN_CHRISTMAS_LIGHTS.get(),
                ChristmasItems.GOLD_CHRISTMAS_LIGHTS.get(),
                ChristmasItems.SILVER_CHRISTMAS_LIGHTS.get(),

                ChristmasItems.RED_TINSEL.get(),
                ChristmasItems.BLUE_TINSEL.get(),
                ChristmasItems.YELLOW_TINSEL.get(),
                ChristmasItems.GREEN_TINSEL.get(),
                ChristmasItems.GOLD_TINSEL.get(),
                ChristmasItems.SILVER_TINSEL.get(),

                ChristmasItems.RED_STOCKING.get(),
                ChristmasItems.BLUE_STOCKING.get(),
                ChristmasItems.YELLOW_STOCKING.get(),
                ChristmasItems.GREEN_STOCKING.get(),
                ChristmasItems.GOLD_STOCKING.get(),
                ChristmasItems.SILVER_STOCKING.get(),

                ChristmasItems.CHRISTMAS_WREATH.get(),
                ChristmasItems.SANTA_LIST.get(),

                ChristmasItems.CHRISTMAS_HAM.get(),
                ChristmasItems.CHRISTMAS_PUDDING.get(),
                ChristmasItems.LOG_CAKE.get(),
                ChristmasItems.MILK_AND_COOKIES.get(),

                ChristmasItems.CHRISTMAS_STAR.get()
        );

        for (Item item : blockItems) blockWithItemTexture(item);

        return blockItems;
    }

    private void blockWithItemTexture(Item item) {
        String itemId = itemId(item);
        withExistingParent(itemId(item), ITEM_GENERATED).texture("layer0", resourceOfItem(itemId));
    }

    private ResourceLocation resourceOfBlock(String path) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "block/" + path);
    }

    private ResourceLocation resourceOfItem(String path) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "item/" + path);
    }

    private String itemId(Item item) {
        return item.getRegistryName().getPath();
    }
}
