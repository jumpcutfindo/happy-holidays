package com.jumpcutfindo.happyholidays.common.block.christmas;

import org.jetbrains.annotations.Nullable;

import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.ExplosivePresentEntity;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ExplosivePresentBlock extends TntBlock implements ChristmasBlock {
    // Explosive Present Block is separated from the other present blocks since it doesn't really behave like a present
    public static final String BLOCK_ID = "explosive_present";

    public static final Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(1.0f)
                    .sound(SoundType.METAL)
                    .randomTicks();

    public static final VoxelShape SHAPE = Shapes.or(
            box(2.0, 0.0, 2.0, 14.0, 8.0, 14.0),
            box(1.0, 8.0, 1.0, 15.0, 11.0, 15.0)
    );

    public ExplosivePresentBlock() {
        super(BLOCK_PROPERTIES);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockReader, BlockPos blockPos,
                               CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }

    @Override
    public void onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        createExplosiveEntity(world, new Vec3(pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d), igniter);
    }

    public static void createExplosiveEntity(Level level, Vec3 pos, @Nullable LivingEntity igniter) {
        if (!level.isClientSide) {
            ExplosivePresentEntity explosivePresent = ChristmasEntities.EXPLOSIVE_PRESENT.get().create(level);

            if (explosivePresent == null) return;

            explosivePresent.setOwner(igniter);
            explosivePresent.moveTo(pos.x, pos.y, pos.z);
            level.addFreshEntity(explosivePresent);
            level.playSound((Player)null, explosivePresent.getX(), explosivePresent.getY(), explosivePresent.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }
}
