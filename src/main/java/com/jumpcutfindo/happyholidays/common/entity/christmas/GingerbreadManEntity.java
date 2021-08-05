package com.jumpcutfindo.happyholidays.common.entity.christmas;

import com.jumpcutfindo.happyholidays.common.registry.EntityRegistry;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class GingerbreadManEntity extends GingerbreadPersonEntity {
    public static final int SPAWN_PROBABILITY = 150;
    public static final int MIN_SPAWN_COUNT = 2;
    public static final int MAX_SPAWN_COUNT = 4;

    public static final String ENTITY_ID = "gingerbread_man";

    public static final AttributeModifierMap ENTITY_ATTRIBUTES =
            createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 10.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.23D)
                    .build();

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
        this.convertTo(EntityRegistry.SOGGY_GINGERBREAD_MAN.get(), true);
        this.playSound(SoundEvents.GENERIC_SPLASH, 1.0F, 1.0F);
    }
}
