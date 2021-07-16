package com.bayobayobayo.happyholidays.common.entity.christmas;

import com.bayobayobayo.happyholidays.common.handlers.ChristmasHandler;
import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class GingerbreadManEntity extends GingerbreadPersonEntity {
    public static final String ENTITY_ID = "gingerbread_man";

    public GingerbreadManEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide() && this.isInWaterOrRain()) {
            convertToSoggy();
        }
    }

    private void convertToSoggy() {
        GingerbreadEntities gingerbreadEntities =
                (GingerbreadEntities) ModuleHandler.CHRISTMAS_HANDLER.getSpecificEntities(GingerbreadEntities.GINGERBREAD_ENTITIES_ID);

        this.convertTo(gingerbreadEntities.getSoggyGingerbreadManObject().get(), true);
        this.playSound(SoundEvents.GENERIC_SPLASH, 1.0F, 1.0F);
    }
}
