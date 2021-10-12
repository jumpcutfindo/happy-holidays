package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class BaseGingerbreadBlock extends ChristmasBlock {
    public static final BlockBehaviour.Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.SNOW)
                    .strength(0.5f)
                    .sound(SoundType.FUNGUS);

    public static final BlockBehaviour.Properties SOGGY_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.SNOW)
                    .strength(0.25f)
                    .sound(SoundType.FUNGUS);

    public static final Item.Properties ITEM_PROPERITES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public BaseGingerbreadBlock() {
        super(BLOCK_PROPERTIES);
    }
}
