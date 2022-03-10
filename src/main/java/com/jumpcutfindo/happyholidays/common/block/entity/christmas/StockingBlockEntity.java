package com.jumpcutfindo.happyholidays.common.block.entity.christmas;

import java.util.Random;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.StockingBlock;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

public class StockingBlockEntity extends BlockEntity implements ChristmasEntityBlock {
    public static final String BLOCK_ENTITY_ID = "stocking";

    private static final ResourceLocation STOCKING_PRESENTS_LOOT_TABLE = new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/stocking_presents");
    private static final ResourceLocation ENCHANTED_STOCKING_PRESENTS_LOOT_TABLE = new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/enchanted_stocking_presents");

    private boolean isEmpty = true;
    private boolean isDoneForNight = false;
    private Random random = new Random();

    public StockingBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state) {
        super(entityType, pos, state);
    }

    public StockingBlockEntity(BlockPos pos, BlockState state) {
        this(ChristmasBlockEntities.STOCKING.get(), pos, state);
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isDoneForNight() {
        return isDoneForNight;
    }

    public void resetStocking() {
        this.isDoneForNight = false;
    }

    public void fillStocking() {
        this.isEmpty = false;
        this.isDoneForNight = true;

        this.level.setBlock(this.getBlockPos(), this.getBlockState().setValue(StockingBlock.FILLED, true), 2);

        for (int i = 0; i < 5; i++) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;

            ((ServerLevel) this.level).sendParticles(ParticleTypes.HAPPY_VILLAGER,
                    this.random.nextDouble(),
                    this.random.nextDouble() + 0.5D,
                    this.random.nextDouble(),
                    1, d0, d1, d2, 0.0D);
        }

        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
    }

    public void dropStockingItems(boolean isEnchanted) {
        LootTable lootTable =
                isEnchanted ? this.level.getServer().getLootTables().get(ENCHANTED_STOCKING_PRESENTS_LOOT_TABLE)
                        : this.level.getServer().getLootTables().get(STOCKING_PRESENTS_LOOT_TABLE);

        LootContext ctx = new LootContext.Builder((ServerLevel) this.level)
                .withRandom(this.random)
                .withParameter(LootContextParams.ORIGIN, new Vec3(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ()))
                .withParameter(LootContextParams.BLOCK_STATE, this.getBlockState())
                .withParameter(LootContextParams.TOOL, Items.WOODEN_HOE.getDefaultInstance())
                .create(LootContextParamSets.BLOCK);

        lootTable.getRandomItems(ctx)
                .forEach(itemStack -> {
                    Block.popResource(this.level, this.getBlockPos(), itemStack);
                });

        this.isEmpty = true;

        this.level.setBlock(this.getBlockPos(), this.getBlockState().setValue(StockingBlock.FILLED, false), 2);
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("IsEmpty", this.isEmpty);
        tag.putBoolean("IsDoneForNight", this.isDoneForNight);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

        this.isEmpty = nbt.getBoolean("IsEmpty");
        this.isDoneForNight = nbt.getBoolean("IsDoneForNight");
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket(){
        return ClientboundBlockEntityDataPacket.create(this, StockingBlockEntity::createUpdateTag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt){
        CompoundTag nbtTag = pkt.getTag();

        this.isEmpty = nbtTag.getBoolean("IsEmpty");
        this.isDoneForNight = nbtTag.getBoolean("IsDoneForNight");
    }

    public static CompoundTag createUpdateTag(BlockEntity blockEntity) {
        CompoundTag nbtTag = new CompoundTag();

        if (blockEntity instanceof StockingBlockEntity stockingBlockEntity) {
            nbtTag.putBoolean("IsEmpty", stockingBlockEntity.isEmpty);
            nbtTag.putBoolean("IsDoneForNight", stockingBlockEntity.isDoneForNight);
        }

        return nbtTag;
    }
}
