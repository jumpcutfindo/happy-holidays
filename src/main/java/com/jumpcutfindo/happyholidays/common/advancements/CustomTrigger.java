package com.jumpcutfindo.happyholidays.common.advancements;

import com.google.gson.JsonObject;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

public class CustomTrigger extends AbstractCriterionTrigger<CustomTrigger.Instance> {
    private final ResourceLocation triggerId;

    public CustomTrigger(String location) {
        this(new ResourceLocation(HappyHolidaysMod.MOD_ID, location));
    }

    public CustomTrigger(ResourceLocation resourceLocation) {
        super();
        triggerId = resourceLocation;
    }

    public void trigger(ServerPlayerEntity playerEntity) {
        this.trigger(playerEntity, Instance::test);
    }

    @Override
    public ResourceLocation getId() {
        return triggerId;
    }

    public Instance getInstance() {
        return new CustomTrigger.Instance(this.getId());
    }

    @Override
    protected Instance createInstance(JsonObject jsonIn, EntityPredicate.AndPredicate entityPredicateIn,
                                      ConditionArrayParser conditionsParserIn) {
        return new CustomTrigger.Instance(this.getId());
    }

    public static class Instance extends CriterionInstance {
        public Instance(ResourceLocation resourceLocation) {
            super(resourceLocation, EntityPredicate.AndPredicate.ANY);
        }

        public boolean test() {
            return true;
        }
    }
}
