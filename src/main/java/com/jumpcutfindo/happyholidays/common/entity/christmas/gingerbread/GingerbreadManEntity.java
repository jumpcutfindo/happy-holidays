package com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread;

import java.util.List;

import com.jumpcutfindo.happyholidays.common.events.christmas.GingerbreadConversionEvent;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;

public class GingerbreadManEntity extends GingerbreadPersonEntity {
    public static final int SPAWN_WEIGHT = 15;
    public static final int MIN_SPAWN_COUNT = 2;
    public static final int MAX_SPAWN_COUNT = 4;

    public static final String ENTITY_ID = "gingerbread_man";

    public static final AttributeSupplier ENTITY_ATTRIBUTES =
            Mob.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 10.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.23D)
                    .build();

    public GingerbreadManEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
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
            List<Player> players = HappyHolidaysUtils.findPlayersInRadius(this.level, this.position(), 5.0d);

            for (Player player : players) {
                GingerbreadConversionEvent.ToSoggy turnSoggyEvent =
                        new GingerbreadConversionEvent.ToSoggy(player, this, this.blockPosition());
                MinecraftForge.EVENT_BUS.post(turnSoggyEvent);
            }
        }
    }
}
