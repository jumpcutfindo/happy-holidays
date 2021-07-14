package com.bayobayobayo.happyholidays.common.entity;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public interface HappyHolidaysEntities {
    void createAttributes(EntityAttributeCreationEvent event);
    void registerEntities();
    void configureEntities();
    void configureEntitySpawning(BiomeLoadingEvent event);
}
