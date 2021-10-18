package com.jumpcutfindo.happyholidays.data.server;

import static com.jumpcutfindo.happyholidays.data.server.BaseAdvancementProvider.createRootAdvancement;

import java.util.function.Consumer;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;

public class Advancements implements Consumer<Consumer<Advancement>> {
    @Override
    public void accept(Consumer<Advancement> advancementConsumer) {
        Advancement christmasRoot = createRootAdvancement(ChristmasItems.CHRISTMAS_GUIDE_BOOK.get(), christmasTranslatable("root"), getBackground(), FrameType.TASK, true, true, false)
                .addCriterion("christmas_guide_book", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.CHRISTMAS_GUIDE_BOOK.get()))
                .save(advancementConsumer, christmasAdvancementId("root"));
    }

    private ResourceLocation getBackground() {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/gui/advancements/backgrounds/christmas.png");
    }

    private String christmasTranslatable(String id) {
        return HappyHolidaysMod.MOD_ID + ".christmas." + id;
    }

    private String christmasAdvancementId(String id) {
        return HappyHolidaysMod.MOD_ID + ":christmas/" + id;
    }
}
