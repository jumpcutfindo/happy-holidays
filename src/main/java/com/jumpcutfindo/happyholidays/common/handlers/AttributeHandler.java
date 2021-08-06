package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.common.entity.christmas.GingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.GrinchEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.SoggyGingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.registry.EntityRegistry;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

public class AttributeHandler {
    public static void handleEntityAttributeStuff(final EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.GINGERBREAD_MAN.get(), GingerbreadManEntity.ENTITY_ATTRIBUTES);
        event.put(EntityRegistry.SOGGY_GINGERBREAD_MAN.get(), SoggyGingerbreadManEntity.ENTITY_ATTRIBUTES);
        event.put(EntityRegistry.SANTA_ELF.get(), SantaElfEntity.ENTITY_ATTRIBUTES);
        event.put(EntityRegistry.GRINCH.get(), GrinchEntity.ENTITY_ATTRIBUTES);
    }
}