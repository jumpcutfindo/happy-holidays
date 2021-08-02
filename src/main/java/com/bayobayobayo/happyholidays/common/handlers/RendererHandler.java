package com.bayobayobayo.happyholidays.common.handlers;

import com.bayobayobayo.happyholidays.client.block.MusicBoxRenderer;
import com.bayobayobayo.happyholidays.client.entity.GingerbreadPersonEntityRenderer;
import com.bayobayobayo.happyholidays.client.entity.GrinchEntityRenderer;
import com.bayobayobayo.happyholidays.client.entity.SantaElfEntityRenderer;
import com.bayobayobayo.happyholidays.common.registry.EntityRegistry;
import com.bayobayobayo.happyholidays.common.registry.TileEntityRegistry;

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
