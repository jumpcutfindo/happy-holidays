package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadPersonEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.eventbus.api.Event;

public class GingerbreadConversionEvent extends Event {
    private final PlayerEntity playerEntity;
    private final GingerbreadPersonEntity gingerbreadPersonEntity;
    private final BlockPos conversionPos;

    public GingerbreadConversionEvent(PlayerEntity playerEntity, GingerbreadPersonEntity gingerbreadPersonEntity,
                                      BlockPos conversionPos) {
           this.playerEntity = playerEntity;
           this.gingerbreadPersonEntity = gingerbreadPersonEntity;
           this.conversionPos = conversionPos;
    }

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public GingerbreadPersonEntity getGingerbreadPersonEntity() {
        return gingerbreadPersonEntity;
    }

    public BlockPos getConversionPos() {
        return conversionPos;
    }

    public static class ToSoggy extends GingerbreadConversionEvent {
        public ToSoggy(PlayerEntity playerEntity, GingerbreadPersonEntity gingerbreadPersonEntity,
                       BlockPos conversionPos) {
            super(playerEntity, gingerbreadPersonEntity, conversionPos);
        }
    }

    public static class ToDry extends GingerbreadConversionEvent {
        public ToDry(PlayerEntity playerEntity, GingerbreadPersonEntity gingerbreadPersonEntity,
                     BlockPos conversionPos) {
            super(playerEntity, gingerbreadPersonEntity, conversionPos);
        }
    }
}
