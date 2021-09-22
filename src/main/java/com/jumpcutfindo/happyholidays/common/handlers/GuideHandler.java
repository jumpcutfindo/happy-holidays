package com.jumpcutfindo.happyholidays.common.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.guide.Guide;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.LanguageInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GuideHandler {
    public static final String DEFAULT_LANGUAGE_CODE = "en_us";
    public static final String DEFAULT_GUIDE_LOCATION = "guides/%s/%s.json";

    public static final String[] GUIDE_CODES = { "christmas" };

    public static Map<String, Guide> GUIDES;

    public static String CURRENT_LANGUAGE_CODE;

    @SubscribeEvent
    public static void registerGuides(@Nullable FMLClientSetupEvent event) {
        HappyHolidaysMod.LOGGER.debug("Attempting to register guides for Happy Holidays...");
        GUIDES = Maps.newHashMap();

        LanguageInfo language = Minecraft.getInstance().getLanguageManager().getSelected();
        CURRENT_LANGUAGE_CODE = language.getCode();
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();

        for (String guideCode : GUIDE_CODES) {
            String defaultLocation = String.format(DEFAULT_GUIDE_LOCATION, DEFAULT_LANGUAGE_CODE, guideCode);
            String localisedLocation = String.format(DEFAULT_GUIDE_LOCATION, language.getCode(), guideCode);

            List<Resource> guideResources = null;

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

            for (Resource resource : guideResources) {
                InputStream inputStream = resource.getInputStream();
                Guide guide = new Gson().fromJson(new InputStreamReader(inputStream), Guide.class);

                GUIDES.put(guideCode, guide);
            }
        }

        HappyHolidaysMod.LOGGER.debug(String.format("Registered %d guides for Happy Holidays!", GUIDES.size()));
    }

    public static Guide getGuide(String code) {
        updateGuides();

        return GUIDES.get(code);
    }

    public static void updateGuides() {
        if (!Minecraft.getInstance().getLanguageManager().getSelected().getCode().equals(CURRENT_LANGUAGE_CODE)) {
            registerGuides(null);
        }
    }
}