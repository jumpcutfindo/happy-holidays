package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.stairs;

import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.GingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.Soggifiable;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

public class GingerbreadStair extends BaseGingerbreadStairBlock implements Soggifiable {
    public static final String BLOCK_ID = "gingerbread_stairs";

    public GingerbreadStair() {
        super(ChristmasBlocks.supplierOf(ChristmasBlocks.GINGERBREAD_BLOCK), GingerbreadBlock.BLOCK_PROPERTIES);
    }
}
