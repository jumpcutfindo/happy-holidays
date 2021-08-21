package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.block.MusicBoxRenderer;
import com.jumpcutfindo.happyholidays.client.entity.GingerbreadPersonEntityRenderer;
import com.jumpcutfindo.happyholidays.client.entity.GrinchEntityRenderer;
import com.jumpcutfindo.happyholidays.client.entity.SantaElfEntityRenderer;
import com.jumpcutfindo.happyholidays.client.entity.SantaEntityRenderer;
import com.jumpcutfindo.happyholidays.common.registry.EntityRegistry;
import com.jumpcutfindo.happyholidays.common.registry.TileEntityRegistry;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RendererHandler {
    @SubscribeEvent
    public static void handleRenderStuff(FMLClientSetupEvent event) {
        // Register gingerbread people entity rendering
        RenderingRegistry.registerEntityRenderingHandler(
                EntityRegistry.GINGERBREAD_MAN.get(),
                GingerbreadPersonEntityRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                EntityRegistry.SOGGY_GINGERBREAD_MAN.get(),
                GingerbreadPersonEntityRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                EntityRegistry.SANTA_ELF.get(),
                SantaElfEntityRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                EntityRegistry.GRINCH.get(),
                GrinchEntityRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                EntityRegistry.HAPPY_SANTA.get(),
                SantaEntityRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                EntityRegistry.ANGRY_SANTA.get(),
                SantaEntityRenderer::new
        );

        ClientRegistry.bindTileEntityRenderer(TileEntityRegistry.MUSIC_BOX_ENTITY_TYPE.get(), MusicBoxRenderer::new);
    }
}
