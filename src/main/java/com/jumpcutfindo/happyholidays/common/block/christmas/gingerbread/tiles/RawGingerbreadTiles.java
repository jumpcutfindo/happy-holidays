package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.tiles;

import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.GingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.Soggifiable;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.bricks.BaseGingerbreadBricks;

public class RawGingerbreadTiles extends BaseGingerbreadBricks implements Soggifiable {
    public static final String BLOCK_ID = "gingerbread_dough_tiles";

    public RawGingerbreadTiles() {
        super(GingerbreadBlock.BLOCK_PROPERTIES);
    }
}
