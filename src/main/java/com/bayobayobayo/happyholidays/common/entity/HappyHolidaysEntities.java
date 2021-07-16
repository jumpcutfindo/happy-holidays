package com.bayobayobayo.happyholidays.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;

public interface HappyHolidaysEntities {
    void createAttributes(EntityAttributeCreationEvent event);
    void registerEntities();
    void configureEntities();
    void configureEntitySpawning(BiomeLoadingEvent event);

    String getId();
}
