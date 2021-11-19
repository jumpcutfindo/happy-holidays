package com.jumpcutfindo.happyholidays.common.item.christmas.outfits;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class Outfit {
    public static final Item.Properties DEFAULT_ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP)
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

    public void addHeadpiece(String headpieceId) {
        this.headpieceId = headpieceId;
        this.headpiece = new OutfitItem(material, EquipmentSlot.HEAD, DEFAULT_ITEM_PROPERTIES);
    }

    public void addTop(String topId) {
        this.topId = topId;
        this.top = new OutfitItem(material, EquipmentSlot.CHEST, DEFAULT_ITEM_PROPERTIES);
    }

    public void addBottom(String bottomId) {
        this.bottomId = bottomId;
        this.bottom = new OutfitItem(material, EquipmentSlot.LEGS, DEFAULT_ITEM_PROPERTIES);
    }

    public void addFeet(String feetId) {
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

    public static class Builder {
        private Outfit outfit;

        private Builder() {
        }

        public Builder material(String materialId) {
            this.outfit = new Outfit(materialId);
            return this;
        }

        public Builder headpiece(String headpieceId) {
            this.outfit.addHeadpiece(headpieceId);
            return this;
        }

        public Builder top(String topId) {
            this.outfit.addTop(topId);
            return this;
        }

        public Builder bottom(String bottomId) {
            this.outfit.addBottom(bottomId);
            return this;
        }

        public Builder feet(String feetId) {
            this.outfit.addFeet(feetId);
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
