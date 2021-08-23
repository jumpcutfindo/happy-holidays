package com.jumpcutfindo.happyholidays.common.network.christmas;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

public class SummonSantaPacket {
    public boolean shouldSummonSanta;
    public int x, y, z;

    public SummonSantaPacket(BlockPos blockPos) {
        this.x = blockPos.getX();
        this.y = blockPos.getY();
        this.z = blockPos.getZ();

        this.shouldSummonSanta = true;
    }

    public SummonSantaPacket(PacketBuffer buffer) {
        this.shouldSummonSanta = buffer.readBoolean();

        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
    }

    public static void encode(SummonSantaPacket packet, PacketBuffer packetBuffer) {
        packetBuffer.writeBoolean(packet.shouldSummonSanta);

        packetBuffer.writeInt(packet.x);
        packetBuffer.writeInt(packet.y);
        packetBuffer.writeInt(packet.z);
    }

    public static void handle(SummonSantaPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() ->
                // Make sure it's only executed on the physical client
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> SummonSantaPacketHandler.handlePacket(packet, ctx))
        );
        ctx.get().setPacketHandled(true);
    }
}
