package com.jumpcutfindo.happyholidays.common.advancements;

import com.google.gson.JsonObject;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.resources.ResourceLocation;

public class CustomTrigger extends SimpleCriterionTrigger<CustomTrigger.Instance> {
    private final ResourceLocation triggerId;

    public CustomTrigger(String location) {
        this(new ResourceLocation(HappyHolidaysMod.MOD_ID, location));
    }

    public CustomTrigger(ResourceLocation resourceLocation) {
        super();
        triggerId = resourceLocation;
    }

    public void trigger(ServerPlayer playerEntity) {
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
    protected Instance createInstance(JsonObject jsonIn, EntityPredicate.Composite entityPredicateIn,
                                      DeserializationContext conditionsParserIn) {
        return new CustomTrigger.Instance(this.getId());
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        public Instance(ResourceLocation resourceLocation) {
            super(resourceLocation, EntityPredicate.Composite.ANY);
        }

        public boolean test() {
            return true;
        }
    }
}
