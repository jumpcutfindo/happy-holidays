package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class SoggyGingerbreadBlock extends BaseGingerbreadBlock {
    public static final String BLOCK_ID = "soggy_gingerbread_block";

    public static final BlockBehaviour.Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.SNOW)
                    .strength(0.25f)
                    .sound(SoundType.FUNGUS);

    public SoggyGingerbreadBlock() {
        super(BLOCK_PROPERTIES);
    }
}
