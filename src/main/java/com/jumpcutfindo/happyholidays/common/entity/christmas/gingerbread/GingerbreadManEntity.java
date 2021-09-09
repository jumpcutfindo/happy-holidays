package com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread;

import java.util.List;

import com.jumpcutfindo.happyholidays.common.events.christmas.GingerbreadConversionEvent;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GingerbreadManEntity extends GingerbreadPersonEntity {
    public static final int SPAWN_WEIGHT = 100;
    public static final int MIN_SPAWN_COUNT = 2;
    public static final int MAX_SPAWN_COUNT = 4;

    public static final String ENTITY_ID = "gingerbread_man";

    public static final AttributeModifierMap ENTITY_ATTRIBUTES =
            MobEntity.createMobAttributes()
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
        this.convertTo(ChristmasEntities.SOGGY_GINGERBREAD_MAN.get(), true);
        this.playSound(SoundEvents.GENERIC_SPLASH, 1.0F, 1.0F);

        if (!this.level.isClientSide()) {
            List<PlayerEntity> players = HappyHolidaysUtils.findPlayersInRadius(this.level, this.position(), 5.0d);

            for (PlayerEntity player : players) {
                GingerbreadConversionEvent.ToSoggy turnSoggyEvent =
                        new GingerbreadConversionEvent.ToSoggy(player, this, this.blockPosition());
                MinecraftForge.EVENT_BUS.post(turnSoggyEvent);
            }
        }
    }
}
