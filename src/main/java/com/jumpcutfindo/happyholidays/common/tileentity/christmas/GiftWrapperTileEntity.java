package com.jumpcutfindo.happyholidays.common.tileentity.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.container.christmas.gifts.GiftWrapperContainer;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasTileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class GiftWrapperTileEntity extends LockableTileEntity implements IChristmasTileEntity {
    public static final String TILE_ENTITY_ID = "gift_wrapping_station";
    public static final int SLOTS = 10;

    public static final int STRING_SLOT_INDEX = 0;
    public static final int PAPER_SLOT_INDEX = 1;
    public static final int DYE_SLOT_INDEX = 2;

    public static final int GIFT_ITEMS_SLOT_INDEX_START = 3;
    public static final int GIFT_ITEMS_SLOT_INDEX_END = 8;

    public static final int RESULT_SLOT_INDEX = 9;

    protected NonNullList<ItemStack> items = NonNullList.withSize(SLOTS, ItemStack.EMPTY);

    private GiftWrapperContainer container;

    protected GiftWrapperTileEntity(TileEntityType<?> p_i48285_1_) {
        super(p_i48285_1_);
    }

    public GiftWrapperTileEntity() {
        this(ChristmasTileEntities.GIFT_WRAPPER_ENTITY_TYPE.get());
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + HappyHolidaysMod.MOD_ID + "." + TILE_ENTITY_ID);
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInv) {
        this.container = new GiftWrapperContainer(id, playerInv, this);
        return this.container;
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
    public ItemStack removeItem(int start, int count) {
        if (!this.level.isClientSide()) this.container.slotsChanged(this);
        return ItemStackHelper.removeItem(this.items, start, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack itemStack) {
        this.items.set(index, itemStack);
        if (!this.level.isClientSide() && index != RESULT_SLOT_INDEX)  this.container.slotsChanged(this);
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
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);

        ItemStackHelper.saveAllItems(nbt, this.items);

        return nbt;
    }

    public static ItemStack assembleGift(PlayerEntity playerEntity, IInventory giftWrapperInv) {
        NonNullList<ItemStack> giftItems = NonNullList.withSize(6, ItemStack.EMPTY);
        for (int i = GIFT_ITEMS_SLOT_INDEX_START; i <= GIFT_ITEMS_SLOT_INDEX_END; i++) {
            giftItems.set(i - GIFT_ITEMS_SLOT_INDEX_START, giftWrapperInv.getItem(i));
        }

        CompoundNBT giftItemsNBT = new CompoundNBT();
        ItemStackHelper.saveAllItems(giftItemsNBT, giftItems);

        ItemStack giftStack = ItemStack.EMPTY;
        if (ItemStack.isSame(giftWrapperInv.getItem(DYE_SLOT_INDEX), Items.RED_DYE.getDefaultInstance())) {
            giftStack = ChristmasItems.RED_CHRISTMAS_GIFT_ITEM.get().getDefaultInstance();
        } else if (ItemStack.isSame(giftWrapperInv.getItem(DYE_SLOT_INDEX), Items.BLUE_DYE.getDefaultInstance())) {
            giftStack = ChristmasItems.BLUE_CHRISTMAS_GIFT_ITEM.get().getDefaultInstance();
        } else if (ItemStack.isSame(giftWrapperInv.getItem(DYE_SLOT_INDEX), Items.YELLOW_DYE.getDefaultInstance())) {
            giftStack = ChristmasItems.YELLOW_CHRISTMAS_GIFT_ITEM.get().getDefaultInstance();
        } else if (ItemStack.isSame(giftWrapperInv.getItem(DYE_SLOT_INDEX), Items.GREEN_DYE.getDefaultInstance())) {
            giftStack = ChristmasItems.GREEN_CHRISTMAS_GIFT_ITEM.get().getDefaultInstance();
        } else if (ItemStack.isSame(giftWrapperInv.getItem(DYE_SLOT_INDEX), Items.GOLD_INGOT.getDefaultInstance())) {
            giftStack = ChristmasItems.GOLD_CHRISTMAS_GIFT_ITEM.get().getDefaultInstance();
        } else {
            giftStack = ChristmasItems.SILVER_CHRISTMAS_GIFT_ITEM.get().getDefaultInstance();
        }

        CompoundNBT giftTag = giftStack.getOrCreateTag();

        giftTag.put("Gifts", giftItemsNBT);
        giftTag.putString("WrappedBy", playerEntity.getDisplayName().getString());

        return giftStack;
    }

    public static boolean isValidColourModifier(ItemStack itemStack) {
        return ItemStack.isSame(itemStack, Items.RED_DYE.getDefaultInstance())
                || ItemStack.isSame(itemStack, Items.BLUE_DYE.getDefaultInstance())
                || ItemStack.isSame(itemStack, Items.YELLOW_DYE.getDefaultInstance())
                || ItemStack.isSame(itemStack, Items.GREEN_DYE.getDefaultInstance())
                || ItemStack.isSame(itemStack, Items.IRON_INGOT.getDefaultInstance())
                || ItemStack.isSame(itemStack, Items.GOLD_INGOT.getDefaultInstance());
    }
}
