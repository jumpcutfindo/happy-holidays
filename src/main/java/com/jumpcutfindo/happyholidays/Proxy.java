package com.jumpcutfindo.happyholidays;

import net.minecraft.world.item.ItemStack;

public interface Proxy {
    void initClient();

    void openGuideGUI(ItemStack itemStack);
}
