package com.jumpcutfindo.happyholidays.proxies;

import com.jumpcutfindo.happyholidays.proxies.client.ChristmasProxy;

import net.minecraft.world.item.ItemStack;

public class CommonProxy implements Proxy {
    public CommonProxy() {
    }

    @Override
    public void initClient() {

    }

    public void openGuideGUI(ItemStack itemStack) {
    }

    @Override
    public ChristmasProxy getChristmasProxy() {
        return null;
    }
}
