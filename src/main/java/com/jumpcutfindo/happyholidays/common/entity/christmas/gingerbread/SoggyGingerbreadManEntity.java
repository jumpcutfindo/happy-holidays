package com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread;

import java.util.List;

import com.jumpcutfindo.happyholidays.common.events.christmas.GingerbreadConversionEvent;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;

public class SoggyGingerbreadManEntity extends GingerbreadPersonEntity {
    public static final String ENTITY_ID = "soggy_gingerbread_man";

    public static final AttributeSupplier ENTITY_ATTRIBUTES =
            createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 10.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.18D)
                    .build();

    private float timeLeftToDry;

    public SoggyGingerbreadManEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);

        timeLeftToDry = 300.0f;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide() && !isInWaterOrRain() && isNearFireSource()) {
            if (--timeLeftToDry == 0.0f) convertToDry();
        }

        if (this.level.isClientSide() && !isInWaterOrRain() && isNearFireSource()) {
            spawnSmokeParticles();
        }
    }

    private void spawnSmokeParticles() {
        EntityDimensions size = this.getDimensions(this.getPose());

        this.level.addParticle(
                ParticleTypes.SMOKE,
                this.getX() + random.nextDouble() * size.width - 0.5D,
                this.getY() + random.nextDouble() * size.height,
                this.getZ() + random.nextDouble() * size.width - 0.5D,
                0.0D,
                0.0D,
                0.0D
        );
    }

    private boolean isNearFireSource() {
        BlockPos pos1 = new BlockPos(this.getX(), this.getY(), this.getZ()).offset(-1, -1, -1);
        BlockPos pos2 = new BlockPos(this.getX(), this.getY(), this.getZ()).offset(1, 2, 1);

        for (BlockPos pos : BlockPos.betweenClosed(pos1, pos2)) {
            if (GingerbreadPersonEntity.isValidHeatSource(this.level.getBlockState(pos))) return true;
        }

        return false;
    }

    private void convertToDry() {
        this.convertTo(ChristmasEntities.GINGERBREAD_MAN.get(), true);
        this.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);

        this.dropConversionLoot();

        if (!this.level.isClientSide()) {
            List<Player> players = HappyHolidaysUtils.findPlayersInRadius(this.level, this.position(), 5.0d);

            for (Player player : players) {
                GingerbreadConversionEvent.ToDry turnDryEvent =
                        new GingerbreadConversionEvent.ToDry(player, this, this.blockPosition());
                MinecraftForge.EVENT_BUS.post(turnDryEvent);
            }
        }
    }
}
