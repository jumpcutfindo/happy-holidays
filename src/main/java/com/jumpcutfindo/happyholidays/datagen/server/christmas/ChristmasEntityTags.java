package com.jumpcutfindo.happyholidays.datagen.server.christmas;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ChristmasEntityTags extends EntityTypeTagsProvider {
    public ChristmasEntityTags(DataGenerator p_126517_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126517_, HappyHolidaysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ChristmasTags.Entities.CANDY_CANE_EXPLODERS)
                .add(EntityType.GHAST)
                .add(EntityType.CREEPER)
                .add(EntityType.FIREBALL)
                .add(EntityType.TNT)
                .add(EntityType.TNT_MINECART);
    }
}
