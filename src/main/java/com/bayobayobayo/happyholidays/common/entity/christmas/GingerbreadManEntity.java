package com.bayobayobayo.happyholidays.common.entity.christmas;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class GingerbreadManEntity extends GingerbreadPersonEntity {
    public static final String ENTITY_ID = "gingerbread_man";

    public GingerbreadManEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }
}
