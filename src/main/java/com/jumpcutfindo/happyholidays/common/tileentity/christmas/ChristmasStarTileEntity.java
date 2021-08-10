package com.jumpcutfindo.happyholidays.common.tileentity.christmas;

import java.util.List;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.head.HeadOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.ChristmasStarBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.ChristmasStarTier;
import com.jumpcutfindo.happyholidays.common.container.christmas.star.ChristmasStarContainer;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.registry.EffectRegistry;
import com.jumpcutfindo.happyholidays.common.registry.TileEntityRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.BeaconTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ChristmasStarTileEntity extends LockableTileEntity implements ChristmasTileEntity, ITickableTileEntity {
    public static final String TILE_ENTITY_ID = "christmas_star_block";
    public static final int SLOTS = 6;

    public static final int BONUS_SLOT_INDEX = 5;

    public static final int MAX_POINTS = 100;

    public static final int MAX_RADIUS_INDEX = 5;
    public static final int[] PLAYER_EFFECT_RADIUS = { 0, 8, 16, 24, 32, 40 };
    public static final int[] ENTITY_EFFECT_RADIUS = { 0, 8, 16, 24, 32, 40 };
    public static final int[] BLOCK_EFFECT_RADIUS = { 0, 4, 8, 12, 16, 20 };

    private int currentTier;
    private int currentPoints;

    public final IIntArray dataAccess = new IIntArray() {
        @Override
        public int get(int code) {
            switch (code) {
            case 0:
                return ChristmasStarTileEntity.this.currentTier;
            case 1:
                return ChristmasStarTileEntity.this.currentPoints;
            default:
                return 0;
            }
        }

        @Override
        public void set(int code, int value) {
            switch (code) {
            case 0:
                ChristmasStarTileEntity.this.currentTier = value;
                break;
            case 1:
                ChristmasStarTileEntity.this.currentPoints = value;
                break;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    protected NonNullList<ItemStack> items = NonNullList.withSize(SLOTS, ItemStack.EMPTY);

    public ChristmasStarTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public ChristmasStarTileEntity() {
        this(TileEntityRegistry.CHRISTMAS_STAR_ENTITY_TYPE.get());
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + HappyHolidaysMod.MOD_ID + "." + TILE_ENTITY_ID);
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInv) {
        return new ChristmasStarContainer(id, playerInv, this);
    }

    @Override
    public int getContainerSize() {
        return SLOTS;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack removeItem(int start, int end) {
        return ItemStackHelper.removeItem(this.items, start, end);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack itemStack) {
        this.items.set(index, itemStack);
    }

    @Override
    public boolean stillValid(PlayerEntity playerEntity) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return playerEntity.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void load(BlockState blockState, CompoundNBT nbt) {
        super.load(blockState, nbt);

        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.items);

        this.currentTier = nbt.getInt("CurrentTier");
        this.currentPoints = nbt.getInt("CurrentPoints");
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);

        ItemStackHelper.saveAllItems(nbt, this.items);

        nbt.putInt("CurrentTier", this.currentTier);
        nbt.putInt("CurrentPoints", this.currentPoints);

        return nbt;
    }

    @Override
    public void tick() {
        if (!this.level.isClientSide() && this.level.getGameTime() % 80L == 0L) {
            this.applyPlayerEffects();
        }

        this.updatePoints();
    }

    public void applyPlayerEffects() {
        if (!this.level.isClientSide() && this.currentTier > 0) {
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(this.worldPosition).inflate(PLAYER_EFFECT_RADIUS[this.currentTier]);
            List<PlayerEntity> playerList = this.level.getEntitiesOfClass(PlayerEntity.class, axisAlignedBB);

            for (PlayerEntity playerEntity : playerList) {
                playerEntity.addEffect(new EffectInstance(EffectRegistry.SPIRIT_OF_CHRISTMAS_EFFECT.get(),
                        200, this.currentTier - 1, true, true));
            }
        }
    }

    public int getCurrentTier() {
        return currentTier;
    }

    public boolean isBonusActive() {
        return !this.items.get(BONUS_SLOT_INDEX).isEmpty();
    }

    /**
     * Updates points and tiers. Items are split into a few tiers:
     * 0. Trash tier - any basic item gives zero points.
     * 1. Basic tier - any basic Christmas items / ornaments / blocks will give 5 points each
     * 2. Pro tier - any rare Christmas ornaments
     */
    private void updatePoints() {
        // Checks basic item points
        int newPoints = 0;
        List<ItemStack> itemStackList = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            ItemStack itemStack = this.items.get(i);

            if (itemStackList.stream().anyMatch(itemStack1 -> ItemStack.isSame(itemStack, itemStack1))) {
                // Checks if the new item added is unique (avoid double counting)
                continue;
            } else {
                itemStackList.add(itemStack);
            }

            if (ChristmasItem.isBasicOrnamentItem(itemStack)) {
                newPoints += 5;
            } else if (ChristmasItem.isRareOrnamentItem(itemStack)) {
                newPoints += 10;
            } else if (ChristmasItem.isLegendaryOrnamentItem(itemStack)) {
                newPoints += 20;
            }
        }

        this.currentPoints = Math.min(newPoints, MAX_POINTS);

        // Update tier
        if (this.currentPoints == 0) {
            changeTier(0);
        } else if (this.currentPoints <= 20) {
            changeTier(1);
        } else if (this.currentPoints <= 40) {
            changeTier(2);
        } else if (this.currentPoints <= 60) {
            changeTier(3);
        } else if (this.currentPoints <= 80) {
            changeTier(4);
        } else {
            changeTier(5);
        }
    }

    private void changeTier(int newTier) {
        int oldTier = this.currentTier;
        this.currentTier = newTier;

        if (oldTier != newTier && this.level != null) {
            ChristmasStarTier starTier = newTier == 0 ? ChristmasStarTier.TIER_0
                    : newTier == 1 ? ChristmasStarTier.TIER_1
                    : newTier == 2 ? ChristmasStarTier.TIER_2
                    : newTier == 3 ? ChristmasStarTier.TIER_3
                    : newTier == 4 ? ChristmasStarTier.TIER_4
                    : ChristmasStarTier.TIER_5;

            this.level.setBlock(this.worldPosition, this.getBlockState().setValue(ChristmasStarBlock.STAR_TIER,
                    starTier), 0);
            this.level.playSound(null, this.worldPosition.getX(), this.worldPosition.getY(),
                    this.worldPosition.getZ(), SoundEvents.NOTE_BLOCK_BELL, SoundCategory.BLOCKS, 1.0F,
                    1.0F + newTier * 0.1F);
        }
    }

    public static ChristmasStarTileEntity getNearestStar(World world, BlockPos blockPos) {
        int radius = ChristmasStarTileEntity.BLOCK_EFFECT_RADIUS[MAX_RADIUS_INDEX];

        for (TileEntity tileEntity : world.blockEntityList) {
            if (tileEntity instanceof ChristmasStarTileEntity && new AxisAlignedBB(tileEntity.getBlockPos()).inflate(radius).contains(blockPos.getX(), blockPos.getY(), blockPos.getZ()))
                return (ChristmasStarTileEntity) tileEntity;
        }

        return null;
    }
}
