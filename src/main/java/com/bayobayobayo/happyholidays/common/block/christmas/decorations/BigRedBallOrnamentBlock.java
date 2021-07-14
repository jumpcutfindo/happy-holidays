package com.bayobayobayo.happyholidays.common.block.christmas.decorations;

import com.bayobayobayo.happyholidays.common.utils.HappyHolidaysUtils;

public class BigRedBallOrnamentBlock extends OrnamentBlock {
    private static final String BLOCK_ID = "big_red_ball_ornament_block";

    public BigRedBallOrnamentBlock() {
        super(BLOCK_ID);

        this.normalShape = HappyHolidaysUtils.createShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
        this.hangingShape = HappyHolidaysUtils.createShape(4.0, 3.75, 4.0, 12.0, 11.75, 12.0);
        this.wallShape = HappyHolidaysUtils.createShape(4.0, 0.0, 0.0, 12.0, 11.75, 8.0);
    }
}
