package com.jumpcutfindo.happyholidays.common.block.entity.cny;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.cny.OfferingTableBlock;
import com.jumpcutfindo.happyholidays.common.block.cny.offering.Offering;
import com.jumpcutfindo.happyholidays.common.block.cny.offering.OfferingCombinations;
import com.jumpcutfindo.happyholidays.common.registry.cny.CNYBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class OfferingTableBlockEntity extends BlockEntity {
    public static final int DEFAULT_LIGHT_DURATION = 20;

    private UUID litPlayer;
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

    public void lightTable(Player player) {
        this.lightTable(player, DEFAULT_LIGHT_DURATION);
    }

    public void lightTable(Player player, int duration) {
        this.litDuration = duration;
        this.litPlayer = player.getUUID();
    }

    public boolean isTableLit() {
        return this.litDuration > 0;
    }

    public void burn() {
        this.litDuration--;

        if (this.litDuration <= 0) {
            this.assessOffering();
            if (!this.level.isClientSide()) {
                this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
                this.litPlayer = null;
            }
        }
    }

    public int getLitDuration() {
        return this.litDuration;
    }

    public UUID getLitPlayer() {
        return litPlayer;
    }

    public void assessOffering() {
        if (this.getBlockState().getValue(OfferingTableBlock.PART) != OfferingTableBlock.OfferingTablePart.CENTER) return;

        Direction facingDirection = this.getBlockState().getValue(OfferingTableBlock.FACING);
        BlockPos center = this.getBlockPos();
        BlockPos left = center.relative(facingDirection.getClockWise());
        BlockPos right = center.relative(facingDirection.getCounterClockWise());

        ItemStack firstItem = null, secondItem = null, thirdItem = null;

        if (this.level.getBlockEntity(left) instanceof OfferingTableBlockEntity leftBlockEntity) {
            firstItem = leftBlockEntity.removeItem();
        }

        secondItem = this.removeItem();

        if (this.level.getBlockEntity(right) instanceof OfferingTableBlockEntity rightBlockEntity) {
            thirdItem = rightBlockEntity.removeItem();
        }

        if (firstItem == null || secondItem == null || thirdItem == null) {
            HappyHolidaysMod.LOGGER.debug(String.format("Error retrieving items for offering table at %s", this.getBlockPos()));
            return;
        }

        if (!this.level.isClientSide() && this.litPlayer != null) {
            Offering offering = new Offering(firstItem, secondItem, thirdItem);
            OfferingCombinations.resolveOffering(this.level, this.level.getPlayerByUUID(this.litPlayer), this.getBlockPos(), offering);
        }
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
        if (this.litPlayer != null) tag.putUUID("LitPlayer", this.litPlayer);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        this.litDuration = tag.getInt("LitDuration");
        this.litPlayer = tag.getUUID("LitPlayer");
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, OfferingTableBlockEntity::createUpdatePacket);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();

        int currLitDuration = this.litDuration;
        this.litDuration = tag.getInt("LitDuration");
        if (currLitDuration != litDuration && litDuration == 0) this.assessOffering();

        if (pkt.getTag().contains("LitPlayer")) this.litPlayer = tag.getUUID("LitPlayer");
    }

    private static CompoundTag createUpdatePacket(BlockEntity blockEntity) {
        CompoundTag tag = new CompoundTag();
        if (blockEntity instanceof OfferingTableBlockEntity offeringTableEntity) {
            tag.putInt("LitDuration", offeringTableEntity.getLitDuration());
            if (offeringTableEntity.getLitPlayer() != null) tag.putUUID("LitPlayer", offeringTableEntity.getLitPlayer());
        }

        return tag;
    }
}
