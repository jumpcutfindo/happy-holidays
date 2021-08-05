package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.client.block.MusicBoxRenderer;
import com.jumpcutfindo.happyholidays.client.entity.GingerbreadPersonEntityRenderer;
import com.jumpcutfindo.happyholidays.client.entity.GrinchEntityRenderer;
import com.jumpcutfindo.happyholidays.client.entity.SantaElfEntityRenderer;
import com.jumpcutfindo.happyholidays.common.registry.EntityRegistry;
import com.jumpcutfindo.happyholidays.common.registry.TileEntityRegistry;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RendererHandler {
    public static void handleRenderStuff() {
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


        ClientRegistry.bindTileEntityRenderer(TileEntityRegistry.MUSIC_BOX_ENTITY_TYPE.get(), MusicBoxRenderer::new);
    }
}
