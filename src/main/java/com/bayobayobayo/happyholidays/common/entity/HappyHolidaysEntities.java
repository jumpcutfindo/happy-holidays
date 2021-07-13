package com.bayobayobayo.happyholidays.common.entity;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

public interface HappyHolidaysEntities {
    void createAttributes(EntityAttributeCreationEvent event);
    void registerEntities();
    void configureEntities();
}
