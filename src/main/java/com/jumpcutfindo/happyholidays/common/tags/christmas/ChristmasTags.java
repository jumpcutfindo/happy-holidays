package com.jumpcutfindo.happyholidays.common.tags.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ChristmasTags {

    public class Blocks {
        public static final Tags.IOptionalNamedTag<Block> BAUBLES = blockTag("baubles");

        public static final Tags.IOptionalNamedTag<Block> BIG_BAUBLES = blockTag("big_baubles");

        public static final Tags.IOptionalNamedTag<Block> TINSEL = blockTag("tinsel");

        public static final Tags.IOptionalNamedTag<Block> CHRISTMAS_LIGHTS = blockTag("lights");

        public static final Tags.IOptionalNamedTag<Block> CHRISTMAS_BELLS = blockTag("bells");

        public static final Tags.IOptionalNamedTag<Block> STOCKINGS = blockTag("stockings");

        public static final Tags.IOptionalNamedTag<Block> DECORATIONS = blockTag("decorations");

        public static final Tags.IOptionalNamedTag<Block> HEAD_ORNAMENTS = blockTag("head_ornaments");

        public static final Tags.IOptionalNamedTag<Block> GINGERBREAD_BLOCKS = blockTag("gingerbread_blocks");

        public static final Tags.IOptionalNamedTag<Block> CANDY_CANE_BLOCKS = blockTag("candy_cane_blocks");

        public static final Tags.IOptionalNamedTag<Block> STAR_AFFECTED_BLOCKS = blockTag("star_affected_blocks");

        public static final Tags.IOptionalNamedTag<Block> GINGERBREAD_MEN_SPAWNABLE_ON = blockTag("gingerbread_men_spawnable_on");

        public static final Tags.IOptionalNamedTag<Block> NUTCRACKER_SPAWNABLE_ON = blockTag("nutcracker_spawnable_on");

        public static final Tags.IOptionalNamedTag<Block> HEAT_EMITTING_BLOCKS = blockTag("heat_emitting_blocks");
    }

    public class Items {
        public static final Tags.IOptionalNamedTag<Item> BAUBLES = itemTag("baubles");

        public static final Tags.IOptionalNamedTag<Item> BIG_BAUBLES = itemTag("big_baubles");

        public static final Tags.IOptionalNamedTag<Item> TINSEL = itemTag("tinsel");

        public static final Tags.IOptionalNamedTag<Item> CHRISTMAS_LIGHTS = itemTag("lights");

        public static final Tags.IOptionalNamedTag<Item> CHRISTMAS_BELLS = itemTag("bells");

        public static final Tags.IOptionalNamedTag<Item> STOCKINGS = itemTag("stockings");

        public static final Tags.IOptionalNamedTag<Item> DECORATIONS = itemTag("decorations");

        public static final Tags.IOptionalNamedTag<Item> HEAD_ORNAMENTS = itemTag("head_ornaments");

        public static final Tags.IOptionalNamedTag<Item> BASIC_ORNAMENTS = itemTag("basic_ornaments");

        public static final Tags.IOptionalNamedTag<Item> RARE_ORNAMENTS = itemTag("rare_ornaments");

        public static final Tags.IOptionalNamedTag<Item> LEGENDARY_ORNAMENTS = itemTag("legendary_ornaments");

        public static final Tags.IOptionalNamedTag<Item> FOODS = itemTag("foods");

        public static final Tags.IOptionalNamedTag<Item> LARGE_FOODS = itemTag("large_foods");

        public static final Tags.IOptionalNamedTag<Item> SHEET_MUSIC = itemTag("sheet_music");

        public static final Tags.IOptionalNamedTag<Item> GINGERBREAD_BLOCKS = itemTag("gingerbread_blocks");

        public static final Tags.IOptionalNamedTag<Item> CANDY_CANE_BLOCKS = itemTag("candy_cane_blocks");

        public static final Tags.IOptionalNamedTag<Item> STAR_AFFECTED_BLOCKS = itemTag("star_affected_blocks");

        public static final Tags.IOptionalNamedTag<Item> SANTA_ELF_BASIC_REQUESTABLES = itemTag("santa_elf_basic_requestables");

        public static final Tags.IOptionalNamedTag<Item> SANTA_ELF_INTERMEDIATE_REQUESTABLES = itemTag("santa_elf_intermediate_requestables");

        public static final Tags.IOptionalNamedTag<Item> SANTA_ELF_ADVANCED_REQUESTABLES = itemTag("santa_elf_advanced_requestables");

        public static final Tags.IOptionalNamedTag<Item> SANTA_ELF_ADVANCED_REQUESTABLES_ALTERNATE = itemTag("santa_elf_advanced_requestables_alternate");

        public static final Tags.IOptionalNamedTag<Item> NUTCRACKER_FOOD = itemTag("nutcracker_food");

        public static final Tags.IOptionalNamedTag<Item> HEAT_EMITTING_ITEMS = itemTag("heat_emitting_items");
    }

    public class Entities {
        public static final Tags.IOptionalNamedTag<EntityType<?>> CANDY_CANE_EXPLODERS = entityTag("candy_cane_exploders");
    }

    private static Tags.IOptionalNamedTag<Block> blockTag(String name) {
        return BlockTags.createOptional(new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/" + name));
    }

    private static Tags.IOptionalNamedTag<Item> itemTag(String name) {
        return ItemTags.createOptional(new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/" + name));
    }

    private static Tags.IOptionalNamedTag<EntityType<?>> entityTag(String name) {
        return EntityTypeTags.createOptional(new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/" + name));
    }
}
