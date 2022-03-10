package com.jumpcutfindo.happyholidays.proxies.client;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.screen.guide.GuideScreen;
import com.jumpcutfindo.happyholidays.common.Holiday;
import com.jumpcutfindo.happyholidays.server.resources.guide.GuideManager;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.proxies.Proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, value = Dist.CLIENT)
public class ClientProxy implements Proxy {
    private ChristmasProxy christmasProxy = new ChristmasProxy();

    public ClientProxy() {
    }

    @Override
    public void initClient() {
    }

    @Override
    public ChristmasProxy getChristmasProxy() {
        return christmasProxy;
    }

    @Override
    public void openGuideGUI(ItemStack itemStack) {
        if (itemStack.is(ChristmasItems.CHRISTMAS_GUIDE_BOOK.get())) {
            Minecraft.getInstance().setScreen(new GuideScreen(GuideManager.getGuide(Holiday.CHRISTMAS)));
        }
    }
}
