package com.jumpcutfindo.happyholidays.datagen.server.christmas;

import static com.jumpcutfindo.happyholidays.datagen.server.BaseAdvancementProvider.createAdvancement;
import static com.jumpcutfindo.happyholidays.datagen.server.BaseAdvancementProvider.createRootAdvancement;

import java.util.function.Consumer;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.outfits.Outfit;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasOutfits;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasTriggers;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.PlacedBlockTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ChristmasAdvancements implements Consumer<Consumer<Advancement>> {
    private Advancement christmasRoot = null;

    @Override
    public void accept(Consumer<Advancement> advancementConsumer) {
        christmasRoot = createRootAdvancement(ChristmasItems.RED_CHRISTMAS_GIFT.get(), translatable("root"), getBackground(), FrameType.TASK, true, true, false)
                .addCriterion("has_any", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.ANY))
                .save(advancementConsumer, advancementId("root"));

        Advancement guideBook = createAdvancement(christmasRoot, ChristmasItems.CHRISTMAS_GUIDE_BOOK.get(), translatable("guide_book"))
                .addCriterion("guide_book", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.CHRISTMAS_GUIDE_BOOK.get()))
                .save(advancementConsumer, advancementId("guide_book"));

        presentsBranch(advancementConsumer);
        grinchBranch(advancementConsumer);
        ornamentsBranch(advancementConsumer);
        musicBranch(advancementConsumer);
        miscBranch(advancementConsumer);
        gingerbreadBranch(advancementConsumer);
        candyCaneBranch(advancementConsumer);
        hollyBranch(advancementConsumer);
        stockingBranch(advancementConsumer);
        starBranch(advancementConsumer);
        snowGlobeBranch(advancementConsumer);
        nutcrackerBranch(advancementConsumer);
        outfitBranch(advancementConsumer);
    }

    private void miscBranch(Consumer<Advancement> advancementConsumer) {
        Advancement explosiveTendencies = createAdvancement(christmasRoot, ChristmasItems.EXPLOSIVE_PRESENT.get(), translatable("explosive_tendencies"))
                .addCriterion("has_explosive_present", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.EXPLOSIVE_PRESENT.get()))
                .save(advancementConsumer, advancementId("explosive_tendencies"));
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
                .addCriterion("encounter_grinch", ChristmasTriggers.GRINCH_ENCOUNTER.getInstance())
                .save(advancementConsumer, advancementId("grinch_encounter"));

        Advancement grinchAppeasement = createAdvancement(grinchStart, ChristmasItems.RED_CHRISTMAS_GIFT.get(), translatable("grinch_appeasement"))
                .addCriterion("appease_grinch", ChristmasTriggers.GRINCH_APPEASE.getInstance())
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

        Advancement ornamentBells = createAdvancement(ornamentDyes, ChristmasItems.RED_CHRISTMAS_BELLS.get(), translatable("ornament_bells"))
                .addCriterion("has_red_bells", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.RED_CHRISTMAS_BELLS.get()))
                .addCriterion("has_blue_bells", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BLUE_CHRISTMAS_BELLS.get()))
                .addCriterion("has_yellow_bells", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.YELLOW_CHRISTMAS_BELLS.get()))
                .addCriterion("has_green_bells", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GREEN_CHRISTMAS_BELLS.get()))
                .addCriterion("has_gold_bells", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SILVER_CHRISTMAS_BELLS.get()))
                .addCriterion("has_silver_bells", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GOLD_CHRISTMAS_BELLS.get()))
                .requirements(RequirementsStrategy.AND)
                .save(advancementConsumer, advancementId("ornament_bells"));

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
                .addCriterion("has_christmas_bells",
                        InventoryChangeTrigger.TriggerInstance.hasItems(
                                ItemPredicate.Builder.item().of(ChristmasItems.RED_CHRISTMAS_BELLS.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.BLUE_CHRISTMAS_BELLS.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.YELLOW_CHRISTMAS_BELLS.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.GREEN_CHRISTMAS_BELLS.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.GOLD_CHRISTMAS_BELLS.get()).withCount(MinMaxBounds.Ints.exactly(64)).build(),
                                ItemPredicate.Builder.item().of(ChristmasItems.SILVER_CHRISTMAS_BELLS.get()).withCount(MinMaxBounds.Ints.exactly(64)).build()
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
                .addCriterion("has_nutcracker_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.NUTCRACKER_ORNAMENT.get()))
                .rewards(AdvancementRewards.Builder.experience(150).build())
                .requirements(RequirementsStrategy.AND)
                .save(advancementConsumer, advancementId("ornament_fancy_hoarder"));

        Advancement loyalPlayer = createAdvancement(ornamentStart, ChristmasItems.ALPHABET_ORNAMENT_M.get(), translatable("ornament_loyal_player"))
                .addCriterion("has_m_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ALPHABET_ORNAMENT_M.get()))
                .addCriterion("has_i_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ALPHABET_ORNAMENT_I.get()))
                .addCriterion("has_n_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ALPHABET_ORNAMENT_N.get()))
                .addCriterion("has_e_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ALPHABET_ORNAMENT_E.get()))
                .addCriterion("has_c_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ALPHABET_ORNAMENT_C.get()))
                .addCriterion("has_r_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ALPHABET_ORNAMENT_R.get()))
                .addCriterion("has_a_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ALPHABET_ORNAMENT_A.get()))
                .addCriterion("has_f_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ALPHABET_ORNAMENT_F.get()))
                .addCriterion("has_t_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ALPHABET_ORNAMENT_T.get()))
                .requirements(RequirementsStrategy.AND)
                .save(advancementConsumer, advancementId("ornament_loyal_player"));
    }

    private void musicBranch(Consumer<Advancement> advancementConsumer) {
        Advancement musicStart = createAdvancement(christmasRoot, ChristmasItems.MUSIC_BOX.get(), translatable("music_musical_tendencies"))
                .addCriterion("has_music_box", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.MUSIC_BOX.get()))
                .save(advancementConsumer, advancementId("music_musical_tendencies"));

        Advancement settingTheMood = createAdvancement(musicStart, ChristmasItems.MUSIC_BOX.get(), translatable("music_setting_the_mood"))
                .addCriterion("played_music", ChristmasTriggers.PLAY_MUSIC_BOX.getInstance())
                .save(advancementConsumer, advancementId("music_setting_the_mood"));

        Advancement musicalMaestro = createAdvancement(musicStart, ChristmasItems.SHEET_MUSIC_CAROL_OF_THE_BELLS.get(), translatable("music_musical_maestro"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("has_sheet_music_0", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_ANGELS_ON_HIGH.get()))
                .addCriterion("has_sheet_music_1", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_CAROL_OF_THE_BELLS.get()))
                .addCriterion("has_sheet_music_2", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_DECK_THE_HALLS.get()))
                .addCriterion("has_sheet_music_3", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_FROSTY_THE_SNOWMAN.get()))
                .addCriterion("has_sheet_music_4", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_GOD_REST_GENTLEMEN.get()))
                .addCriterion("has_sheet_music_5", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_HERE_COMES_SANTA.get()))
                .addCriterion("has_sheet_music_6", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_JINGLE_BELL_ROCK.get()))
                .addCriterion("has_sheet_music_7", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_JINGLE_BELLS.get()))
                .addCriterion("has_sheet_music_8", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_JOY_TO_THE_WORLD.get()))
                .addCriterion("has_sheet_music_9", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_RUDOLPH.get()))
                .addCriterion("has_sheet_music_10", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_SILENT_NIGHT.get()))
                .addCriterion("has_sheet_music_11", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_SLEIGH_RIDE.get()))
                .addCriterion("has_sheet_music_12", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_THE_FIRST_NOEL.get()))
                .addCriterion("has_sheet_music_13", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_WE_THREE_KINGS.get()))
                .addCriterion("has_sheet_music_14", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_WE_WISH_YOU.get()))
                .addCriterion("has_sheet_music_15", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SHEET_MUSIC_WHITE_CHRISTMAS.get()))
                .rewards(AdvancementRewards.Builder.experience(100).build())
                .save(advancementConsumer, advancementId("music_musical_maestro"));
    }

    private void gingerbreadBranch(Consumer<Advancement> advancementConsumer) {
        Advancement gingerbreadStart = createAdvancement(christmasRoot, ChristmasItems.RAW_GINGERBREAD.get(), translatable("gingerbread_start"))
                .addCriterion("has_dough", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.RAW_GINGERBREAD.get()))
                .save(advancementConsumer, advancementId("gingerbread_start"));

        Advancement gingerbreadManTurnSoggy = createAdvancement(gingerbreadStart, ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get(), translatable("gingerbread_man_turn_soggy"))
                .addCriterion("turn_soggy", ChristmasTriggers.GINGERBREAD_MAN_TURN_SOGGY.getInstance())
                .save(advancementConsumer, advancementId("gingerbread_man_turn_soggy"));

        Advancement gingerbreadManTurnDry = createAdvancement(gingerbreadManTurnSoggy, ChristmasItems.GINGERBREAD_BLOCK.get(), translatable("gingerbread_man_turn_dry"))
                .addCriterion("turn_dry", ChristmasTriggers.GINGERBREAD_MAN_TURN_DRY.getInstance())
                .save(advancementConsumer, advancementId("gingerbread_man_turn_dry"));

        Advancement gingerbreadManDryChallenge = createAdvancement(gingerbreadManTurnDry, ChristmasItems.GINGERBREAD_HEADPIECE.get(), translatable("gingerbread_man_dry_challenge"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("complete_challenge", ChristmasTriggers.GINGERBREAD_MAN_DRY_CHALLENGE.getInstance())
                .rewards(AdvancementRewards.Builder.experience(200))
                .save(advancementConsumer, advancementId("gingerbread_man_dry_challenge"));

        Advancement gingerbreadOrnament = createAdvancement(gingerbreadManTurnDry, ChristmasItems.GINGERBREAD_MAN_ORNAMENT.get(), translatable("gingerbread_man_ornament"), FrameType.GOAL, true, false, false)
                .addCriterion("has_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GINGERBREAD_MAN_ORNAMENT.get()))
                .save(advancementConsumer, advancementId("gingerbread_man_ornament"));

        Advancement gingerbreadBlock = createAdvancement(gingerbreadStart, ChristmasItems.GINGERBREAD_BLOCK.get(), translatable("gingerbread_block"))
                .addCriterion("has_block", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GINGERBREAD_BLOCK.get()))
                .save(advancementConsumer, advancementId("gingerbread_block"));

        Advancement gingerbreadCookie = createAdvancement(gingerbreadStart, ChristmasItems.GINGERBREAD_COOKIE.get(), translatable("gingerbread_cookie_eat"))
                .addCriterion("eat_cookie", ConsumeItemTrigger.TriggerInstance.usedItem(ChristmasItems.GINGERBREAD_COOKIE.get()))
                .save(advancementConsumer, advancementId("gingerbread_cookie_eat"));
    }

    private void candyCaneBranch(Consumer<Advancement> advancementConsumer) {
        Advancement candyCaneStart = createAdvancement(christmasRoot, ChristmasItems.CANDY_CANE_BLOCK.get(), translatable("candy_cane_start"))
                .addCriterion("has_block", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.CANDY_CANE_BLOCK.get()))
                .save(advancementConsumer, advancementId("candy_cane_start"));

        Advancement candyCaneFestiveBlock = createAdvancement(candyCaneStart, ChristmasItems.FESTIVE_CANDY_CANE_BLOCK.get(), translatable("candy_cane_festive_block"))
                .addCriterion("has_block", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.FESTIVE_CANDY_CANE_BLOCK.get()))
                .save(advancementConsumer, advancementId("candy_cane_festive_block"));

        Advancement candyCrush = createAdvancement(candyCaneStart, ChristmasItems.ENCHANTED_CANDY_CANE.get(), translatable("candy_crush"))
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.ENCHANTED_CANDY_CANE.get()))
                .save(advancementConsumer, advancementId("candy_crush"));

        Advancement candyCaneOrnament = createAdvancement(candyCrush, ChristmasItems.CANDY_CANE_ORNAMENT.get(), translatable("candy_cane_ornament"), FrameType.GOAL, true, false, false)
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.CANDY_CANE_ORNAMENT.get()))
                .save(advancementConsumer, advancementId("candy_cane_ornament"));

        Advancement candyCaneEat = createAdvancement(candyCaneStart, ChristmasItems.CANDY_CANE.get(), translatable("candy_cane_eat"))
                .addCriterion("eat_candy_cane", ConsumeItemTrigger.TriggerInstance.usedItem(ChristmasItems.CANDY_CANE.get()))
                .addCriterion("eat_festive_candy_cane", ConsumeItemTrigger.TriggerInstance.usedItem(ChristmasItems.FESTIVE_CANDY_CANE.get()))
                .requirements(RequirementsStrategy.OR)
                .save(advancementConsumer, advancementId("candy_cane_eat"));

        santaElfSubBranch(advancementConsumer, candyCrush);
    }

    private void santaElfSubBranch(Consumer<Advancement> advancementConsumer, Advancement root) {
        Advancement santaElfBell = createAdvancement(root, ChristmasItems.SANTA_ELF_BELL.get(), translatable("santa_elf_bell"))
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SANTA_ELF_BELL.get()))
                .save(advancementConsumer, advancementId("santa_elf_bell"));

        Advancement santaElfSummon = createAdvancement(santaElfBell, ChristmasItems.SANTA_ELF_ORNAMENT.get(), translatable("santa_elf_summon"))
                .addCriterion("has_summoned", ChristmasTriggers.SANTA_ELF_SUMMON.getInstance())
                .save(advancementConsumer, advancementId("santa_elf_summon"));

        Advancement santaElfTrade = createAdvancement(santaElfSummon, Items.EMERALD, translatable("santa_elf_trade"))
                .addCriterion("has_trade", ChristmasTriggers.SANTA_ELF_TRADE.getInstance())
                .save(advancementConsumer, advancementId("santa_elf_trade"));

        Advancement santaElfRequest = createAdvancement(santaElfSummon, ChristmasItems.TOY_PARTS_REQUEST.get(), translatable("santa_elf_request"))
                .addCriterion("done_request", ChristmasTriggers.SANTA_ELF_COMPLETE_REQUEST.getInstance())
                .save(advancementConsumer, advancementId("santa_elf_request"));

        Advancement santaElfRequestQuick = createAdvancement(santaElfRequest, ChristmasItems.TOY_PARTS_REQUEST.get(), translatable("santa_elf_request_quick"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("done_request", ChristmasTriggers.SANTA_ELF_COMPLETE_REQUEST_QUICK.getInstance())
                .rewards(AdvancementRewards.Builder.experience(150))
                .save(advancementConsumer, advancementId("santa_elf_request_quick"));

        Advancement santaElfHelpChallenge = createAdvancement(santaElfRequest, ChristmasItems.SANTA_ELF_ORNAMENT.get(), translatable("santa_elf_help_challenge"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("complete_challenge", ChristmasTriggers.SANTA_ELF_HELP_CHALLENGE.getInstance())
                .rewards(AdvancementRewards.Builder.experience(200))
                .save(advancementConsumer, advancementId("santa_elf_help_challenge"));

        Advancement santaElfOrnament = createAdvancement(santaElfRequest, ChristmasItems.SANTA_ELF_ORNAMENT.get(), translatable("santa_elf_ornament"), FrameType.GOAL, true, false, false)
                .addCriterion("has_block", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SANTA_ELF_ORNAMENT.get()))
                .save(advancementConsumer, advancementId("santa_elf_ornament"));
    }

    private void hollyBranch(Consumer<Advancement> advancementConsumer) {
        Advancement hollyStart = createAdvancement(christmasRoot, ChristmasItems.HOLLY.get(), translatable("holly_start"))
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.HOLLY.get()))
                .save(advancementConsumer, advancementId("holly_start"));

        Advancement christmasWreath = createAdvancement(hollyStart, ChristmasItems.CHRISTMAS_WREATH.get(), translatable("holly_wreath"))
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.CHRISTMAS_WREATH.get()))
                .save(advancementConsumer, advancementId("holly_wreath"));

        Advancement christmasGlutton = createAdvancement(hollyStart, ChristmasItems.LOG_CAKE.get(), translatable("food_glutton"))
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ChristmasItems.CHRISTMAS_HAM.get(),
                        ChristmasItems.CHRISTMAS_PUDDING.get(),
                        ChristmasItems.LOG_CAKE.get(),
                        ChristmasItems.MILK_AND_COOKIES.get(),
                        ChristmasItems.EGGNOG.get()
                ))
                .save(advancementConsumer, advancementId("food_glutton"));
    }

    private void stockingBranch(Consumer<Advancement> advancementConsumer) {
        Advancement stockingStart = createAdvancement(christmasRoot, ChristmasItems.RED_STOCKING.get(), translatable("stocking_start"))
                .addCriterion("has_red_stocking", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.RED_STOCKING.get()))
                .addCriterion("has_blue_stocking", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.BLUE_STOCKING.get()))
                .addCriterion("has_yellow_stocking", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.YELLOW_STOCKING.get()))
                .addCriterion("has_green_stocking", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GREEN_STOCKING.get()))
                .addCriterion("has_gold_stocking", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SILVER_STOCKING.get()))
                .addCriterion("has_silver_stocking", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.GOLD_STOCKING.get()))
                .requirements(RequirementsStrategy.OR)
                .save(advancementConsumer, advancementId("stocking_start"));

        Advancement stockingFill = createAdvancement(stockingStart, ChristmasItems.GOLD_STOCKING.get(), translatable("stocking_fill"))
                .addCriterion("stocking_filled", ChristmasTriggers.STOCKING_FILL.getInstance())
                .save(advancementConsumer, advancementId("stocking_fill"));

        Advancement stockingUpgrade = createAdvancement(stockingStart, ChristmasItems.ENCHANTED_THREAD.get(), translatable("stocking_upgrade"))
                .addCriterion("stocking_upgrade", ChristmasTriggers.STOCKING_UPGRADE.getInstance())
                .save(advancementConsumer, advancementId("stocking_upgrade"));
    }

    private void starBranch(Consumer<Advancement> advancementConsumer) {
        Advancement starStart = createAdvancement(christmasRoot, ChristmasItems.CHRISTMAS_STAR.get(), translatable("star_start"))
                .addCriterion("has_star", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.CHRISTMAS_STAR.get()))
                .save(advancementConsumer, advancementId("star_start"));

        Advancement starPutOrnament = createAdvancement(starStart, ChristmasItems.RED_BAUBLE.get(), translatable("star_put_ornament"))
                .addCriterion("put_ornament", ChristmasTriggers.STAR_PUT_ORNAMENT.getInstance())
                .save(advancementConsumer, advancementId("star_put_ornament"));

        Advancement starMaxTier = createAdvancement(starPutOrnament, ChristmasItems.CHRISTMAS_STAR.get(), translatable("star_max_tier"))
                .addCriterion("max_tier", ChristmasTriggers.STAR_MAXED_TIER.getInstance())
                .save(advancementConsumer, advancementId("star_max_tier"));

        Advancement starHatMagic = createAdvancement(starMaxTier, ChristmasItems.ENCHANTED_SANTA_HAT.get(), translatable("star_hat_magic"), FrameType.GOAL, true, false, false)
                .addCriterion("put_santa_hat", ChristmasTriggers.STAR_REACH_BONUS.getInstance())
                .rewards(AdvancementRewards.Builder.experience(100))
                .save(advancementConsumer, advancementId("star_hat_magic"));

        santaSubBranch(advancementConsumer, starMaxTier);
    }

    private void santaSubBranch(Consumer<Advancement> advancementConsumer, Advancement parent) {
        Advancement santaStart = createAdvancement(parent, ChristmasItems.SANTA_HAT.get(), translatable("santa_summon"))
                .addCriterion("summon_santa", ChristmasTriggers.STAR_SUMMON_SANTA.getInstance())
                .save(advancementConsumer, advancementId("santa_summon"));

        Advancement santaDropParty = createAdvancement(santaStart, ChristmasItems.GOLD_CHRISTMAS_GIFT.get(), translatable("santa_drop_party"))
                .addCriterion("drop_party", ChristmasTriggers.SANTA_DROP_PARTY_COMPLETE.getInstance())
                .save(advancementConsumer, advancementId("santa_drop_party"));

        Advancement santaHappyChallenge = createAdvancement(santaDropParty, ChristmasItems.SANTA_HAT.get(), translatable("santa_happy_challenge"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("complete_challenge", ChristmasTriggers.SANTA_HAPPY_CHALLENGE.getInstance())
                .rewards(AdvancementRewards.Builder.experience(200))
                .save(advancementConsumer, advancementId("santa_happy_challenge"));

        Advancement santaAngryDefeat = createAdvancement(santaStart, ChristmasItems.ENCHANTED_SANTA_HAT.get(), translatable("santa_angry_defeat"))
                .addCriterion("defeat_santa", ChristmasTriggers.SANTA_ANGRY_DIE.getInstance())
                .save(advancementConsumer, advancementId("santa_angry_defeat"));

        Advancement santaAngryChallenge = createAdvancement(santaAngryDefeat, ChristmasItems.SANTA_HAT.get(), translatable("santa_angry_challenge"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("complete_challenge", ChristmasTriggers.SANTA_ANGRY_CHALLENGE.getInstance())
                .rewards(AdvancementRewards.Builder.experience(200))
                .save(advancementConsumer, advancementId("santa_angry_challenge"));

        Advancement noTouchy = createAdvancement(santaStart, ChristmasItems.ENCHANTED_SANTA_HAT.get(), translatable("santa_no_touchy"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("defeat_santa_restricted", ChristmasTriggers.SANTA_NO_TOUCHY.getInstance())
                .rewards(AdvancementRewards.Builder.experience(200).build())
                .save(advancementConsumer, advancementId("santa_no_touchy"));
    }

    private void snowGlobeBranch(Consumer<Advancement> advancementConsumer) {
        Advancement snowGlobeStart = createAdvancement(christmasRoot, ChristmasItems.SNOW_GLOBE.get(), translatable("snow_globe_start"))
                .addCriterion("get_snow_globe", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SNOW_GLOBE.get()))
                .save(advancementConsumer, advancementId("snow_globe_start"));

        Advancement snowGlobeUse = createAdvancement(snowGlobeStart, ChristmasItems.SNOW_GLOBE.get(), translatable("snow_globe_use"))
                .addCriterion("use_snow_globe", ChristmasTriggers.SNOW_GLOBE_USE.getInstance())
                .save(advancementConsumer, advancementId("snow_globe_use"));

        Advancement snowGlobeUseChallenge = createAdvancement(snowGlobeStart, ChristmasItems.SNOW_GLOBE.get(), translatable("snow_globe_use_challenge"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("use_snow_globe", ChristmasTriggers.SNOW_GLOBE_USE_CHALLENGE.getInstance())
                .rewards(AdvancementRewards.Builder.experience(200).build())
                .save(advancementConsumer, advancementId("snow_globe_use_challenge"));
    }

    private void nutcrackerBranch(Consumer<Advancement> advancementConsumer) {
        Advancement nutcrackerStart = createAdvancement(christmasRoot, ChristmasItems.NUTCRACKER_ORNAMENT.get(), translatable("nutcracker_encounter"))
                .addCriterion("encounter_nutcracker", ChristmasTriggers.NUTCRACKER_ENCOUNTER.getInstance())
                .save(advancementConsumer, advancementId("nutcracker_start"));

        Advancement nutcrackerTame = createAdvancement(nutcrackerStart, ChristmasItems.WALNUT.get(), translatable("nutcracker_tame"))
                .addCriterion("tame_nutcracker", ChristmasTriggers.NUTCRACKER_TAME.getInstance())
                .save(advancementConsumer, advancementId("nutcracker_tame"));

        Advancement nutcrackerTameChallenge = createAdvancement(nutcrackerTame, ChristmasItems.WALNUT.get(), translatable("nutcracker_tame_challenge"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("tame_nutcracker_challenge", ChristmasTriggers.NUTCRACKER_TAME_CHALLENGE.getInstance())
                .rewards(AdvancementRewards.Builder.experience(200).build())
                .save(advancementConsumer, advancementId("nutcracker_tame_challenge"));

        Advancement nutcrackerKill = createAdvancement(nutcrackerTame, Items.ZOMBIE_HEAD, translatable("nutcracker_kill_mob"))
                .addCriterion("nutcracker_kill_mobs", ChristmasTriggers.NUTCRACKER_KILLS_MOB.getInstance())
                .save(advancementConsumer, advancementId("nutcracker_kill_mob"));

        Advancement nutcrackerOrnament = createAdvancement(nutcrackerKill, ChristmasItems.NUTCRACKER_ORNAMENT.get(), translatable("nutcracker_ornament_get"), FrameType.GOAL, true, false, false)
                .addCriterion("obtain_nutcracker_ornament", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.NUTCRACKER_ORNAMENT.get()))
                .save(advancementConsumer, advancementId("nutcracker_ornament_get"));

        Advancement nutcrackerKillsChallenge = createAdvancement(nutcrackerKill, Items.ZOMBIE_HEAD, translatable("nutcracker_kill_mob_challenge"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("nutcracker_kill_mobs", ChristmasTriggers.NUTCRACKER_KILL_CHALLENGE.getInstance())
                .rewards(AdvancementRewards.Builder.experience(100).build())
                .save(advancementConsumer, advancementId("nutcracker_kill_mob_challenge"));

        Advancement patrolDuty = createAdvancement(nutcrackerTame, ChristmasItems.PATROL_ORDERS.get(), translatable("nutcracker_patrol_duty"))
                .addCriterion("get_patrol_orders", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.PATROL_ORDERS.get()))
                .save(advancementConsumer, advancementId("nutcracker_patrol_duty"));

        Advancement daintyDelegation = createAdvancement(patrolDuty, ChristmasItems.PATROL_ORDERS.get(), translatable("nutcracker_dainty_delegation"))
                .addCriterion("complete_patrol_orders", ChristmasTriggers.PATROL_ORDERS_COMPLETE.getInstance())
                .save(advancementConsumer, advancementId("nutcracker_dainty_delegation"));

        Advancement rogerThat = createAdvancement(daintyDelegation, ChristmasItems.PATROL_ORDERS.get(), translatable("nutcracker_roger_that"))
                .addCriterion("receive_complete_orders", ChristmasTriggers.NUTCRACKER_RECEIVE_COMPLETE_ORDERS.getInstance())
                .save(advancementConsumer, advancementId("nutcracker_roger_that"));

        Advancement walnutObtain = createAdvancement(nutcrackerStart, ChristmasItems.WALNUT.get(), translatable("nutcracker_walnut_obtain"))
                .addCriterion("get_walnut", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.WALNUT.get()))
                .save(advancementConsumer, advancementId("nutcracker_walnut_obtain"));

        Advancement specialWalnut = createAdvancement(walnutObtain, ChristmasItems.SUGARED_WALNUT.get(), translatable("nutcracker_special_walnut"))
                .addCriterion("obtain_sugared_walnut", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SUGARED_WALNUT.get()))
                .addCriterion("obtain_explosive_walnut", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.EXPLOSIVE_WALNUT.get()))
                .addCriterion("obtain_halved_walnut", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.HALVED_WALNUT.get()))
                .addCriterion("obtain_metallic_walnut", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.METALLIC_WALNUT.get()))
                .requirements(RequirementsStrategy.OR)
                .save(advancementConsumer, advancementId("nutcracker_special_walnut"));

        Advancement specialForces = createAdvancement(specialWalnut, ChristmasItems.METALLIC_WALNUT.get(), translatable("nutcracker_special_forces"))
                .addCriterion("give_nutcracker_special_walnut", ChristmasTriggers.NUTCRACKER_RECEIVE_SPECIAL_WALNUT.getInstance())
                .save(advancementConsumer, advancementId("nutcracker_special_forces"));

        Advancement explosiveInventory = createAdvancement(specialWalnut, ChristmasItems.EXPLOSIVE_WALNUT.get(), translatable("nutcracker_explosive_inventory"), FrameType.CHALLENGE, true, true,false)
                .addCriterion("nutcracker_filled_explosive", ChristmasTriggers.NUTCRACKER_EXPLOSIVE_INVENTORY.getInstance())
                .rewards(AdvancementRewards.Builder.experience(100))
                .save(advancementConsumer, advancementId("nutcracker_explosive_inventory"));

        Advancement coolerStick = createAdvancement(nutcrackerStart, ChristmasItems.SWAGGER_STICK.get(), translatable("nutcracker_cooler_stick"))
                .addCriterion("obtain_swagger_stick", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.SWAGGER_STICK.get()))
                .save(advancementConsumer, advancementId("nutcracker_cooler_stick"));

        Advancement avengersAssemble = createAdvancement(coolerStick, ChristmasItems.SWAGGER_STICK.get(), translatable("nutcracker_avengers_assemble"))
                .addCriterion("assemble_nutcrackers", ChristmasTriggers.SWAGGER_STICK_HELD.getInstance())
                .save(advancementConsumer, advancementId("nutcracker_avengers_assemble"));

        Advancement builtDifferent = createAdvancement(nutcrackerStart, Items.LEATHER_CHESTPLATE, translatable("nutcracker_built_different"))
                .addCriterion("give_nutcracker_armor", ChristmasTriggers.NUTCRACKER_RECEIVE_ARMOR.getInstance())
                .save(advancementConsumer, advancementId("nutcracker_built_different"));

        Advancement hardNutToCrack = createAdvancement(builtDifferent, Items.NETHERITE_CHESTPLATE, translatable("nutcracker_hard_nut"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("nutcracker_armor_rating_high", ChristmasTriggers.NUTCRACKER_HIGH_ARMOR_RATING.getInstance())
                .rewards(AdvancementRewards.Builder.experience(100))
                .save(advancementConsumer, advancementId("nutcracker_hard_nut"));
    }

    private void outfitBranch(Consumer<Advancement> advancementConsumer) {
        Advancement outfitStart = createAdvancement(christmasRoot, ChristmasItems.THREAD.get(), translatable("outfit_start"))
                .addCriterion("obtain_thread", InventoryChangeTrigger.TriggerInstance.hasItems(ChristmasItems.THREAD.get()))
                .save(advancementConsumer, advancementId("outfit_start"));

        Advancement dressUp = createAdvancement(outfitStart, ChristmasItems.THREAD.get(), translatable("outfit_fashion_show"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("obtain_santa_outfit", createObtainOutfitCriterion(ChristmasOutfits.SANTA_OUTFIT))
                .addCriterion("obtain_santa_elf_outfit", createObtainOutfitCriterion(ChristmasOutfits.SANTA_ELF_OUTFIT))
                .addCriterion("obtain_snowman_outfit", createObtainOutfitCriterion(ChristmasOutfits.SNOWMAN_OUTFIT))
                .addCriterion("obtain_candy_cane_outfit", createObtainOutfitCriterion(ChristmasOutfits.CANDY_CANE_OUTFIT))
                .addCriterion("obtain_reindeer_outfit", createObtainOutfitCriterion(ChristmasOutfits.REINDEER_OUTFIT))
                .addCriterion("obtain_nutcracker_outfit", createObtainOutfitCriterion(ChristmasOutfits.NUTCRACKER_OUTFIT))
                .addCriterion("obtain_gingerbread_outfit", createObtainOutfitCriterion(ChristmasOutfits.GINGERBREAD_OUTFIT))
                .rewards(AdvancementRewards.Builder.experience(150))
                .save(advancementConsumer, advancementId("outfit_fashion_show"));
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

    private InventoryChangeTrigger.TriggerInstance createObtainOutfitCriterion(Outfit outfit) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(outfit.getOutfitItems().toArray(new Item[0]));
    }
}
