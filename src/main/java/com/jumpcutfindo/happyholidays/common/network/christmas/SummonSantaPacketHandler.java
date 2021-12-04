package com.jumpcutfindo.happyholidays.common.network.christmas;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

public class SummonSantaPacketHandler {
    public static void handlePacket(SummonSantaPacket pkt, Supplier<NetworkEvent.Context> ctx) {
        ServerLevel serverWorld = ctx.get().getSender().getLevel();

        if (serverWorld != null) {
            BlockEntity blockEntity = serverWorld.getBlockEntity(new BlockPos(pkt.x, pkt.y, pkt.z));

            if (blockEntity instanceof ChristmasStarBlockEntity) {
                ((ChristmasStarBlockEntity) blockEntity).summonSanta();
            }
        }
    }
}
