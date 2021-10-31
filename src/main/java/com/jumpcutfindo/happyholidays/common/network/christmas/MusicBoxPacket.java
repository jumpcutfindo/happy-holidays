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
    public boolean isPrevRequest;
    public boolean isNextRequest;

    private MusicBoxPacket(BlockPos blockPos) {
        this.x = blockPos.getX();
        this.y = blockPos.getY();
        this.z = blockPos.getZ();
    }

    public MusicBoxPacket(FriendlyByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();

        this.isPlayRequest = buffer.readBoolean();
        this.isStopRequest = buffer.readBoolean();
        this.isToggleLoopRequest = buffer.readBoolean();
        this.isPrevRequest = buffer.readBoolean();
        this.isNextRequest = buffer.readBoolean();
    }

    public static void encode(MusicBoxPacket packet, FriendlyByteBuf packetBuffer) {
        packetBuffer.writeInt(packet.x);
        packetBuffer.writeInt(packet.y);
        packetBuffer.writeInt(packet.z);

        packetBuffer.writeBoolean(packet.isPlayRequest);
        packetBuffer.writeBoolean(packet.isStopRequest);
        packetBuffer.writeBoolean(packet.isToggleLoopRequest);
        packetBuffer.writeBoolean(packet.isPrevRequest);
        packetBuffer.writeBoolean(packet.isNextRequest);
    }

    public static void handle(MusicBoxPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> MusicBoxPacketHandler.handlePacket(packet, ctx));
                DistExecutor.unsafeRunWhenOn(Dist.DEDICATED_SERVER, () -> () -> MusicBoxPacketHandler.handlePacket(packet, ctx));
            }
        );
        ctx.get().setPacketHandled(true);
    }

    public MusicBoxPacket setPlayRequest(boolean playRequest) {
        isPlayRequest = playRequest;
        return this;
    }

    public MusicBoxPacket setStopRequest(boolean stopRequest) {
        isStopRequest = stopRequest;
        return this;
    }

    public MusicBoxPacket setToggleLoopRequest(boolean toggleLoopRequest) {
        isToggleLoopRequest = toggleLoopRequest;
        return this;
    }

    public MusicBoxPacket setPrevRequest(boolean prevRequest) {
        isPrevRequest = prevRequest;
        return this;
    }

    public MusicBoxPacket setNextRequest(boolean nextRequest) {
        isNextRequest = nextRequest;
        return this;
    }

    public static MusicBoxPacket createPlayRequestPacket(BlockPos blockPos) {
        return new MusicBoxPacket(blockPos).setPlayRequest(true);
    }

    public static MusicBoxPacket createStopRequestPacket(BlockPos blockPos) {
        return new MusicBoxPacket(blockPos).setStopRequest(true);
    }

    public static MusicBoxPacket createToggleLoopRequestPacket(BlockPos blockPos) {
        return new MusicBoxPacket(blockPos).setToggleLoopRequest(true);
    }

    public static MusicBoxPacket createPreviousRequestPacket(BlockPos blockPos) {
        return new MusicBoxPacket(blockPos).setPrevRequest(true);
    }

    public static MusicBoxPacket createNextRequestPacket(BlockPos blockPos) {
        return new MusicBoxPacket(blockPos).setNextRequest(true);
    }
}
