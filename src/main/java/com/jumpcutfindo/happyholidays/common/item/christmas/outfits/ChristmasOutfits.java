package com.jumpcutfindo.happyholidays.common.item.christmas.outfits;

import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.subs.CandyCaneOutfitItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.subs.ReindeerOutfitItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.subs.SantaElfOutfitItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.subs.SantaOutfitItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.subs.SnowmanOutfitItem;

public class ChristmasOutfits {
    public static final Outfit SANTA_OUTFIT = Outfit.Builder.start()
            .material("santa")
            .headpiece("santa_hat", SantaOutfitItem::new)
            .top("santa_top", SantaOutfitItem::new)
            .bottom("santa_bottom", SantaOutfitItem::new)
            .feet("santa_boots", SantaOutfitItem::new)
            .finish();

    public static final Outfit SANTA_ELF_OUTFIT = Outfit.Builder.start()
            .material("santa_elf")
            .headpiece("santa_elf_hat", SantaElfOutfitItem::new)
            .top("santa_elf_top", SantaElfOutfitItem::new)
            .bottom("santa_elf_bottom", SantaElfOutfitItem::new)
            .feet("santa_elf_boots", SantaElfOutfitItem::new)
            .finish();

    public static final Outfit SNOWMAN_OUTFIT = Outfit.Builder.start()
            .material("snowman")
            .headpiece("snowman_headpiece", SnowmanOutfitItem::new)
            .top("snowman_top", SnowmanOutfitItem::new)
            .bottom("snowman_bottom", SnowmanOutfitItem::new)
            .finish();

    public static final Outfit CANDY_CANE_OUTFIT = Outfit.Builder.start()
            .material("candy_cane")
            .headpiece("candy_cane_headpiece", CandyCaneOutfitItem::new)
            .top("candy_cane_top", CandyCaneOutfitItem::new)
            .bottom("candy_cane_bottom", CandyCaneOutfitItem::new)
            .feet("candy_cane_boots", CandyCaneOutfitItem::new)
            .finish();

    public static final Outfit REINDEER_OUTFIT = Outfit.Builder.start()
            .material("reindeer")
            .headpiece("reindeer_headpiece", ReindeerOutfitItem::new)
            .top("reindeer_top", ReindeerOutfitItem::new)
            .bottom("reindeer_bottom", ReindeerOutfitItem::new)
            .feet("reindeer_boots", ReindeerOutfitItem::new)
            .finish();

    /*
    public static final Outfit GINGERBREAD_MAN_OUTFIT = Outfit.Builder.start()
            .material("gingerbread")
            .headpiece("gingerbread_headpiece").top("gingerbread_top").bottom("gingerbread_bottom").feet("gingerbread_shoes")
            .finish();
     */
}
