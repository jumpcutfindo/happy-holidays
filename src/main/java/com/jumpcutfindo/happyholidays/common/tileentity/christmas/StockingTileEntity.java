package com.jumpcutfindo.happyholidays.common.tileentity.christmas;

import java.util.Random;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.StockingBlock;
import com.jumpcutfindo.happyholidays.common.registry.TileEntityRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

public class StockingTileEntity extends TileEntity implements IChristmasTileEntity {
    public static final String TILE_ENTITY_ID = "stocking";

    private static final ResourceLocation STOCKING_PRESENTS_LOOT_TABLE = new ResourceLocation("happyholidays"
            + ":blocks/stocking_presents");

    private boolean isEmpty = true;
    private boolean isDoneForNight = false;
    private Random random = new Random();

    public StockingTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public StockingTileEntity() {
        this(TileEntityRegistry.STOCKING_ENTITY_TYPE.get());
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

            ((ServerWorld) this.level).sendParticles(ParticleTypes.HAPPY_VILLAGER,
                    this.random.nextDouble(),
                    this.random.nextDouble() + 0.5D,
                    this.random.nextDouble(),
                    1, d0, d1, d2, 0.0D);
        }

        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
    }

    public void dropStockingItems() {
        LootTable lootTable = this.level.getServer().getLootTables().get(STOCKING_PRESENTS_LOOT_TABLE);

        LootContext ctx = new LootContext.Builder((ServerWorld) this.level)
                .withRandom(this.random)
                .withParameter(LootParameters.ORIGIN, new Vector3d(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ()))
                .withParameter(LootParameters.BLOCK_STATE, this.getBlockState())
                .withParameter(LootParameters.TOOL, Items.WOODEN_HOE.getDefaultInstance())
                .create(LootParameterSets.BLOCK);

        lootTable.getRandomItems(ctx)
                .forEach(itemStack -> {
                    Block.popResource(this.level, this.getBlockPos(), itemStack);
                });

        this.isEmpty = true;

        this.level.setBlock(this.getBlockPos(), this.getBlockState().setValue(StockingBlock.FILLED, false), 2);
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        nbt.putBoolean("IsEmpty", this.isEmpty);
        nbt.putBoolean("IsDoneForNight", this.isDoneForNight);

        return nbt;
    }

    @Override
    public void load(BlockState blockState, CompoundNBT nbt) {
        super.load(blockState, nbt);

        this.isEmpty = nbt.getBoolean("IsEmpty");
        this.isDoneForNight = nbt.getBoolean("IsDoneForNight");
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket(){
        CompoundNBT nbtTag = new CompoundNBT();

        nbtTag.putBoolean("IsEmpty", this.isEmpty);
        nbtTag.putBoolean("IsDoneForNight", this.isDoneForNight);

        return new SUpdateTileEntityPacket(getBlockPos(), -1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt){
        CompoundNBT nbtTag = pkt.getTag();

        this.isEmpty = nbtTag.getBoolean("IsEmpty");
        this.isDoneForNight = nbtTag.getBoolean("IsDoneForNight");
    }
}
