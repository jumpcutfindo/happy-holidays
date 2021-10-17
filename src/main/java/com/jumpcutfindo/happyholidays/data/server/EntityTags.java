package com.jumpcutfindo.happyholidays.data.server;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EntityTags extends EntityTypeTagsProvider {
    public EntityTags(DataGenerator p_126517_, @Nullable ExistingFileHelper existingFileHelper) {
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
