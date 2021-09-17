package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadPersonEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

public class GingerbreadConversionEvent extends ChristmasEvent {
    private final GingerbreadPersonEntity gingerbreadPersonEntity;
    private final BlockPos conversionPos;

    public GingerbreadConversionEvent(GingerbreadPersonEntity gingerbreadPersonEntity, Player playerEntity,
                                      BlockPos conversionPos) {
           super(playerEntity);
           this.gingerbreadPersonEntity = gingerbreadPersonEntity;
           this.conversionPos = conversionPos;
    }

    public GingerbreadPersonEntity getGingerbreadPersonEntity() {
        return gingerbreadPersonEntity;
    }

    public BlockPos getConversionPos() {
        return conversionPos;
    }

    public static class ToSoggy extends GingerbreadConversionEvent {
        public ToSoggy(GingerbreadPersonEntity gingerbreadPersonEntity, Player playerEntity,
                       BlockPos conversionPos) {
            super(gingerbreadPersonEntity, playerEntity, conversionPos);
        }
    }

    public static class ToDry extends GingerbreadConversionEvent {
        public ToDry(GingerbreadPersonEntity gingerbreadPersonEntity, Player playerEntity,
                     BlockPos conversionPos) {
            super(gingerbreadPersonEntity, playerEntity, conversionPos);
        }
    }
}
