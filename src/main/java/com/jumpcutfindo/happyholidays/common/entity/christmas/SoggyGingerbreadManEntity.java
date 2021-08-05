package com.jumpcutfindo.happyholidays.common.entity.christmas;

import com.jumpcutfindo.happyholidays.common.registry.EntityRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoggyGingerbreadManEntity extends GingerbreadPersonEntity {
    public static final String ENTITY_ID = "soggy_gingerbread_man";

    public static final AttributeModifierMap ENTITY_ATTRIBUTES =
            createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 10.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.18D)
                    .build();

    private float timeLeftToDry;

    public SoggyGingerbreadManEntity(EntityType<? extends CreatureEntity> entityType, World world) {
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
        EntitySize size = this.getDimensions(this.getPose());

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
        BlockPos blockPos = new BlockPos(this.getX(), this.getY(), this.getZ());
        BlockState[] blockStates = new BlockState[] {
                this.level.getBlockState(blockPos.below()),
                this.level.getBlockState(blockPos.north()),
                this.level.getBlockState(blockPos.south()),
                this.level.getBlockState(blockPos.west()),
                this.level.getBlockState(blockPos.east()),
                this.level.getBlockState(blockPos.above().above())
        };

        for (BlockState blockState : blockStates) {
            if (blockState.is(Blocks.FURNACE) && blockState.getValue(BlockStateProperties.LIT)
                || blockState.is(Blocks.BLAST_FURNACE) && blockState.getValue(BlockStateProperties.LIT)
                || blockState.is(Blocks.SMOKER) && blockState.getValue(BlockStateProperties.LIT)
                || blockState.is(Blocks.FIRE) || blockState.is(Blocks.SOUL_FIRE)
                || blockState.is(Blocks.CAMPFIRE) || blockState.is(Blocks.SOUL_CAMPFIRE)
                || blockState.is(Blocks.MAGMA_BLOCK)
            ) {
                return true;
            }
        }

        return false;
    }

    private void convertToDry() {
        this.convertTo(EntityRegistry.GINGERBREAD_MAN.get(), true);
        this.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);

        this.dropConversionLoot();
    }
}
