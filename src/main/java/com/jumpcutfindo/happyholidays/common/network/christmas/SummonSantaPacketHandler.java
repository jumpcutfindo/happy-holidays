package com.jumpcutfindo.happyholidays.common.network.christmas;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

public class SummonSantaPacketHandler {
    public static void handlePacket(SummonSantaPacket pkt, Supplier<NetworkEvent.Context> ctx) {
        ServerWorld serverWorld = ctx.get().getSender().getLevel();

        if (serverWorld != null) {
            TileEntity tileEntity = serverWorld.getBlockEntity(new BlockPos(pkt.x, pkt.y, pkt.z));

            if (tileEntity instanceof ChristmasStarTileEntity) {
                ((ChristmasStarTileEntity) tileEntity).summonSanta();
            }
        }
    }
}
