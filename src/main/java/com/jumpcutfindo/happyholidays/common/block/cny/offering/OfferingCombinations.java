package com.jumpcutfindo.happyholidays.common.block.cny.offering;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.Maps;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.utils.message.GameplayMessage;
import com.jumpcutfindo.happyholidays.common.utils.message.MessageType;
import com.jumpcutfindo.happyholidays.common.utils.message.Messenger;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class OfferingCombinations {
    public static Map<ResourceLocation, OfferingCombination> offeringCombinations = Util.make(Maps.newHashMap(), map -> {
        // TODO: We update this using datapacks instead of this manual addition
        map.put(resourceOf("three_iron_for_experience"), OfferingCombination.Builder.builder().requireItem(Items.IRON_INGOT).requireItem(Items.IRON_INGOT).requireItem(Items.IRON_INGOT).setReward(ExperienceReward.withAmount(50)).build());
        map.put(resourceOf("two_iron_for_experience"), OfferingCombination.Builder.builder().requireItem(Items.IRON_INGOT).requireItem(Items.IRON_INGOT).setReward(ExperienceReward.withAmount(25)).build());
        map.put(resourceOf("one_iron_for_experience"), OfferingCombination.Builder.builder().requireItem(Items.IRON_INGOT).setReward(ExperienceReward.withAmount(10)).build());
        map.put(resourceOf("coals_for_torches"), OfferingCombination.Builder.builder().requireItems(ItemTags.COALS).requireItem(Items.COAL).requireItem(Items.CHARCOAL).setReward(ItemReward.withRewards(Items.TORCH, 4, 64)).build());
    });

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
        return offeringCombinations.values().stream()
                .filter(combination -> combination.matches(offering))
                .collect(Collectors.toList());
    }

    public static List<OfferingCombination> getAllCombinations() {
        List<OfferingCombination> combinations = Lists.newArrayList();
        combinations.addAll(offeringCombinations.values());
        return combinations;
    }

    public static ResourceLocation resourceOf(String resourceId) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, resourceId);
    }
}
