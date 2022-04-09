package com.jumpcutfindo.happyholidays.common.block.entity.cny;

import org.jetbrains.annotations.Nullable;

import com.jumpcutfindo.happyholidays.common.registry.cny.CNYBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class OfferingTableBlockEntity extends BlockEntity {
    public static final int DEFAULT_LIGHT_DURATION = 20;

    private ItemStack itemStack = ItemStack.EMPTY;
    private int litDuration = 0;

    public OfferingTableBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public OfferingTableBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(CNYBlockEntities.OFFERING_TABLE.get(), blockPos, blockState);
    }

    public void setItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItem() {
        return this.itemStack;
    }

    public ItemStack removeItem() {
        ItemStack temp = itemStack.copy();
        this.itemStack = ItemStack.EMPTY;

        return temp;
    }

    public boolean hasItem() {
        return !this.itemStack.isEmpty();
    }

    public void lightTable() {
        this.lightTable(DEFAULT_LIGHT_DURATION);
    }

    public void lightTable(int duration) {
        this.litDuration = duration;
    }

    public boolean isTableLit() {
        return this.litDuration > 0;
    }

    public void burn() {
        this.litDuration--;

        if (this.litDuration <= 0 && !this.level.isClientSide()) {
            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        }
    }

    public int getLitDuration() {
        return this.litDuration;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState blockState, OfferingTableBlockEntity blockEntity) {
        if (blockEntity.isTableLit()) {
            blockEntity.burn();
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putInt("LitDuration", this.litDuration);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        this.litDuration = tag.getInt("LitDuration");
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, OfferingTableBlockEntity::createUpdatePacket);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();

        this.litDuration = tag.getInt("LitDuration");
    }

    private static CompoundTag createUpdatePacket(BlockEntity blockEntity) {
        CompoundTag tag = new CompoundTag();
        if (blockEntity instanceof OfferingTableBlockEntity offeringTableEntity) {
            tag.putInt("LitDuration", offeringTableEntity.getLitDuration());
        }

        return tag;
    }
}
