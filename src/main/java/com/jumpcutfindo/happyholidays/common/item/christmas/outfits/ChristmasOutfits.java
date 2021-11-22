package com.jumpcutfindo.happyholidays.common.item.christmas.outfits;

public class ChristmasOutfits {
    public static final Outfit SANTA_OUTFIT = Outfit.Builder.start()
            .material("santa")
            .headpiece("santa_hat").top("santa_top").bottom("santa_bottom").feet("santa_boots")
            .finish();

    public static final Outfit SANTA_ELF_OUTFIT = Outfit.Builder.start()
            .material("santa_elf")
            .headpiece("santa_elf_hat").top("santa_elf_top").bottom("santa_elf_bottom").feet("santa_elf_boots")
            .finish();

    public static final Outfit SNOWMAN_OUTFIT = Outfit.Builder.start()
            .material("snowman")
            .headpieceSpecial("snowman_headpiece", SnowmanOutfitItem::new)
            .topSpecial("snowman_top", SnowmanOutfitItem::new)
            .bottomSpecial("snowman_bottom", SnowmanOutfitItem::new)
            .finish();

    public static final Outfit REINDEER_OUTFIT = Outfit.Builder.start()
            .material("reindeer")
            .headpiece("reindeer_headpiece").top("reindeer_top").bottom("reindeer_bottom").feet("reindeer_feet")
            .finish();

    public static final Outfit CANDY_CANE_OUTFIT = Outfit.Builder.start()
            .material("candy_cane")
            .headpiece("candy_cane_headpiece").top("candy_cane_top").bottom("candy_cane_bottom").feet("candy_cane_shoes")
            .finish();

    public static final Outfit GINGERBREAD_MAN_OUTFIT = Outfit.Builder.start()
            .material("gingerbread")
            .headpiece("gingerbread_headpiece").top("gingerbread_top").bottom("gingerbread_bottom").feet("gingerbread_shoes")
            .finish();
}
