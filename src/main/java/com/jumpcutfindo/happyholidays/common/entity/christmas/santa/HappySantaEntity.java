package com.jumpcutfindo.happyholidays.common.entity.christmas.santa;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class HappySantaEntity extends BaseSantaEntity{
    public static final String ENTITY_ID = "happy_santa";

    public HappySantaEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }
}
