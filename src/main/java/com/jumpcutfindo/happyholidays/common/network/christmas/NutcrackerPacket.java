package com.jumpcutfindo.happyholidays.common.network.christmas;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public class NutcrackerPacket {
    public int entityId;
    public boolean isFinishedInteracting;

    public NutcrackerPacket(int entityId) {
        this.entityId = entityId;
        this.isFinishedInteracting = true;
    }

    public NutcrackerPacket(FriendlyByteBuf buffer) {
        this.entityId = buffer.readInt();
        this.isFinishedInteracting = buffer.readBoolean();
    }

    public static void encode(NutcrackerPacket packet, FriendlyByteBuf packetBuffer) {
        packetBuffer.writeInt(packet.entityId);
        packetBuffer.writeBoolean(packet.isFinishedInteracting);
    }

    public static void handle(NutcrackerPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
                    DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> NutcrackerPacketHandler.handlePacket(packet, ctx));
                    DistExecutor.unsafeRunWhenOn(Dist.DEDICATED_SERVER, () -> () -> NutcrackerPacketHandler.handlePacket(packet, ctx));
                }
        );
        ctx.get().setPacketHandled(true);
    }
}
