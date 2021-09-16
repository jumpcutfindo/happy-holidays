package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadPersonEntity;

import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraftforge.eventbus.api.Event;

public class GingerbreadConversionEvent extends Event {
    private final Player playerEntity;
    private final GingerbreadPersonEntity gingerbreadPersonEntity;
    private final BlockPos conversionPos;

    public GingerbreadConversionEvent(Player playerEntity, GingerbreadPersonEntity gingerbreadPersonEntity,
                                      BlockPos conversionPos) {
           this.playerEntity = playerEntity;
           this.gingerbreadPersonEntity = gingerbreadPersonEntity;
           this.conversionPos = conversionPos;
    }

    public Player getPlayerEntity() {
        return playerEntity;
    }

    public GingerbreadPersonEntity getGingerbreadPersonEntity() {
        return gingerbreadPersonEntity;
    }

    public BlockPos getConversionPos() {
        return conversionPos;
    }

    public static class ToSoggy extends GingerbreadConversionEvent {
        public ToSoggy(Player playerEntity, GingerbreadPersonEntity gingerbreadPersonEntity,
                       BlockPos conversionPos) {
            super(playerEntity, gingerbreadPersonEntity, conversionPos);
        }
    }

    public static class ToDry extends GingerbreadConversionEvent {
        public ToDry(Player playerEntity, GingerbreadPersonEntity gingerbreadPersonEntity,
                     BlockPos conversionPos) {
            super(playerEntity, gingerbreadPersonEntity, conversionPos);
        }
    }
}
