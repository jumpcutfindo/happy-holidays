package com.jumpcutfindo.happyholidays.data.server;

import static com.jumpcutfindo.happyholidays.data.server.BaseAdvancementProvider.createAdvancement;
import static com.jumpcutfindo.happyholidays.data.server.BaseAdvancementProvider.createRootAdvancement;

import java.util.function.Consumer;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasTriggers;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.PlacedBlockTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class Advancements implements Consumer<Consumer<Advancement>> {
    private Advancement christmasRoot = null;

    @Override
    public void accept(Consumer<Advancement> advancementConsumer) {
        christmasRoot = createRootAdvancement(ChristmasItems.RED_CHRISTMAS_GIFT_ITEM.get(), translatable("root"), getBackground(), FrameType.TASK, true, true, false)
                .addCriterion("has_any", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.ANY))
                .save(advancementConsumer, advancementId("root"));

        Advancement guideBook = createAdvancement(christmasRoot, ChristmasItems.CHRISTMAS_GUIDE_BOOK.get(), translatable("guide_book"))
                .addCriterion("guide_book", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.CHRISTMAS_GUIDE_BOOK.get()))
                .save(advancementConsumer, advancementId("guide_book"));

        presentsBranch(advancementConsumer);
        grinchBranch(advancementConsumer);
        ornamentsBranch(advancementConsumer);
    }

    private void presentsBranch(Consumer<Advancement> advancementConsumer) {
        Advancement presentStart = createAdvancement(christmasRoot, ChristmasItems.PRESENT_SCRAPS.get(), translatable("present_scraps"))
                .addCriterion("has_scraps", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.PRESENT_SCRAPS.get()))
                .save(advancementConsumer, advancementId("present_scraps"));

        Advancement getBabyPresent = createAdvancement(presentStart, ChristmasItems.BABY_PRESENT.get(), translatable("present_obtain_baby"))
                .addCriterion("has_present", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BABY_PRESENT.get()))
                .save(advancementConsumer, advancementId("present_obtain_baby"));

        Advancement growBabyPresent = createAdvancement(getBabyPresent, ChristmasItems.BABY_PRESENT.get(), translatable("present_plant_baby"))
                .addCriterion("placed_present", PlacedBlockTrigger.TriggerInstance.placedBlock(ChristmasBlocks.BABY_PRESENT.get()))
                .save(advancementConsumer, advancementId("present_plant_baby"));

        Advancement babyPresentOrnament = createAdvancement(growBabyPresent, ChristmasItems.BABY_PRESENT_ORNAMENT.get(), translatable("present_baby_ornament"), FrameType.GOAL, true, false, false)
                .addCriterion("has_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BABY_PRESENT_ORNAMENT.get()))
                .save(advancementConsumer, advancementId("present_baby_ornament"));

        Advancement adultPresentOrnament = createAdvancement(growBabyPresent, ChristmasItems.ADULT_PRESENT_ORNAMENT.get(), translatable("present_adult_ornament"), FrameType.GOAL, true, false, false)
                .addCriterion("has_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ADULT_PRESENT_ORNAMENT.get()))
                .save(advancementConsumer, advancementId("present_adult_ornament"));

        Advancement elderPresentOrnament = createAdvancement(growBabyPresent, ChristmasItems.ELDER_PRESENT_ORNAMENT.get(), translatable("present_elder_ornament"), FrameType.GOAL, true, false, false)
                .addCriterion("has_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ELDER_PRESENT_ORNAMENT.get()))
                .save(advancementConsumer, advancementId("present_elder_ornament"));
    }

    private void grinchBranch(Consumer<Advancement> advancementConsumer) {
        Advancement grinchStart = createAdvancement(christmasRoot, ChristmasItems.GRINCH_ORNAMENT.get(), translatable("grinch_encounter"))
                .addCriterion("encounter_grinch", ChristmasTriggers.CHRISTMAS_GRINCH_ENCOUNTER.getInstance())
                .save(advancementConsumer, advancementId("grinch_encounter"));

        Advancement grinchAppeasement = createAdvancement(grinchStart, ChristmasItems.RED_CHRISTMAS_GIFT_ITEM.get(), translatable("grinch_appeasement"))
                .addCriterion("appease_grinch", ChristmasTriggers.CHRISTMAS_GRINCH_APPEASE.getInstance())
                .save(advancementConsumer, advancementId("grinch_appeasement"));

        Advancement grinchOrnament = createAdvancement(grinchAppeasement, ChristmasItems.GRINCH_ORNAMENT.get(), translatable("grinch_ornament"), FrameType.GOAL, true, false, false)
                .addCriterion("has_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GRINCH_ORNAMENT.get()))
                .save(advancementConsumer, advancementId("grinch_ornament"));
    }

    private void ornamentsBranch(Consumer<Advancement> advancementConsumer) {
        Advancement ornamentStart = createAdvancement(christmasRoot, ChristmasItems.RED_BAUBLE.get(), translatable("ornament_start"))
                .addCriterion("has_string", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STRING))
                .addCriterion("has_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHITE_WOOL))
                .addCriterion("has_glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
                .save(advancementConsumer, advancementId("ornament_start"));

        Advancement ornamentDyes = createAdvancement(ornamentStart, ChristmasItems.RED_CHRISTMAS_DYE.get(), translatable("ornament_dyes"))
                .addCriterion("has_red_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.RED_CHRISTMAS_DYE.get()))
                .addCriterion("has_blue_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BLUE_CHRISTMAS_DYE.get()))
                .addCriterion("has_yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.YELLOW_CHRISTMAS_DYE.get()))
                .addCriterion("has_green_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GREEN_CHRISTMAS_DYE.get()))
                .addCriterion("has_gold_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SILVER_CHRISTMAS_DYE.get()))
                .addCriterion("has_silver_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GOLD_CHRISTMAS_DYE.get()))
                .requirements(RequirementsStrategy.OR)
                .save(advancementConsumer, advancementId("ornament_dyes"));

        Advancement ornamentBaubles = createAdvancement(ornamentDyes, ChristmasItems.RED_BAUBLE.get(), translatable("ornament_baubles"))
                .addCriterion("has_red_bauble", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.RED_BAUBLE.get()))
                .addCriterion("has_blue_bauble", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BLUE_BAUBLE.get()))
                .addCriterion("has_yellow_bauble", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.YELLOW_BAUBLE.get()))
                .addCriterion("has_green_bauble", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GREEN_BAUBLE.get()))
                .addCriterion("has_gold_bauble", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SILVER_BAUBLE.get()))
                .addCriterion("has_silver_bauble", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GOLD_BAUBLE.get()))
                .requirements(RequirementsStrategy.AND)
                .save(advancementConsumer, advancementId("ornament_baubles"));

        Advancement ornamentBigBaubles = createAdvancement(ornamentDyes, ChristmasItems.BIG_RED_BAUBLE.get(), translatable("ornament_big_baubles"))
                .addCriterion("has_red_bauble", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BIG_RED_BAUBLE.get()))
                .addCriterion("has_blue_bauble", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BIG_BLUE_BAUBLE.get()))
                .addCriterion("has_yellow_bauble", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BIG_YELLOW_BAUBLE.get()))
                .addCriterion("has_green_bauble", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BIG_GREEN_BAUBLE.get()))
                .addCriterion("has_gold_bauble", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BIG_SILVER_BAUBLE.get()))
                .addCriterion("has_silver_bauble", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BIG_GOLD_BAUBLE.get()))
                .requirements(RequirementsStrategy.AND)
                .save(advancementConsumer, advancementId("ornament_big_baubles"));

        Advancement ornamentTinsels = createAdvancement(ornamentDyes, ChristmasItems.RED_TINSEL.get(), translatable("ornament_tinsels"))
                .addCriterion("has_red_tinsel", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.RED_TINSEL.get()))
                .addCriterion("has_blue_tinsel", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BLUE_TINSEL.get()))
                .addCriterion("has_yellow_tinsel", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.YELLOW_TINSEL.get()))
                .addCriterion("has_green_tinsel", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GREEN_TINSEL.get()))
                .addCriterion("has_gold_tinsel", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SILVER_TINSEL.get()))
                .addCriterion("has_silver_tinsel", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GOLD_TINSEL.get()))
                .requirements(RequirementsStrategy.AND)
                .save(advancementConsumer, advancementId("ornament_tinsels"));

        Advancement ornamentLights = createAdvancement(ornamentDyes, ChristmasItems.RED_CHRISTMAS_LIGHTS.get(), translatable("ornament_lights"))
                .addCriterion("has_red_lights", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.RED_CHRISTMAS_LIGHTS.get()))
                .addCriterion("has_blue_lights", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BLUE_CHRISTMAS_LIGHTS.get()))
                .addCriterion("has_yellow_lights", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.YELLOW_CHRISTMAS_LIGHTS.get()))
                .addCriterion("has_green_lights", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GREEN_CHRISTMAS_LIGHTS.get()))
                .addCriterion("has_gold_lights", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SILVER_CHRISTMAS_LIGHTS.get()))
                .addCriterion("has_silver_lights", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GOLD_CHRISTMAS_LIGHTS.get()))
                .requirements(RequirementsStrategy.AND)
                .save(advancementConsumer, advancementId("ornament_lights"));

        Advancement ornamentHoarder = createAdvancement(ornamentStart, ChristmasItems.GOLD_BAUBLE.get(), translatable("ornament_hoarder"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("has_baubles",
                        InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item().of(ChristmasItems.RED_BAUBLE.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                            ItemPredicate.Builder.item().of(ChristmasItems.BLUE_BAUBLE.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                            ItemPredicate.Builder.item().of(ChristmasItems.YELLOW_BAUBLE.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                            ItemPredicate.Builder.item().of(ChristmasItems.GREEN_BAUBLE.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                            ItemPredicate.Builder.item().of(ChristmasItems.GOLD_BAUBLE.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                            ItemPredicate.Builder.item().of(ChristmasItems.SILVER_BAUBLE.get()).withCount(MinMaxBounds.Ints.exactly(64)).build()
                        ))
                .addCriterion("has_big_baubles",
                        InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item().of(ChristmasItems.BIG_RED_BAUBLE.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                            ItemPredicate.Builder.item().of(ChristmasItems.BIG_BLUE_BAUBLE.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                            ItemPredicate.Builder.item().of(ChristmasItems.BIG_YELLOW_BAUBLE.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                            ItemPredicate.Builder.item().of(ChristmasItems.BIG_GREEN_BAUBLE.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                            ItemPredicate.Builder.item().of(ChristmasItems.BIG_GOLD_BAUBLE.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                            ItemPredicate.Builder.item().of(ChristmasItems.BIG_SILVER_BAUBLE.get()).withCount(MinMaxBounds.Ints.exactly(64)).build()
                        ))
                .addCriterion("has_tinsels",
                        InventoryChangeTrigger.TriggerInstance.hasItems(
                                ItemPredicate.Builder.item().of(ChristmasItems.RED_TINSEL.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.BLUE_TINSEL.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.YELLOW_TINSEL.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.GREEN_TINSEL.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.GOLD_TINSEL.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.SILVER_TINSEL.get()).withCount(MinMaxBounds.Ints.exactly(64)).build()
                        ))
                .addCriterion("has_christmas_lights",
                        InventoryChangeTrigger.TriggerInstance.hasItems(
                                ItemPredicate.Builder.item().of(ChristmasItems.RED_CHRISTMAS_LIGHTS.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.BLUE_CHRISTMAS_LIGHTS.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.YELLOW_CHRISTMAS_LIGHTS.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.GREEN_CHRISTMAS_LIGHTS.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.GOLD_CHRISTMAS_LIGHTS.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.SILVER_CHRISTMAS_LIGHTS.get()).withCount(MinMaxBounds.Ints.exactly(64)).build()
                        ))
                .rewards(AdvancementRewards.Builder.experience(50).build())
                .requirements(RequirementsStrategy.AND)
                .save(advancementConsumer, advancementId("ornament_hoarder"));

        Advancement fancyOrnamentHoarder = createAdvancement(ornamentStart, ChristmasItems.GINGERBREAD_MAN_ORNAMENT.get(), translatable("ornament_fancy_hoarder"), FrameType.GOAL, true, false, false)
                .addCriterion("has_gingerbread_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GINGERBREAD_MAN_ORNAMENT.get()))
                .addCriterion("has_baby_present_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BABY_PRESENT_ORNAMENT.get()))
                .addCriterion("has_adult_present_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ADULT_PRESENT_ORNAMENT.get()))
                .addCriterion("has_elder_present_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ELDER_PRESENT_ORNAMENT.get()))
                .addCriterion("has_candy_cane_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.CANDY_CANE_ORNAMENT.get()))
                .addCriterion("has_grinch_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GRINCH_ORNAMENT.get()))
                .addCriterion("has_santa_elf_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SANTA_ELF_ORNAMENT.get()))
                .rewards(AdvancementRewards.Builder.experience(150).build())
                .requirements(RequirementsStrategy.AND)
                .save(advancementConsumer, advancementId("ornament_fancy_hoarder"));
    }

    private ResourceLocation getBackground() {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/gui/advancements/backgrounds/christmas.png");
    }

    private String translatable(String id) {
        return HappyHolidaysMod.MOD_ID + ".christmas." + id;
    }

    private String advancementId(String id) {
        return HappyHolidaysMod.MOD_ID + ":christmas/" + id;
    }
}
