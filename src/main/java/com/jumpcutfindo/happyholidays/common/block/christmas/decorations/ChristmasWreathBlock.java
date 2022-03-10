package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.WallDecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChristmasWreathBlock extends WallDecorationBlock implements ChristmasBlock {
    public static final Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.WOOL)
                    .strength(0.1f)
                    .sound(SoundType.CROP)
                    .noOcclusion()
                    .noCollission();

    public ChristmasWreathBlock() {
        super(BLOCK_PROPERTIES);
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }

    @Override
    public @NotNull VoxelShape getVoxelShape() {
        return Block.box(0.0, 0.0, 0.0 ,16.0, 16.0,0.5);
    }
}
