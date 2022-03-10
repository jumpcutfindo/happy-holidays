package com.jumpcutfindo.happyholidays.network.christmas;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerEntity;

import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.network.NetworkEvent;

public class NutcrackerPacketHandler {
    public static void handlePacket(NutcrackerPacket pkt, Supplier<NetworkEvent.Context> ctx) {
        ServerLevel serverWorld = ctx.get().getSender().getLevel();

        if (serverWorld != null) {
            NutcrackerEntity nutcracker = (NutcrackerEntity) serverWorld.getEntity(pkt.entityId);
            if (nutcracker != null) nutcracker.setInteractingPlayer(null);
        }
    }
}
