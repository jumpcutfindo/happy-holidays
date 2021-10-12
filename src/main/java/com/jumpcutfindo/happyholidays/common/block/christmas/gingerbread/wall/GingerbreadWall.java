package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.wall;

import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.GingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.Soggifiable;

public class GingerbreadWall extends BaseGingerbreadWall implements Soggifiable {
    public static final String BLOCK_ID = "gingerbread_wall";

    public GingerbreadWall() {
        super(GingerbreadBlock.BLOCK_PROPERTIES);
    }
}
