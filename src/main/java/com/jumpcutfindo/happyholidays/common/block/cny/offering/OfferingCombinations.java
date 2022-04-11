package com.jumpcutfindo.happyholidays.common.block.cny.offering;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.compress.utils.Lists;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.tags.cny.CNYTags;
import com.jumpcutfindo.happyholidays.common.utils.message.GameplayMessage;
import com.jumpcutfindo.happyholidays.common.utils.message.MessageType;
import com.jumpcutfindo.happyholidays.common.utils.message.Messenger;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class OfferingCombinations {
    public static final List<OfferingCombination> OFFERING_COMBINATIONS = Lists.newArrayList();

    public static final OfferingCombination ONE_WOODEN_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("one_wooden_sword_for_strength").requireItem(Items.WOODEN_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 1, 5)).build());
    public static final OfferingCombination TWO_WOODEN_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("two_wooden_sword_for_strength").requireItem(Items.WOODEN_SWORD).requireItem(Items.WOODEN_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 1, 10)).build());
    public static final OfferingCombination THREE_WOODEN_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("three_wooden_sword_for_strength").requireItem(Items.WOODEN_SWORD).requireItem(Items.WOODEN_SWORD).requireItem(Items.WOODEN_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 1, 20)).build());
    public static final OfferingCombination ONE_STONE_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("one_stone_sword_for_strength").requireItem(Items.STONE_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 1, 10)).build());
    public static final OfferingCombination TWO_STONE_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("two_stone_sword_for_strength").requireItem(Items.STONE_SWORD).requireItem(Items.STONE_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 1, 20)).build());
    public static final OfferingCombination THREE_STONE_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("three_stone_sword_for_strength").requireItem(Items.STONE_SWORD).requireItem(Items.STONE_SWORD).requireItem(Items.STONE_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 1, 40)).build());
    public static final OfferingCombination ONE_IRON_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("one_iron_sword_for_strength").requireItem(Items.IRON_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 2, 30)).build());
    public static final OfferingCombination TWO_IRON_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("two_iron_sword_for_strength").requireItem(Items.IRON_SWORD).requireItem(Items.IRON_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 2, 60)).build());
    public static final OfferingCombination THREE_IRON_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("three_iron_sword_for_strength").requireItem(Items.IRON_SWORD).requireItem(Items.IRON_SWORD).requireItem(Items.IRON_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 2, 120)).build());
    public static final OfferingCombination ONE_GOLD_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("one_gold_sword_for_strength").requireItem(Items.GOLDEN_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 5, 5)).build());
    public static final OfferingCombination TWO_GOLD_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("two_gold_sword_for_strength").requireItem(Items.GOLDEN_SWORD).requireItem(Items.GOLDEN_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 5, 10)).build());
    public static final OfferingCombination THREE_GOLD_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("three_gold_sword_for_strength").requireItem(Items.GOLDEN_SWORD).requireItem(Items.GOLDEN_SWORD).requireItem(Items.GOLDEN_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 5, 20)).build());
    public static final OfferingCombination ONE_DIAMOND_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("one_diamond_sword_for_strength").requireItem(Items.DIAMOND_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 4, 60)).build());
    public static final OfferingCombination TWO_DIAMOND_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("two_diamond_sword_for_strength").requireItem(Items.DIAMOND_SWORD).requireItem(Items.DIAMOND_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 4, 120)).build());
    public static final OfferingCombination THREE_DIAMOND_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("three_diamond_sword_for_strength").requireItem(Items.DIAMOND_SWORD).requireItem(Items.DIAMOND_SWORD).requireItem(Items.DIAMOND_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 4, 240)).build());
    public static final OfferingCombination ONE_NETHERITE_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("one_netherite_sword_for_strength").requireItem(Items.NETHERITE_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 5, 240)).build());
    public static final OfferingCombination TWO_NETHERITE_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("two_netherite_sword_for_strength").requireItem(Items.NETHERITE_SWORD).requireItem(Items.NETHERITE_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 5, 480)).build());
    public static final OfferingCombination THREE_NETHERITE_SWORD_FOR_STRENGTH = register(OfferingCombination.builder().name("three_netherite_sword_for_strength").requireItem(Items.NETHERITE_SWORD).requireItem(Items.NETHERITE_SWORD).requireItem(Items.NETHERITE_SWORD).setReward(EffectReward.withEffect(MobEffects.DAMAGE_BOOST, 5, 960)).build());

    public static final OfferingCombination ONE_LEATHER_ARMOR_FOR_RESISTANCE = register(OfferingCombination.builder().name("one_leather_armor_for_resist").requireItems(CNYTags.Items.LEATHER_ARMOR).setReward(EffectReward.withEffect(MobEffects.DAMAGE_RESISTANCE, 1, 20)).build());
    public static final OfferingCombination TWO_LEATHER_ARMOR_FOR_RESISTANCE = register(OfferingCombination.builder().name("two_leather_armor_for_resist").requireItems(CNYTags.Items.LEATHER_ARMOR).requireItems(CNYTags.Items.LEATHER_ARMOR).setReward(EffectReward.withEffect(MobEffects.DAMAGE_RESISTANCE, 1, 40)).build());
    public static final OfferingCombination THREE_LEATHER_ARMOR_FOR_RESISTANCE = register(OfferingCombination.builder().name("three_leather_armor_for_resist").requireItems(CNYTags.Items.LEATHER_ARMOR).requireItems(CNYTags.Items.LEATHER_ARMOR).requireItems(CNYTags.Items.LEATHER_ARMOR).setReward(EffectReward.withEffect(MobEffects.DAMAGE_RESISTANCE, 1, 80)).build());

    public static final OfferingCombination ONE_IRON_INGOT_FOR_RESISTANCE = register(OfferingCombination.builder().name("one_iron_ingot_for_resistance").requireItem(Items.IRON_INGOT).setReward(EffectReward.withEffect(MobEffects.DAMAGE_RESISTANCE, 1, 60)).build());
    public static final OfferingCombination TWO_IRON_INGOT_FOR_RESISTANCE = register(OfferingCombination.builder().name("two_iron_ingot_for_resistance").requireItem(Items.IRON_INGOT).requireItem(Items.IRON_INGOT).setReward(EffectReward.withEffect(MobEffects.DAMAGE_RESISTANCE, 1, 120)).build());
    public static final OfferingCombination THREE_IRON_INGOT_FOR_RESISTANCE = register(OfferingCombination.builder().name("three_iron_ingot_for_resistance").requireItem(Items.IRON_INGOT).requireItem(Items.IRON_INGOT).requireItem(Items.IRON_INGOT).setReward(EffectReward.withEffect(MobEffects.DAMAGE_RESISTANCE, 1, 240)).build());
    public static final OfferingCombination ONE_IRON_BLOCK_FOR_RESISTANCE = register(OfferingCombination.builder().name("one_iron_block_for_resistance").requireItem(Items.IRON_BLOCK).setReward(EffectReward.withEffect(MobEffects.DAMAGE_RESISTANCE, 3, 60)).build());
    public static final OfferingCombination TWO_IRON_BLOCK_FOR_RESISTANCE = register(OfferingCombination.builder().name("two_iron_block_for_resistance").requireItem(Items.IRON_BLOCK).requireItem(Items.IRON_BLOCK).setReward(EffectReward.withEffect(MobEffects.DAMAGE_RESISTANCE, 3, 120)).build());
    public static final OfferingCombination THREE_IRON_BLOCK_FOR_RESISTANCE = register(OfferingCombination.builder().name("three_iron_block_for_resistance").requireItem(Items.IRON_BLOCK).requireItem(Items.IRON_BLOCK).requireItem(Items.IRON_BLOCK).setReward(EffectReward.withEffect(MobEffects.DAMAGE_RESISTANCE, 3, 240)).build());

    public static final OfferingCombination ONE_APPLES_FOR_XP = register(OfferingCombination.builder().name("one_apples_for_xp").requireItem(Items.APPLE).setReward(ExperienceReward.withAmount(1)).build());
    public static final OfferingCombination TWO_APPLES_FOR_XP = register(OfferingCombination.builder().name("two_apples_for_xp").requireItem(Items.APPLE).requireItem(Items.APPLE).setReward(ExperienceReward.withAmount(2)).build());
    public static final OfferingCombination THREE_APPLES_FOR_XP = register(OfferingCombination.builder().name("three_apples_for_xp").requireItem(Items.APPLE).requireItem(Items.APPLE).requireItem(Items.APPLE).setReward(ExperienceReward.withAmount(3)).build());
    public static final OfferingCombination ONE_CAKE_FOR_XP = register(OfferingCombination.builder().name("one_cake_for_xp").requireItem(Items.CAKE).setReward(ExperienceReward.withAmount(4)).build());
    public static final OfferingCombination TWO_CAKE_FOR_XP = register(OfferingCombination.builder().name("two_cake_for_xp").requireItem(Items.CAKE).requireItem(Items.CAKE).setReward(ExperienceReward.withAmount(8)).build());
    public static final OfferingCombination THREE_CAKE_FOR_XP = register(OfferingCombination.builder().name("three_cake_for_xp").requireItem(Items.CAKE).requireItem(Items.CAKE).requireItem(Items.CAKE).setReward(ExperienceReward.withAmount(12)).build());

    public static final OfferingCombination PHANTOM_MEMBRANE_FOR_PHANTOM = register(OfferingCombination.builder().name("phantom_membrane_for_phantom").requireItem(Items.PHANTOM_MEMBRANE).requireItem(Items.PHANTOM_MEMBRANE).requireItem(Items.PHANTOM_MEMBRANE).setReward(MobSpawnReward.mob(EntityType.PHANTOM, 1)).build());

    public static void resolveOffering(Level level, Player player, BlockPos blockPos, Offering offering) {
        List<OfferingCombination> validCombinations = findMatches(offering);

        switch (validCombinations.size()) {
        case 0 -> {
            GameplayMessage message = new GameplayMessage(MessageType.ERROR, "chat.happyholidays.offering_table.no_combination");
            Messenger.sendChatMessage(message, player);
        }
        case 1 -> {
            OfferingCombination validCombination = validCombinations.get(0);
            validCombination.getReward().execute(level, player, blockPos);

            GameplayMessage message = new GameplayMessage(MessageType.SUCCESS, "chat.happyholidays.offering_table.valid_combination");
            Messenger.sendChatMessage(message, player);
        }
        default -> {
            GameplayMessage message = new GameplayMessage(MessageType.ERROR, "chat.happyholidays.offering_table.no_combination");
            Messenger.sendChatMessage(message, player);

            HappyHolidaysMod.LOGGER.debug("Found multiple matching offering combinations for this item combination!");
        }
        }
    }

    public static List<OfferingCombination> findMatches(Offering offering) {
        return OFFERING_COMBINATIONS.stream()
                .filter(combination -> combination.matches(offering))
                .collect(Collectors.toList());
    }

    public static List<OfferingCombination> getAllCombinations() {
        return OFFERING_COMBINATIONS;
    }

    public static OfferingCombination register(OfferingCombination offeringCombination) {
        OFFERING_COMBINATIONS.add(offeringCombination);
        return offeringCombination;
    }
}
