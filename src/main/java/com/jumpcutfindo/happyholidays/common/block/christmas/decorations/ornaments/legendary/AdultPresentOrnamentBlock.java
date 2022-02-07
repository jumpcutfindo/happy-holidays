package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.LegendaryOrnament;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AdultPresentOrnamentBlock extends DecorationBlock implements ChristmasLike, ChristmasBlock, LegendaryOrnament {
    public static final String BLOCK_ID = "adult_present_ornament";

    public AdultPresentOrnamentBlock() {
        super();
    }

    @Override
    public @NotNull VoxelShape getFloorShape() {
        return Shapes.or(
                Block.box(6.0, 0.0, 6.0, 10.0, 3.0, 10.0),
                Block.box(5.5, 3.0, 5.5, 10.5, 4.0, 10.5)
        );
    }

    @Override
    public @NotNull VoxelShape getCeilingShape() {
        return Shapes.or(
                Block.box(6.0, 10.5, 6.0, 10.0, 13.5, 10.0),
                Block.box(5.5, 13.5, 5.5, 10.5, 14.5, 10.5)
        );
    }

    @Override
    public @NotNull VoxelShape getWallShape() {
        return Block.box(5.0, 0.0, 0.0, 11.0, 5.0, 4.5);
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.LEGENDARY;
    }
}
