package com.bayobayobayo.happyholidays.common.entity.christmas;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.client.entity.GingerbreadPersonEntityRenderer;
import com.bayobayobayo.happyholidays.common.RegistryHandler;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class GingerbreadEntities implements ChristmasEntities {

    private RegistryObject<EntityType<GingerbreadPersonEntity>> gingerbreadManObject;

    public GingerbreadEntities() {
    }

    public void createAttributes(EntityAttributeCreationEvent event) {
        event.put(gingerbreadManObject.get(),
                MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .build()
        );
    }

    @Override
    public void registerEntities() {
        gingerbreadManObject = RegistryHandler.ENTITY_TYPES.register(GingerbreadPersonEntity.ENTITY_ID,
                () -> EntityType.Builder.of(GingerbreadPersonEntity::new, EntityClassification.AMBIENT)
                        .sized(0.8f, 2.0f)
                        .build(GingerbreadPersonEntity.ENTITY_ID)
        );
    }

    @Override
    public void configureEntities() {
        RenderingRegistry.registerEntityRenderingHandler(
                gingerbreadManObject.get(),
                GingerbreadPersonEntityRenderer::new
        );
    }
}
