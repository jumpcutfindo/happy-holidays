package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.stairs;

import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.RawGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.Soggifiable;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

public class RawGingerbreadStair extends BaseGingerbreadStairBlock implements Soggifiable {
    public static final String BLOCK_ID = "gingerbread_dough_stairs";

    public RawGingerbreadStair() {
        super(ChristmasBlocks.supplierOf(ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK), RawGingerbreadBlock.BLOCK_PROPERTIES);
    }
}
