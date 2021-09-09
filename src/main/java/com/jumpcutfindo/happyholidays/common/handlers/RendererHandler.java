package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.block.MusicBoxRenderer;
import com.jumpcutfindo.happyholidays.client.entity.ExplosivePresentRenderer;
import com.jumpcutfindo.happyholidays.client.entity.GingerbreadPersonEntityRenderer;
import com.jumpcutfindo.happyholidays.client.entity.GrinchEntityRenderer;
import com.jumpcutfindo.happyholidays.client.entity.SantaElfEntityRenderer;
import com.jumpcutfindo.happyholidays.client.entity.SantaEntityRenderer;
import com.jumpcutfindo.happyholidays.client.entity.SleighRenderer;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasTileEntities;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RendererHandler {
    @SubscribeEvent
    public static void handleEntityRendering(FMLClientSetupEvent event) {
        // Register gingerbread people entity rendering
        RenderingRegistry.registerEntityRenderingHandler(
                ChristmasEntities.GINGERBREAD_MAN.get(),
                GingerbreadPersonEntityRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                ChristmasEntities.SOGGY_GINGERBREAD_MAN.get(),
                GingerbreadPersonEntityRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                ChristmasEntities.SANTA_ELF.get(),
                SantaElfEntityRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                ChristmasEntities.GRINCH.get(),
                GrinchEntityRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                ChristmasEntities.HAPPY_SANTA.get(),
                SantaEntityRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                ChristmasEntities.ANGRY_SANTA.get(),
                SantaEntityRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                ChristmasEntities.SLEIGH.get(),
                SleighRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                ChristmasEntities.EXPLOSIVE_PRESENT.get(),
                ExplosivePresentRenderer::new
        );
    }

    @SubscribeEvent
    public static void handleTileEntityRendering(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(
                ChristmasTileEntities.MUSIC_BOX_ENTITY_TYPE.get(),
                MusicBoxRenderer::new
        );
    }
}
