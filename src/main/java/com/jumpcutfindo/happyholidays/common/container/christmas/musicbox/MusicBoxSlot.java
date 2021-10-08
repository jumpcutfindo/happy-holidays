package com.jumpcutfindo.happyholidays.common.container.christmas.musicbox;

import com.jumpcutfindo.happyholidays.common.item.christmas.music.SheetMusicItem;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MusicBoxSlot extends Slot {
    public MusicBoxSlot(Container p_40223_, int p_40224_, int p_40225_, int p_40226_) {
        super(p_40223_, p_40224_, p_40225_, p_40226_);
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return itemStack.getItem() instanceof SheetMusicItem;
    }
}
