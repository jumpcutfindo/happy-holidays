package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.block.MusicBoxRenderer;
import com.jumpcutfindo.happyholidays.client.entity.ExplosivePresentRenderer;
import com.jumpcutfindo.happyholidays.client.entity.GingerbreadPersonEntityRenderer;
import com.jumpcutfindo.happyholidays.client.entity.GrinchEntityRenderer;
import com.jumpcutfindo.happyholidays.client.entity.SantaElfEntityRenderer;
import com.jumpcutfindo.happyholidays.client.entity.SantaEntityRenderer;
import com.jumpcutfindo.happyholidays.client.entity.SleighRenderer;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RendererHandler {

    @SubscribeEvent
    public static void handleEntityRendering(EntityRenderersEvent.RegisterRenderers event) {
        // Register gingerbread people entity rendering
        event.registerEntityRenderer(
                ChristmasEntities.GINGERBREAD_MAN.get(),
                GingerbreadPersonEntityRenderer::new
        );

        event.registerEntityRenderer(
                ChristmasEntities.SOGGY_GINGERBREAD_MAN.get(),
                GingerbreadPersonEntityRenderer::new
        );

        event.registerEntityRenderer(
                ChristmasEntities.SANTA_ELF.get(),
                SantaElfEntityRenderer::new
        );

        event.registerEntityRenderer(
                ChristmasEntities.GRINCH.get(),
                GrinchEntityRenderer::new
        );

        event.registerEntityRenderer(
                ChristmasEntities.HAPPY_SANTA.get(),
                SantaEntityRenderer::new
        );

        event.registerEntityRenderer(
                ChristmasEntities.ANGRY_SANTA.get(),
                SantaEntityRenderer::new
        );

        event.registerEntityRenderer(
                ChristmasEntities.SLEIGH.get(),
                SleighRenderer::new
        );

        event.registerEntityRenderer(
                ChristmasEntities.EXPLOSIVE_PRESENT.get(),
                ExplosivePresentRenderer::new
        );

        // Register block entities
        event.registerBlockEntityRenderer(
                ChristmasBlockEntities.MUSIC_BOX_ENTITY_TYPE.get(),
                MusicBoxRenderer::new
        );
    }
}
