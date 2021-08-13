package com.jumpcutfindo.happyholidays.common.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.guide.Guide;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundSystem;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.client.resources.Language;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.SimpleReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.ClientModLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.server.LanguageHook;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GuideHandler {
    public static final String DEFAULT_LANGUAGE_CODE = "en_us";
    public static final String DEFAULT_GUIDE_LOCATION = "guides/%s/%s.json";

    public static final String[] GUIDE_CODES = { "christmas" };

    public static List<Guide> GUIDES;

    @SubscribeEvent
    public static void registerGuides(@Nullable FMLClientSetupEvent event) {
        HappyHolidaysMod.LOGGER.debug("Attempting to register guides for Happy Holidays...");
        GUIDES = Lists.newArrayList();

        Language language = Minecraft.getInstance().getLanguageManager().getSelected();
        IResourceManager resourceManager = Minecraft.getInstance().getResourceManager();

        for (String guideCode : GUIDE_CODES) {
            String defaultLocation = String.format(DEFAULT_GUIDE_LOCATION, DEFAULT_LANGUAGE_CODE, guideCode);
            String localisedLocation = String.format(DEFAULT_GUIDE_LOCATION, language.getCode(), guideCode);

            List<IResource> guideResources = null;

            // Try loading localised version, and if not exists try loading default version
            try {
                guideResources = resourceManager.getResources(new ResourceLocation(HappyHolidaysMod.MOD_ID, localisedLocation));
            } catch (IOException e) {
                try {
                    guideResources = resourceManager.getResources(new ResourceLocation(HappyHolidaysMod.MOD_ID,
                            defaultLocation));
                } catch (IOException e1) {
                    throw new IllegalStateException(String.format("Unable to find default guide file for id:\"%s\"!",
                            guideCode));
                }
            }

            for (IResource resource : guideResources) {
                InputStream inputStream = resource.getInputStream();
                Guide guide = new Gson().fromJson(new InputStreamReader(inputStream), Guide.class);

                GUIDES.add(guide);
            }
        }

        HappyHolidaysMod.LOGGER.debug(String.format("Registered %d guides for Happy Holidays!", GUIDES.size()));
    }
}