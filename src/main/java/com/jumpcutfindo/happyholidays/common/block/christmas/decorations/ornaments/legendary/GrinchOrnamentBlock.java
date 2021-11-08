package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.LegendaryOrnament;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GrinchOrnamentBlock extends DecorationBlock implements ChristmasLike, ChristmasBlock, LegendaryOrnament {
    public static final String BLOCK_ID = "grinch_ornament";

    public static final VoxelShape[][] ORNAMENT_SHAPES = {
            {
                    Block.box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0),
                    Block.box(4.0, 4.0, 3.0, 12.0, 10.0, 13.0)
            },
            {
                    Block.box(3.0, 3.75, 3.0, 13.0, 7.75, 13.0),
                    Block.box(4.0, 7.75, 3.0, 12.0, 13.75, 13.0)
            },
            { Block.box(3.0, 0.0, 0.0, 13.0, 11.0, 10.0) }

    };

    public GrinchOrnamentBlock() {
        super(ORNAMENT_SHAPES);
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
