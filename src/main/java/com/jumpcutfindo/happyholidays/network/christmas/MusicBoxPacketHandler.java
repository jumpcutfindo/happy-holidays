package com.jumpcutfindo.happyholidays.network.christmas;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.common.block.entity.christmas.MusicBoxBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

public class MusicBoxPacketHandler {
    public static void handlePacket(MusicBoxPacket pkt, Supplier<NetworkEvent.Context> ctx) {
        ServerLevel serverWorld = ctx.get().getSender().getLevel();
        BlockPos blockPos = new BlockPos(pkt.x, pkt.y, pkt.z);

        if (serverWorld != null) {
            BlockEntity blockEntity = serverWorld.getBlockEntity(blockPos);

            if (blockEntity instanceof MusicBoxBlockEntity musicBoxBlockEntity) {
                if (pkt.isPlayRequest) musicBoxBlockEntity.playCurrent();
                if (pkt.isStopRequest) musicBoxBlockEntity.stopMusic();
                if (pkt.isToggleLoopRequest) musicBoxBlockEntity.toggleLoop();
                if (pkt.isPrevRequest) musicBoxBlockEntity.playPrev();
                if (pkt.isNextRequest) musicBoxBlockEntity.playNext();
            }
        }
    }
}
