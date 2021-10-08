package com.jumpcutfindo.happyholidays.common.network.christmas;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class MusicBoxPacket {
    public int x, y, z;

    public boolean isPlayRequest;
    public boolean isStopRequest;
    public boolean isToggleLoopRequest;

    private MusicBoxPacket(BlockPos blockPos, boolean isPlayRequest, boolean isStopRequest, boolean isToggleLoopRequest) {
        this.x = blockPos.getX();
        this.y = blockPos.getY();
        this.z = blockPos.getZ();

        this.isPlayRequest = isPlayRequest;
        this.isStopRequest = isStopRequest;
        this.isToggleLoopRequest = isToggleLoopRequest;
    }

    public MusicBoxPacket(FriendlyByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();

        this.isPlayRequest = buffer.readBoolean();
        this.isStopRequest = buffer.readBoolean();
        this.isToggleLoopRequest = buffer.readBoolean();
    }

    public static void encode(MusicBoxPacket packet, FriendlyByteBuf packetBuffer) {
        packetBuffer.writeInt(packet.x);
        packetBuffer.writeInt(packet.y);
        packetBuffer.writeInt(packet.z);

        packetBuffer.writeBoolean(packet.isPlayRequest);
        packetBuffer.writeBoolean(packet.isStopRequest);
        packetBuffer.writeBoolean(packet.isToggleLoopRequest);
    }

    public static void handle(MusicBoxPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() ->
                // Make sure it's only executed on the physical client
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> MusicBoxPacketHandler.handlePacket(packet, ctx))
        );
        ctx.get().setPacketHandled(true);
    }

    public static MusicBoxPacket createPlayRequestPacket(BlockPos blockPos) {
        return new MusicBoxPacket(blockPos, true, false, false);
    }

    public static MusicBoxPacket createStopRequestPacket(BlockPos blockPos) {
        return new MusicBoxPacket(blockPos, false, true, false);
    }

    public static MusicBoxPacket createToggleLoopRequestPacket(BlockPos blockPos) {
        return new MusicBoxPacket(blockPos, false, false, true);
    }
}
