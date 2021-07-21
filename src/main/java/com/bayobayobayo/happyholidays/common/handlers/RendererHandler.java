package com.bayobayobayo.happyholidays.common.handlers;

import com.bayobayobayo.happyholidays.client.entity.GingerbreadPersonEntityRenderer;
import com.bayobayobayo.happyholidays.common.registry.EntityRegistry;

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
    }
}
