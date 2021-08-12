package com.jumpcutfindo.happyholidays.common.block.christmas;

import com.jumpcutfindo.happyholidays.common.block.HappyHolidaysBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.BaseCandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ConnectedOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.OrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.BaseGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.PresentBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;

public class ChristmasBlock extends Block implements HappyHolidaysBlock {
    public final String blockId;

    private final Properties properties;
    private final Item.Properties itemProperties;

    public ChristmasBlock(String blockId, Properties properties, Item.Properties itemProperties) {
        super(properties);

        this.blockId = blockId;
        this.properties = properties;
        this.itemProperties = itemProperties;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public void configureBlock() {
        // Default left empty since we don't want the block to do anything
    }

    public static boolean isDecorationBlock(Block block) {
        return (block instanceof ConnectedOrnamentBlock || block instanceof OrnamentBlock);
    }

    public static boolean isInfluencedByStar(Block block) {
        return block instanceof PresentBlock || block instanceof BaseCandyCaneBlock;
    }
}
