package com.bayobayobayo.happyholidays.common.entity.christmas;

import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CryingObsidianBlock;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.MagmaBlock;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Dimension;
import net.minecraft.world.World;

public class SoggyGingerbreadManEntity extends GingerbreadPersonEntity {
    public static final String ENTITY_ID = "soggy_gingerbread_man";

    private float timeLeftToDry;

    public SoggyGingerbreadManEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);

        timeLeftToDry = 100.0f;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide() && isNearFireSource()) {
            spawnSmokeParticles();

            if (--timeLeftToDry == 0.0f) convertToDry();
        }
    }

    private void spawnSmokeParticles() {
        EntitySize dimensions = this.getDimensions(this.getPose());
        Vector3d vector3d = this.getDeltaMovement();

        this.level.addParticle(
                ParticleTypes.SMOKE,
                this.getX() + (this.random.nextDouble() - 0.5D) * (double) dimensions.width,
                this.getY() + (this.random.nextDouble() * 2.0D),
                this.getZ() + (this.random.nextDouble() - 0.5D) * (double) dimensions.width,
                vector3d.x * 4.0D,
                0.5D,
                vector3d.z * 4.0D
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
                || blockState.is(Blocks.MAGMA_BLOCK)) {
                return true;
            }
        }

        return false;
    }

    private void convertToDry() {
        GingerbreadEntities gingerbreadEntities =
                (GingerbreadEntities) ModuleHandler.CHRISTMAS_HANDLER.getSpecificEntities(GingerbreadEntities.GINGERBREAD_ENTITIES_ID);


        this.convertTo(gingerbreadEntities.getGingerbreadManObject().get(), true);
        this.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);
    }
}
