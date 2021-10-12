package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;


import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class RawGingerbreadBlock extends BaseGingerbreadBlock implements Soggifiable {
    public static final String BLOCK_ID = "gingerbread_dough_block";

    public static final BlockBehaviour.Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.SNOW)
                    .strength(0.5f)
                    .sound(SoundType.FUNGUS);

    public RawGingerbreadBlock() {
        super(BLOCK_PROPERTIES);
    }
}
