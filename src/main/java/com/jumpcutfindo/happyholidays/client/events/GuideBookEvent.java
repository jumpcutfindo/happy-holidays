package com.jumpcutfindo.happyholidays.client.events;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;

public class GuideBookEvent extends Event {
    private final ItemStack guideBook;

    public GuideBookEvent(ItemStack guideBook) {
        this.guideBook = guideBook;
    }

    public ItemStack getGuideBook() {
        return guideBook;
    }

    public static class Open extends GuideBookEvent {
        public Open(ItemStack guideBook) {
            super(guideBook);
        }
    }
}
