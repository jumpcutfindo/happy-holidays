package com.jumpcutfindo.happyholidays.data.server;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class BaseAdvancementProvider extends AdvancementProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    protected final DataGenerator generator;

    public BaseAdvancementProvider(DataGenerator generator) {
        super(generator);

        this.generator = generator;
    }

    @Override
    public void run(HashCache cache) {
        Path path = this.generator.getOutputFolder();
        Set<ResourceLocation> set = Sets.newHashSet();
        Consumer<Advancement> consumer = (p_123977_) -> {
            if (!set.add(p_123977_.getId())) {
                throw new IllegalStateException("Duplicate advancement " + p_123977_.getId());
            } else {
                Path path1 = createPath(path, p_123977_);

                try {
                    DataProvider.save(GSON, cache, p_123977_.deconstruct().serializeToJson(), path1);
                } catch (IOException ioexception) {
                    HappyHolidaysMod.LOGGER.error("Couldn't save advancement {}", path1, ioexception);
                }

            }
        };

        new Advancements().accept(consumer);
    }

    public static Advancement.Builder createRootAdvancement(ItemLike displayItem, String advancementComponent, ResourceLocation backgroundResource, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().display(displayItem,
                new TranslatableComponent("advancements." + advancementComponent),
                new TranslatableComponent("advancements." + advancementComponent + ".desc"),
                backgroundResource, frameType, showToast, announceToChat, hidden);
    }

    public static Advancement.Builder createAdvancement(Advancement parent, ItemLike displayItem, String advancementComponent) {
        return createAdvancement(parent, displayItem, advancementComponent, FrameType.TASK, true, false, false);
    }

    public static Advancement.Builder createAdvancement(Advancement parent, ItemLike displayItem, String advancementComponent, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(parent).display(displayItem,
                new TranslatableComponent("advancements." + advancementComponent),
                new TranslatableComponent("advancements." + advancementComponent + ".desc"),
                null, frameType, showToast, announceToChat, hidden);
    }

    private static Path createPath(Path p_123971_, Advancement p_123972_) {
        return p_123971_.resolve("data/" + p_123972_.getId().getNamespace() + "/advancements/" + p_123972_.getId().getPath() + ".json");
    }
}
