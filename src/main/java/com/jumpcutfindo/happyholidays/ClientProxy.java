package com.jumpcutfindo.happyholidays;

import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.jumpcutfindo.happyholidays.common.handlers.GuideHandler;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {
    public ClientProxy() {

    }

    @Override
    public void openGuideGUI(ItemStack itemStack) {
        if (itemStack.is(ChristmasItems.CHRISTMAS_GUIDE_BOOK.get())) {
            Minecraft.getInstance().setScreen(new GuideScreen(GuideHandler.getGuide("christmas")));
        }
    }
}
