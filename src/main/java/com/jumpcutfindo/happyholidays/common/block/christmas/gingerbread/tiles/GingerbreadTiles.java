package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.tiles;

import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.GingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.Soggifiable;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.bricks.BaseGingerbreadBricks;

public class GingerbreadTiles extends BaseGingerbreadBricks implements Soggifiable {
    public static final String BLOCK_ID = "gingerbread_tiles";

    public GingerbreadTiles() {
        super(GingerbreadBlock.BLOCK_PROPERTIES);
    }
}
