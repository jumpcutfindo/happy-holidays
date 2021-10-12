package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.stairs;

import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.BaseGingerbreadStairBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.SoggyGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

public class SoggyGingerbreadStair extends BaseGingerbreadStairBlock {
    public static final String BLOCK_ID = "soggy_gingerbread_stairs";

    public SoggyGingerbreadStair() {
        super(ChristmasBlocks.supplierOf(ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK), SoggyGingerbreadBlock.BLOCK_PROPERTIES);
    }
}
