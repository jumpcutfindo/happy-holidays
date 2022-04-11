package com.jumpcutfindo.happyholidays.common.block.cny.offering;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.compress.utils.Lists;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class OfferingCombination {
    private String name;

    private final List<ItemPredicate> tagPredicates = Lists.newArrayList();
    private final List<ItemPredicate> itemPredicates = Lists.newArrayList();
    private OfferingReward reward;

    private OfferingCombination() {
    }

    protected void addTagPredicate(ItemPredicate predicate) {
        tagPredicates.add(predicate);
    }

    protected void addItemPredicate(ItemPredicate predicate) {
        this.itemPredicates.add(predicate);
    }

    protected void setReward(OfferingReward reward) {
        this.reward = reward;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public OfferingReward getReward() {
        return this.reward;
    }

    public int size() {
        return itemPredicates.size() + tagPredicates.size();
    }

    public boolean matches(Offering offering) {
        // Simple check to ensure sizes match
        if (this.size() != offering.getSize()) return false;

        // Matching is done from most specific -> least specific
        Queue<ItemPredicate> queue = new LinkedList<>();
        queue.addAll(itemPredicates);
        queue.addAll(tagPredicates);

        for (ItemStack itemStack : offering.getOfferings()) {
            if (itemStack.isEmpty()) continue;
            int queueSize = queue.size();
            boolean matchesAny = false;

            // We always check the item against the same predicates, adding it back if it doesn't match
            for (int i = 0; i < queueSize; i++) {
                ItemPredicate predicate = queue.poll();
                if (predicate.matches(itemStack)) {
                    matchesAny = true;
                    break;
                }
                else queue.add(predicate);
            }

            // Since the itemStack didn't match any of the remaining predicates, the offering is invalid
            if (!matchesAny) return false;
        }

        return true;
    }

    public static Builder builder() {
        return new OfferingCombination.Builder();
    }

    @Override
    public String toString() {
        return "OfferingCombination{" +
                "name='" + name + '\'' +
                '}';
    }

    public static class Builder {
        private OfferingCombination combination;

        private Builder() {
            this.combination = new OfferingCombination();
        }

        public Builder name(String name) {
            combination.setName(name);
            return this;
        }

        public Builder requireItem(ItemLike item) {
            assert combination.size() < 3;
            this.combination.addItemPredicate(ItemPredicate.Builder.item().of(item).build());
            return this;
        }

        public Builder requireItems(TagKey<Item> itemTag) {
            assert combination.size() < 3;
            this.combination.addTagPredicate(ItemPredicate.Builder.item().of(itemTag).build());
            return this;
        }

        public Builder setReward(OfferingReward reward) {
            this.combination.setReward(reward);
            return this;
        }

        public OfferingCombination build() {
            assert combination.getReward() != null;
            assert combination.getName() != null;

            return combination;
        }
    }
}
