package com.jumpcutfindo.happyholidays.datagen.server.christmas;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ChristmasItemTags extends ItemTagsProvider {
    public ChristmasItemTags(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, HappyHolidaysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        copy(ChristmasTags.Blocks.BAUBLES, ChristmasTags.Items.BAUBLES);
        copy(ChristmasTags.Blocks.BIG_BAUBLES, ChristmasTags.Items.BIG_BAUBLES);
        copy(ChristmasTags.Blocks.TINSEL, ChristmasTags.Items.TINSEL);
        copy(ChristmasTags.Blocks.CHRISTMAS_LIGHTS, ChristmasTags.Items.CHRISTMAS_LIGHTS);
        copy(ChristmasTags.Blocks.CHRISTMAS_BELLS, ChristmasTags.Items.CHRISTMAS_BELLS);
        copy(ChristmasTags.Blocks.STOCKINGS, ChristmasTags.Items.STOCKINGS);
        copy(ChristmasTags.Blocks.DECORATIONS, ChristmasTags.Items.DECORATIONS);
        copy(ChristmasTags.Blocks.HEAD_ORNAMENTS, ChristmasTags.Items.HEAD_ORNAMENTS);
        copy(ChristmasTags.Blocks.GINGERBREAD_BLOCKS, ChristmasTags.Items.GINGERBREAD_BLOCKS);
        copy(ChristmasTags.Blocks.CANDY_CANE_BLOCKS, ChristmasTags.Items.CANDY_CANE_BLOCKS);
        copy(ChristmasTags.Blocks.STAR_AFFECTED_BLOCKS, ChristmasTags.Items.STAR_AFFECTED_BLOCKS);

        tag(ChristmasTags.Items.BASIC_ORNAMENTS)
                .addTag(ChristmasTags.Items.BAUBLES)
                .addTag(ChristmasTags.Items.BIG_BAUBLES)
                .addTag(ChristmasTags.Items.CHRISTMAS_LIGHTS)
                .addTag(ChristmasTags.Items.TINSEL)
                .addTag(ChristmasTags.Items.CHRISTMAS_BELLS)
                .addTag(ChristmasTags.Items.DECORATIONS);

        tag(ChristmasTags.Items.RARE_ORNAMENTS)
                .addTag(ChristmasTags.Items.HEAD_ORNAMENTS);

        tag(ChristmasTags.Items.LEGENDARY_ORNAMENTS)
                .add(ChristmasItems.BABY_PRESENT_ORNAMENT.get())
                .add(ChristmasItems.ADULT_PRESENT_ORNAMENT.get())
                .add(ChristmasItems.ELDER_PRESENT_ORNAMENT.get())
                .add(ChristmasItems.CANDY_CANE_ORNAMENT.get())
                .add(ChristmasItems.GRINCH_ORNAMENT.get())
                .add(ChristmasItems.SANTA_ELF_ORNAMENT.get())
                .add(ChristmasItems.GINGERBREAD_MAN_ORNAMENT.get());

        tag(ChristmasTags.Items.SHEET_MUSIC)
                .add(ChristmasItems.SHEET_MUSIC_ANGELS_ON_HIGH.get())
                .add(ChristmasItems.SHEET_MUSIC_CAROL_OF_THE_BELLS.get())
                .add(ChristmasItems.SHEET_MUSIC_DECK_THE_HALLS.get())
                .add(ChristmasItems.SHEET_MUSIC_FROSTY_THE_SNOWMAN.get())
                .add(ChristmasItems.SHEET_MUSIC_GOD_REST_GENTLEMEN.get())
                .add(ChristmasItems.SHEET_MUSIC_HERE_COMES_SANTA.get())
                .add(ChristmasItems.SHEET_MUSIC_JINGLE_BELL_ROCK.get())
                .add(ChristmasItems.SHEET_MUSIC_JINGLE_BELLS.get())
                .add(ChristmasItems.SHEET_MUSIC_JOY_TO_THE_WORLD.get())
                .add(ChristmasItems.SHEET_MUSIC_RUDOLPH.get())
                .add(ChristmasItems.SHEET_MUSIC_SILENT_NIGHT.get())
                .add(ChristmasItems.SHEET_MUSIC_SLEIGH_RIDE.get())
                .add(ChristmasItems.SHEET_MUSIC_THE_FIRST_NOEL.get())
                .add(ChristmasItems.SHEET_MUSIC_WE_THREE_KINGS.get())
                .add(ChristmasItems.SHEET_MUSIC_WE_WISH_YOU.get())
                .add(ChristmasItems.SHEET_MUSIC_WHITE_CHRISTMAS.get());

        tag(ChristmasTags.Items.FOODS)
                .add(ChristmasItems.EGGNOG.get())
                .add(ChristmasItems.CHRISTMAS_HAM.get())
                .add(ChristmasItems.CHRISTMAS_PUDDING.get())
                .add(ChristmasItems.MILK_AND_COOKIES.get())
                .add(ChristmasItems.LOG_CAKE.get())
                .add(ChristmasItems.GINGERBREAD_COOKIE.get())
                .add(ChristmasItems.CANDY_CANE.get())
                .add(ChristmasItems.FESTIVE_CANDY_CANE.get());

        santaElfRequestables();
    }

    private void santaElfRequestables() {
        tag(ChristmasTags.Items.SANTA_ELF_BASIC_REQUESTABLES)
                .addTag(net.minecraft.tags.ItemTags.FLOWERS)
                .addTag(net.minecraft.tags.ItemTags.WOOL)
                .addTag(net.minecraft.tags.ItemTags.SAPLINGS)
                .addTag(net.minecraft.tags.ItemTags.SAND)
                .add(Items.DIRT)
                .add(Items.COARSE_DIRT)
                .add(Items.GRAVEL)
                .add(Items.CLAY)
                .add(Items.STONE)
                .add(Items.COBBLESTONE)
                .add(Items.GRANITE)
                .add(Items.DIORITE)
                .add(Items.ANDESITE)
                .add(Items.TUFF)
                .add(Items.DEEPSLATE)
                .add(Items.COBBLED_DEEPSLATE)
                .add(Items.GLASS)
                .add(Items.OAK_LOG)
                .add(Items.SPRUCE_LOG)
                .add(Items.BIRCH_LOG)
                .add(Items.ACACIA_LOG)
                .add(Items.DARK_OAK_LOG)
                .add(Items.JUNGLE_LOG)
                .add(Items.OAK_LEAVES)
                .add(Items.SPRUCE_LEAVES)
                .add(Items.BIRCH_LEAVES)
                .add(Items.ACACIA_LEAVES)
                .add(Items.DARK_OAK_LEAVES)
                .add(Items.JUNGLE_LEAVES)
                .add(Items.WHEAT)
                .add(Items.WHEAT_SEEDS)
                .add(Items.BEETROOT)
                .add(Items.BEETROOT_SEEDS)
                .add(Items.CARROT)
                .add(Items.POTATO)
                .add(Items.SUGAR_CANE)
                .add(Items.PUMPKIN)
                .add(Items.MELON_SLICE)
                .add(Items.APPLE)
                .add(Items.SWEET_BERRIES)
                .add(Items.BAMBOO)
                .add(Items.BROWN_MUSHROOM)
                .add(Items.RED_MUSHROOM)
                .add(Items.KELP)
                .add(Items.DRIED_KELP)
                .add(Items.STRING)
                .add(Items.FEATHER)
                .add(Items.GUNPOWDER)
                .add(Items.ROTTEN_FLESH)
                .add(Items.COAL)
                .add(Items.RAW_IRON)
                .add(Items.RAW_COPPER)
                .add(Items.RAW_GOLD)
                .add(Items.RED_DYE)
                .add(Items.BLUE_DYE)
                .add(Items.GREEN_DYE)
                .add(Items.PURPLE_DYE)
                .add(Items.CYAN_DYE)
                .add(Items.LIGHT_GRAY_DYE)
                .add(Items.GRAY_DYE)
                .add(Items.PINK_DYE)
                .add(Items.LIME_DYE)
                .add(Items.YELLOW_DYE)
                .add(Items.LIGHT_BLUE_DYE)
                .add(Items.MAGENTA_DYE)
                .add(Items.ORANGE_DYE)
                .add(Items.BLACK_DYE)
                .add(Items.BROWN_DYE)
                .add(Items.WHITE_DYE);

        tag(ChristmasTags.Items.SANTA_ELF_INTERMEDIATE_REQUESTABLES)
                .add(Items.COPPER_INGOT)
                .add(Items.GOLD_INGOT)
                .add(Items.IRON_INGOT)
                .add(Items.AMETHYST_SHARD)
                .add(Items.NETHER_WART)
                .add(Items.NETHER_BRICK)
                .add(Items.BLAZE_POWDER)
                .add(Items.GLOWSTONE_DUST)
                .add(Items.QUARTZ)
                .add(Items.NETHERRACK)
                .add(Items.SOUL_SAND)
                .add(Items.SOUL_SOIL)
                .add(Items.CRIMSON_STEM)
                .add(Items.CRIMSON_PLANKS)
                .add(Items.WARPED_STEM)
                .add(Items.WARPED_PLANKS)
                .add(Items.WEEPING_VINES)
                .add(Items.TWISTING_VINES)
                .add(Items.SHROOMLIGHT)
                .add(Items.BLACKSTONE)
                .add(Items.BASALT)
                .add(Items.OBSIDIAN)
                .add(Items.HONEYCOMB)
                .add(Items.PHANTOM_MEMBRANE)
                .add(Items.SEA_PICKLE);

        tag(ChristmasTags.Items.SANTA_ELF_ADVANCED_REQUESTABLES)
                .add(Items.PRISMARINE_SHARD)
                .add(Items.PRISMARINE_CRYSTALS)
                .add(Items.END_STONE)
                .add(Items.CHORUS_FRUIT)
                .add(Items.PURPUR_BLOCK)
                .add(Items.END_ROD)
                .add(Items.CRYING_OBSIDIAN)
                .add(Items.TURTLE_EGG);

        tag(ChristmasTags.Items.SANTA_ELF_ADVANCED_REQUESTABLES_ALTERNATE)
                .addTag(net.minecraft.tags.ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(Items.DIAMOND)
                .add(Items.NETHERITE_SCRAP)
                .add(Items.SHULKER_SHELL)
                .add(Items.SPONGE)
                .add(Items.BELL)
                .add(Items.GILDED_BLACKSTONE)
                .add(Items.NAUTILUS_SHELL)
                .add(Items.HEART_OF_THE_SEA)
                .add(Items.SCUTE)
                .add(Items.GHAST_TEAR);

        tag(ChristmasTags.Items.NUTCRACKER_FOOD)
                .addTag(net.minecraft.tags.ItemTags.LOGS);

        tag(ChristmasTags.Items.HEAT_EMITTING_ITEMS)
                .add(Items.CAMPFIRE)
                .add(Items.SOUL_CAMPFIRE)
                .add(Items.LAVA_BUCKET)
                .add(Items.MAGMA_BLOCK);

    }
}
