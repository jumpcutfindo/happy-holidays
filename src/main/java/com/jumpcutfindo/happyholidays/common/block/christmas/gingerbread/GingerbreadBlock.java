package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class GingerbreadBlock extends BaseGingerbreadBlock implements Soggifiable {
    public static final String BLOCK_ID = "gingerbread_block";

    public static final BlockBehaviour.Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.SNOW)
                    .strength(1.0f)
                    .sound(SoundType.FUNGUS);

    public GingerbreadBlock() {
        super(BLOCK_PROPERTIES);
    }
}
