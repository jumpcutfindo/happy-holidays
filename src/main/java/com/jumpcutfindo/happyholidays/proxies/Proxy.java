package com.jumpcutfindo.happyholidays.proxies;

import com.jumpcutfindo.happyholidays.proxies.client.ChristmasProxy;

import net.minecraft.world.item.ItemStack;

public interface Proxy {
    void initClient();

    void openGuideGUI(ItemStack itemStack);

    ChristmasProxy getChristmasProxy();
}
