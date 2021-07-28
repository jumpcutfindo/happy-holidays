package com.bayobayobayo.happyholidays.common.entity.christmas.elf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.bayobayobayo.happyholidays.common.registry.ItemRegistry;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ByteArrayNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public class SantaElfRequest {
    public static final int TOTAL_NO_OF_ITEMS = 4;
    public static final int NO_OF_UNIQUE_BASIC_ITEMS = 4;

    public static final int MIN_BASIC_REQUESTABLE_ITEMS = 4;
    public static final int MAX_BASIC_REQUESTABLE_ITEMS = 8;
    public static final Item[] BASIC_REQUESTABLE_ITEMS = new Item[] {
            Blocks.DIRT.asItem(),
            Blocks.DIORITE.asItem(),
            Blocks.ANDESITE.asItem(),
            Blocks.GRANITE.asItem(),
            Blocks.STONE.asItem()
    };


    public Random random;
    public List<SingleElfRequest> requestedItemsList;

    private SantaElfRequest() {
        this.random = new Random();

        this.requestedItemsList = new ArrayList<>();
    }

    private void generateRequest() {
        for (int i = 0; i < NO_OF_UNIQUE_BASIC_ITEMS; i++) {
            Item randomItem = BASIC_REQUESTABLE_ITEMS[this.random.nextInt(BASIC_REQUESTABLE_ITEMS.length)];
            int randomAmount =
                    random.nextInt((MAX_BASIC_REQUESTABLE_ITEMS - MIN_BASIC_REQUESTABLE_ITEMS) + 1)
                            + MIN_BASIC_REQUESTABLE_ITEMS;

            ItemStack itemStack = randomItem.getDefaultInstance();
            itemStack.setCount(randomAmount);

            SingleElfRequest singleElfRequest = new SingleElfRequest(itemStack);
            this.requestedItemsList.add(singleElfRequest);
        }
    }

    public SingleElfRequest tryFulfilRequest(ItemStack stack) {
        for (SingleElfRequest request : requestedItemsList) {
            if (!request.isCompleted() && request.tryFulfilRequest(stack)) {
                return request;
            }
        }
        return null;
    }

    public boolean isInRequest(ItemStack stack) {
        for (SingleElfRequest request : requestedItemsList) {
            if (ItemStack.isSame(request.getRequestedItems(), stack) && request.getNumberOfItems() <= stack.getCount()) {
                return true;
            }
        }
        return false;
    }

    public ItemStack getRequestPaper() {
        ItemStack requestPaper = ItemRegistry.TOY_PARTS_REQUEST.get().getDefaultInstance();

        CompoundNBT nbt = requestPaper.getOrCreateTag();
        nbt.put("SantaElfRequest", this.createTag());

        return requestPaper;
    }

    public boolean isCompleted() {
        for (SingleElfRequest request : this.requestedItemsList) {
            if (!request.isCompleted()) return false;
        }

        return true;
    }

    public List<SingleElfRequest> getRequestedItems() {
        return requestedItemsList;
    }

    private void addEntry(ItemStack itemStack, boolean isCompleted) {
        SingleElfRequest singleElfRequest = new SingleElfRequest(itemStack);
        if (isCompleted) singleElfRequest.setCompleted();

        this.requestedItemsList.add(singleElfRequest);
    }

    public static SantaElfRequest fromTag(CompoundNBT nbt) {
        SantaElfRequest santaElfRequest = new SantaElfRequest();

        ListNBT singleRequestsNBT = nbt.getList("SingleRequests", 10);
        byte[] completedRequestNBT = nbt.getByteArray("RequestsCompleted");

        for(int i = 0; i < singleRequestsNBT.size(); i++) {
            ItemStack itemStack = ItemStack.of(singleRequestsNBT.getCompound(i));
            boolean isCompleted = completedRequestNBT[i] == (byte) 1;
            if (!itemStack.isEmpty()) {
                santaElfRequest.addEntry(itemStack, isCompleted);
            }
        }

        return santaElfRequest;
    }

    public CompoundNBT createTag() {
        CompoundNBT nbt = new CompoundNBT();

        ListNBT singleRequestsNBT = new ListNBT();

        for (SingleElfRequest singleElfRequest : this.requestedItemsList) {
            ItemStack itemstack = singleElfRequest.getRequestedItems();
            if (!itemstack.isEmpty()) {
                singleRequestsNBT.add(itemstack.save(new CompoundNBT()));
            }
        }

        ByteArrayNBT completedRequestNBT =
                new ByteArrayNBT(this.requestedItemsList.stream().map(request -> request.isCompleted() ? (byte) 1:
                        (byte) 0).collect(Collectors.toList()));

        nbt.put("SingleRequests", singleRequestsNBT);
        nbt.put("RequestsCompleted", completedRequestNBT);

        return nbt;
    }

    public static SantaElfRequest createRandomRequest() {
        SantaElfRequest request = new SantaElfRequest();
        request.generateRequest();

        return request;
    }

    public class SingleElfRequest {
        private final ItemStack requestedItems;
        private boolean isCompleted;

        public SingleElfRequest(ItemStack requestedItems) {
            this.requestedItems = requestedItems;
        }

        public int getNumberOfItems() {
            return requestedItems.getCount();
        }

        public ItemStack getRequestedItems() {
            return requestedItems;
        }

        public boolean tryFulfilRequest(ItemStack stack) {
            if (ItemStack.isSame(stack, this.requestedItems) && stack.getCount() >= requestedItems.getCount()) {
                this.setCompleted();
                return true;
            } else {
                return false;
            }
        }

        public void setCompleted() {
            this.isCompleted = true;
        }

        public boolean isCompleted() {
            return this.isCompleted;
        }

        @Override
        public String toString() {
            String itemName = this.requestedItems.getDisplayName().getString();

            return String.format("%d\u00d7 %s", this.requestedItems.getCount(), itemName.substring(1, itemName.length() - 1));
        }
    }
}
