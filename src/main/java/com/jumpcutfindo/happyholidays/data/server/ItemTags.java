package com.jumpcutfindo.happyholidays.data.server;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTags extends ItemTagsProvider {
    public ItemTags(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, HappyHolidaysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        copy(ChristmasTags.Blocks.BAUBLES, ChristmasTags.Items.BAUBLES);
        copy(ChristmasTags.Blocks.BIG_BAUBLES, ChristmasTags.Items.BIG_BAUBLES);
        copy(ChristmasTags.Blocks.TINSEL, ChristmasTags.Items.TINSEL);
        copy(ChristmasTags.Blocks.CHRISTMAS_LIGHTS, ChristmasTags.Items.CHRISTMAS_LIGHTS);
        copy(ChristmasTags.Blocks.DECORATIONS, ChristmasTags.Items.DECORATIONS);
        copy(ChristmasTags.Blocks.HEAD_ORNAMENTS, ChristmasTags.Items.HEAD_ORNAMENTS);

        tag(ChristmasTags.Items.BASIC_ORNAMENTS)
                .addTag(ChristmasTags.Items.BAUBLES)
                .addTag(ChristmasTags.Items.BIG_BAUBLES)
                .addTag(ChristmasTags.Items.CHRISTMAS_LIGHTS)
                .addTag(ChristmasTags.Items.TINSEL)
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
    }
}
