package com.jumpcutfindo.happyholidays.client.guide;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.Holiday;
import com.jumpcutfindo.happyholidays.common.guide.Guide;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuideManager {
    public static final String DEFAULT_GUIDE_LOCATION = "guides/%s.json";

    public static final Map<Holiday, Guide> GUIDES = new HashMap<>();

    private static final PreparableReloadListener reloadListener = new SimplePreparableReloadListener<Map<Holiday, Guide>>() {
        @Override
        protected Map<Holiday, Guide> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
            profilerFiller.startTick();

            Gson gson = new Gson();
            Map<Holiday, Guide> guideMap = new HashMap<>();

            for (Holiday holiday : Holiday.values()) {
                String guideCode = holiday.getCode();

                ResourceLocation guideLocation = new ResourceLocation(HappyHolidaysMod.MOD_ID, String.format(DEFAULT_GUIDE_LOCATION, guideCode));
                profilerFiller.push(guideLocation::toString);

                List<Resource> guideResources = null;

                try {
                    guideResources = resourceManager.getResources(guideLocation);
                } catch (IOException e) {
                    throw new IllegalStateException(String.format("Unable to find default guide file for id:\"%s\"!", guideCode));
                }

                for (Resource resource : guideResources) {
                    profilerFiller.push("parsing");
                    InputStream inputStream = resource.getInputStream();
                    Guide guide = gson.fromJson(new InputStreamReader(inputStream), Guide.class);

                    guideMap.put(holiday, guide);
                    profilerFiller.pop();
                }
                profilerFiller.pop();
            }

            profilerFiller.endTick();
            return guideMap;
        }

        @Override
        protected void apply(Map<Holiday, Guide> guideMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
            profilerFiller.startTick();
            profilerFiller.popPush("reloading");

            for (Holiday holiday : guideMap.keySet()) {
                Guide guide = guideMap.get(holiday);

                if (GUIDES.containsKey(holiday)) GUIDES.replace(holiday, guide);
                else GUIDES.put(holiday, guide);
            }

            profilerFiller.endTick();
        }
    };

    public static PreparableReloadListener getReloadListener() {
        return reloadListener;
    }

    public static Guide getGuide(Holiday holiday) {
        return GUIDES.get(holiday);
    }
}