package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OutfitHandler {
    @SubscribeEvent
    public static void modifyEntityRendering(RenderPlayerEvent.Pre event) {
        Player player = event.getPlayer();
        for (ItemStack armorItem : player.getArmorSlots()) {
            if (armorItem.is(ChristmasItems.SNOWMAN_TOP.get())) {
                event.getRenderer().getModel().body.visible = false;
                event.getRenderer().getModel().leftArm.visible = false;
                event.getRenderer().getModel().rightArm.visible = false;
            }

            if (armorItem.is(ChristmasItems.SNOWMAN_BOTTOM.get())) {
                event.getRenderer().getModel().leftLeg.visible = false;
                event.getRenderer().getModel().rightLeg.visible = false;
            }
        }
    }
}
