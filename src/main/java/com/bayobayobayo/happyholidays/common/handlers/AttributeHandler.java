package com.bayobayobayo.happyholidays.common.handlers;

import com.bayobayobayo.happyholidays.common.registry.EntityRegistry;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

public class AttributeHandler {
    public static void handleEntityAttributeStuff(final EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.GINGERBREAD_MAN.get(),
                MobEntity.createMobAttributes()
                        .add(Attributes.MAX_HEALTH, 10.0f)
                        .add(Attributes.MOVEMENT_SPEED, 0.23D)
                        .build()
        );

        event.put(EntityRegistry.SOGGY_GINGERBREAD_MAN.get(),
                MobEntity.createMobAttributes()
                        .add(Attributes.MAX_HEALTH, 10.0f)
                        .add(Attributes.MOVEMENT_SPEED, 0.18D)
                        .build()
        );
    }
}
