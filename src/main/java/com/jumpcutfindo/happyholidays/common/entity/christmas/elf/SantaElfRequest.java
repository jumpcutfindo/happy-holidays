package com.jumpcutfindo.happyholidays.common.entity.christmas.elf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.nbt.ByteArrayNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.LongNBT;

public class SantaElfRequest {
    public Random random;
    public List<SingleElfRequest> requestedItemsList;
    private long expiryTime;

    SantaElfRequest() {
        this.random = new Random();

        this.requestedItemsList = new ArrayList<>();
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
        ItemStack requestPaper = ChristmasItems.TOY_PARTS_REQUEST.get().getDefaultInstance();

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

    void addEntry(ItemStack itemStack, boolean isCompleted) {
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

        santaElfRequest.setExpiryTime(nbt.getLong("ExpiryTime"));

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
        nbt.put("ExpiryTime", LongNBT.valueOf(expiryTime));

        return nbt;
    }

    void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public long getExpiryTime() {
        return this.expiryTime;
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
            if (this.requestedItems.getItem() instanceof MusicDiscItem) {
                String itemName = ((MusicDiscItem) this.requestedItems.getItem()).getDisplayName().getString();
                return String.format("%d\u00d7 Music Disc [%s]", this.requestedItems.getCount(), itemName);
            } else {
                String itemName = this.requestedItems.getHoverName().getString();
                return String.format("%d\u00d7 %s", this.requestedItems.getCount(), itemName);
            }
        }
    }
}
