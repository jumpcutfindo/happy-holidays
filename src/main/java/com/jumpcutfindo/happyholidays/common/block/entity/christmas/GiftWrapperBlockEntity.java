package com.jumpcutfindo.happyholidays.common.block.entity.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.inventory.christmas.GiftWrapperContainer;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class GiftWrapperBlockEntity extends BaseContainerBlockEntity implements ChristmasEntityBlock {
    public static final int SLOTS = 10;

    public static final int STRING_SLOT_INDEX = 0;
    public static final int PAPER_SLOT_INDEX = 1;
    public static final int DYE_SLOT_INDEX = 2;

    public static final int GIFT_ITEMS_SLOT_INDEX_START = 3;
    public static final int GIFT_ITEMS_SLOT_INDEX_END = 8;

    public static final int RESULT_SLOT_INDEX = 9;

    protected NonNullList<ItemStack> items = NonNullList.withSize(SLOTS, ItemStack.EMPTY);

    private GiftWrapperContainer container;

    protected GiftWrapperBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state) {
        super(entityType, pos, state);
    }

    public GiftWrapperBlockEntity(BlockPos pos, BlockState state) {
        this(ChristmasBlockEntities.GIFT_WRAPPER.get(), pos, state);
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container." + HappyHolidaysMod.MOD_ID + "." + "gift_wrapping_station");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory playerInv) {
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
        return ContainerHelper.removeItem(this.items, start, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack itemStack) {
        this.items.set(index, itemStack);
        if (!this.level.isClientSide() && index != RESULT_SLOT_INDEX)  this.container.slotsChanged(this);
    }

    @Override
    public boolean stillValid(Player playerEntity) {
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
    public void load(CompoundTag nbt) {
        super.load(nbt);

        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.items);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        ContainerHelper.saveAllItems(tag, this.items);
    }

    public static ItemStack assembleGift(Player playerEntity, Container giftWrapperInv) {
        NonNullList<ItemStack> giftItems = NonNullList.withSize(6, ItemStack.EMPTY);
        for (int i = GIFT_ITEMS_SLOT_INDEX_START; i <= GIFT_ITEMS_SLOT_INDEX_END; i++) {
            giftItems.set(i - GIFT_ITEMS_SLOT_INDEX_START, giftWrapperInv.getItem(i));
        }

        CompoundTag giftItemsNBT = new CompoundTag();
        ContainerHelper.saveAllItems(giftItemsNBT, giftItems);

        ItemStack giftStack = ItemStack.EMPTY;
        if (ItemStack.isSame(giftWrapperInv.getItem(DYE_SLOT_INDEX), ChristmasItems.RED_CHRISTMAS_DYE.get().getDefaultInstance())) {
            giftStack = ChristmasItems.RED_CHRISTMAS_GIFT.get().getDefaultInstance();
        } else if (ItemStack.isSame(giftWrapperInv.getItem(DYE_SLOT_INDEX), ChristmasItems.BLUE_CHRISTMAS_DYE.get().getDefaultInstance())) {
            giftStack = ChristmasItems.BLUE_CHRISTMAS_GIFT.get().getDefaultInstance();
        } else if (ItemStack.isSame(giftWrapperInv.getItem(DYE_SLOT_INDEX), ChristmasItems.YELLOW_CHRISTMAS_DYE.get().getDefaultInstance())) {
            giftStack = ChristmasItems.YELLOW_CHRISTMAS_GIFT.get().getDefaultInstance();
        } else if (ItemStack.isSame(giftWrapperInv.getItem(DYE_SLOT_INDEX), ChristmasItems.GREEN_CHRISTMAS_DYE.get().getDefaultInstance())) {
            giftStack = ChristmasItems.GREEN_CHRISTMAS_GIFT.get().getDefaultInstance();
        } else if (ItemStack.isSame(giftWrapperInv.getItem(DYE_SLOT_INDEX), ChristmasItems.GOLD_CHRISTMAS_DYE.get().getDefaultInstance())) {
            giftStack = ChristmasItems.GOLD_CHRISTMAS_GIFT.get().getDefaultInstance();
        } else if (ItemStack.isSame(giftWrapperInv.getItem(DYE_SLOT_INDEX), ChristmasItems.SILVER_CHRISTMAS_DYE.get().getDefaultInstance())) {
            giftStack = ChristmasItems.SILVER_CHRISTMAS_GIFT.get().getDefaultInstance();
        }

        CompoundTag giftTag = giftStack.getOrCreateTag();

        giftTag.put("Gifts", giftItemsNBT);
        giftTag.putString("WrappedBy", playerEntity.getDisplayName().getString());

        return giftStack;
    }

    public static boolean isValidColourModifier(ItemStack itemStack) {
        return ItemStack.isSame(itemStack, ChristmasItems.RED_CHRISTMAS_DYE.get().getDefaultInstance())
                || ItemStack.isSame(itemStack, ChristmasItems.BLUE_CHRISTMAS_DYE.get().getDefaultInstance())
                || ItemStack.isSame(itemStack, ChristmasItems.YELLOW_CHRISTMAS_DYE.get().getDefaultInstance())
                || ItemStack.isSame(itemStack, ChristmasItems.GREEN_CHRISTMAS_DYE.get().getDefaultInstance())
                || ItemStack.isSame(itemStack, ChristmasItems.SILVER_CHRISTMAS_DYE.get().getDefaultInstance())
                || ItemStack.isSame(itemStack, ChristmasItems.GOLD_CHRISTMAS_DYE.get().getDefaultInstance());
    }
}
