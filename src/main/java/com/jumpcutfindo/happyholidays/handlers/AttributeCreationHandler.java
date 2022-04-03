package com.jumpcutfindo.happyholidays.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.grinch.GrinchEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.SoggyGingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributeCreationHandler {
    @SubscribeEvent
    public static void onEntityAttributeCreation(final EntityAttributeCreationEvent event) {
        event.put(ChristmasEntities.GINGERBREAD_MAN.get(), GingerbreadManEntity.ENTITY_ATTRIBUTES);
        event.put(ChristmasEntities.SOGGY_GINGERBREAD_MAN.get(), SoggyGingerbreadManEntity.ENTITY_ATTRIBUTES);
        event.put(ChristmasEntities.SANTA_ELF.get(), SantaElfEntity.ENTITY_ATTRIBUTES);
        event.put(ChristmasEntities.GRINCH.get(), GrinchEntity.ENTITY_ATTRIBUTES);
        event.put(ChristmasEntities.ANGRY_SANTA.get(), BaseSantaEntity.ENTITY_ATTRIBUTES);
        event.put(ChristmasEntities.HAPPY_SANTA.get(), BaseSantaEntity.ENTITY_ATTRIBUTES);
        event.put(ChristmasEntities.NUTCRACKER.get(), NutcrackerEntity.ENTITY_ATTRIBUTES);
    }
}
