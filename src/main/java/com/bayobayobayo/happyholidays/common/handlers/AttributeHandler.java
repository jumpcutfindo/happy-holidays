package com.bayobayobayo.happyholidays.common.handlers;

import com.bayobayobayo.happyholidays.common.entity.christmas.GingerbreadManEntity;
import com.bayobayobayo.happyholidays.common.entity.christmas.SantaElfEntity;
import com.bayobayobayo.happyholidays.common.entity.christmas.SoggyGingerbreadManEntity;
import com.bayobayobayo.happyholidays.common.registry.EntityRegistry;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

public class AttributeHandler {
    public static void handleEntityAttributeStuff(final EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.GINGERBREAD_MAN.get(), GingerbreadManEntity.ENTITY_ATTRIBUTES);
        event.put(EntityRegistry.SOGGY_GINGERBREAD_MAN.get(), SoggyGingerbreadManEntity.ENTITY_ATTRIBUTES);
        event.put(EntityRegistry.SANTA_ELF.get(), SantaElfEntity.ENTITY_ATTRIBUTES);
    }
}
