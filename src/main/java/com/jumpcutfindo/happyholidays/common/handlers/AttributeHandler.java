package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.grinch.GrinchEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.SoggyGingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.registry.EntityRegistry;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributeHandler {
    @SubscribeEvent
    public static void handleEntityAttributeStuff(final EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.GINGERBREAD_MAN.get(), GingerbreadManEntity.ENTITY_ATTRIBUTES);
        event.put(EntityRegistry.SOGGY_GINGERBREAD_MAN.get(), SoggyGingerbreadManEntity.ENTITY_ATTRIBUTES);
        event.put(EntityRegistry.SANTA_ELF.get(), SantaElfEntity.ENTITY_ATTRIBUTES);
        event.put(EntityRegistry.GRINCH.get(), GrinchEntity.ENTITY_ATTRIBUTES);
        event.put(EntityRegistry.ANGRY_SANTA.get(), BaseSantaEntity.ENTITY_ATTRIBUTES);
        event.put(EntityRegistry.HAPPY_SANTA.get(), BaseSantaEntity.ENTITY_ATTRIBUTES);
    }
}
