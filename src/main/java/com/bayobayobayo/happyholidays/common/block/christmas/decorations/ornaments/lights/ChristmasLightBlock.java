package com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.lights;

import java.util.Random;

import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ConnectedOrnamentBlock;
import com.bayobayobayo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.FireworkParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChristmasLightBlock extends ConnectedOrnamentBlock {
    private static final VoxelShape CHRISTMAS_LIGHT_SHAPE = VoxelShapes.or(
            HappyHolidaysUtils.createShape(0.0, 0.0, 0.0, 16.0, 16.0 ,1.0)
    );

    private static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.GLASS)
                    .harvestLevel(-1)
                    .strength(0.1f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .noCollission()
                    .lightLevel((blockState) -> 14);

    public ChristmasLightBlock(String blockId) {
        super(blockId, BLOCK_PROPERTIES, CHRISTMAS_LIGHT_SHAPE);
    }
}
