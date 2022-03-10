package com.jumpcutfindo.happyholidays.common.item.outfits;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;

import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

public class Outfit {
    public static final Item.Properties DEFAULT_ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.CHRISTMAS_GROUP)
                    .stacksTo(1);

    public final OutfitMaterial material;

    public String headpieceId;
    public String topId;
    public String bottomId;
    public String feetId;

    public OutfitItem headpiece;
    public OutfitItem top;
    public OutfitItem bottom;
    public OutfitItem feet;

    private Outfit(String materialId) {
        this.material = OutfitMaterial.createMaterial(materialId);
    }

    public void createNewHeadpiece(String headpieceId) {
        this.headpieceId = headpieceId;
        this.headpiece = new OutfitItem(material, EquipmentSlot.HEAD, DEFAULT_ITEM_PROPERTIES);
    }

    public void createNewTop(String topId) {
        this.topId = topId;
        this.top = new OutfitItem(material, EquipmentSlot.CHEST, DEFAULT_ITEM_PROPERTIES);
    }

    public void createNewBottom(String bottomId) {
        this.bottomId = bottomId;
        this.bottom = new OutfitItem(material, EquipmentSlot.LEGS, DEFAULT_ITEM_PROPERTIES);
    }

    public void createNewFeet(String feetId) {
        this.feetId = feetId;
        this.feet = new OutfitItem(material, EquipmentSlot.FEET, DEFAULT_ITEM_PROPERTIES);
    }

    public ArmorItem getHeadpiece() {
        return this.headpiece;
    }

    public ArmorItem getTop() {
        return this.top;
    }

    public ArmorItem getBottom() {
        return this.bottom;
    }

    public ArmorItem getFeet() {
        return this.feet;
    }

    public String getHeadpieceId() {
        return headpieceId;
    }

    public String getTopId() {
        return topId;
    }

    public String getBottomId() {
        return bottomId;
    }

    public String getFeetId() {
        return feetId;
    }

    private OutfitMaterial getMaterial() {
        return material;
    }

    private Outfit setHeadpiece(String headpieceId, OutfitItem headpiece) {
        this.headpieceId = headpieceId;
        this.headpiece = headpiece;
        return this;
    }

    private Outfit setTop(String topId, OutfitItem top) {
        this.topId = topId;
        this.top = top;
        return this;
    }

    private Outfit setBottom(String bottomId, OutfitItem bottom) {
        this.bottomId = bottomId;
        this.bottom = bottom;
        return this;
    }

    private Outfit setFeet(String feetId, OutfitItem feet) {
        this.feetId = feetId;
        this.feet = feet;
        return this;
    }

    public static class Builder {
        private Outfit outfit;

        private Builder() {
        }

        public Builder material(String materialId) {
            this.outfit = new Outfit(materialId);
            return this;
        }

        public Builder headpiece(String headpieceId, PropertyDispatch.TriFunction<ArmorMaterial, EquipmentSlot, Item.Properties, OutfitItem> outfitItemConsumer) {
            OutfitItem outfitItem = outfitItemConsumer.apply(outfit.getMaterial(), EquipmentSlot.HEAD, DEFAULT_ITEM_PROPERTIES);

            this.outfit.setHeadpiece(headpieceId, outfitItem);
            return this;
        }

        public Builder top(String topId, PropertyDispatch.TriFunction<ArmorMaterial, EquipmentSlot, Item.Properties, OutfitItem> outfitItemConsumer) {
            OutfitItem outfitItem = outfitItemConsumer.apply(outfit.getMaterial(), EquipmentSlot.CHEST, DEFAULT_ITEM_PROPERTIES);

            this.outfit.setTop(topId, outfitItem);
            return this;
        }

        public Builder bottom(String bottomId, PropertyDispatch.TriFunction<ArmorMaterial, EquipmentSlot, Item.Properties, OutfitItem> outfitItemConsumer) {
            OutfitItem outfitItem = outfitItemConsumer.apply(outfit.getMaterial(), EquipmentSlot.LEGS, DEFAULT_ITEM_PROPERTIES);

            this.outfit.setBottom(bottomId, outfitItem);
            return this;
        }

        public Builder feet(String feetId, PropertyDispatch.TriFunction<ArmorMaterial, EquipmentSlot, Item.Properties, OutfitItem> outfitItemConsumer) {
            OutfitItem outfitItem = outfitItemConsumer.apply(outfit.material, EquipmentSlot.FEET, DEFAULT_ITEM_PROPERTIES);

            this.outfit.setFeet(feetId, outfitItem);
            return this;
        }

        public Outfit finish() {
            return this.outfit;
        }

        public static Builder start() {
            return new Builder();
        }
    }
}
