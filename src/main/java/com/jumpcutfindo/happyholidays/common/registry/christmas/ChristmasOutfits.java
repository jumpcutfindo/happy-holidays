package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.common.item.outfits.Outfit;
import com.jumpcutfindo.happyholidays.common.item.outfits.OutfitItem;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

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

    public static final Outfit NUTCRACKER_OUTFIT = Outfit.Builder.start()
            .material("nutcracker")
            .headpiece("nutcracker_hat", NutcrackerOutfitItem::new)
            .top("nutcracker_top", NutcrackerOutfitItem::new)
            .bottom("nutcracker_bottom", NutcrackerOutfitItem::new)
            .feet("nutcracker_boots", NutcrackerOutfitItem::new)
            .finish();

    public static final Outfit GINGERBREAD_OUTFIT = Outfit.Builder.start()
            .material("gingerbread")
            .headpiece("gingerbread_headpiece", GingerbreadOutfitItem::new)
            .top("gingerbread_top", GingerbreadOutfitItem::new)
            .bottom("gingerbread_bottom", GingerbreadOutfitItem::new)
            .feet("gingerbread_boots", GingerbreadOutfitItem::new)
            .finish();

    public static class CandyCaneOutfitItem extends OutfitItem {
        public CandyCaneOutfitItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
            super(materialIn, slot, builder);
        }
    }

    public static class GingerbreadOutfitItem extends OutfitItem {
        public GingerbreadOutfitItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
            super(materialIn, slot, builder);
        }
    }

    public static class NutcrackerOutfitItem extends OutfitItem {
        public NutcrackerOutfitItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
            super(materialIn, slot, builder);
        }
    }

    public static class ReindeerOutfitItem extends OutfitItem {
        public ReindeerOutfitItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
            super(materialIn, slot, builder);
        }
    }

    public static class SantaElfOutfitItem extends OutfitItem {
        public SantaElfOutfitItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
            super(materialIn, slot, builder);
        }
    }

    public static class SantaOutfitItem extends OutfitItem {
        public SantaOutfitItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
            super(materialIn, slot, builder);
        }
    }

    public static class SnowmanOutfitItem extends OutfitItem {
        public SnowmanOutfitItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
            super(materialIn, slot, builder);
        }

        @Override
        public <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.snowman_outfit.walk"));
            }

            return super.predicate(event);
        }

        @Override
        public void registerControllers(AnimationData data) {
            super.registerControllers(data);
        }
    }
}
