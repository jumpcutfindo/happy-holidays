package com.jumpcutfindo.happyholidays.client.entity.model;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.GingerbreadPersonEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.SoggyGingerbreadManEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GingerbreadPersonModel<T extends GingerbreadPersonEntity> extends AnimatedGeoModel<GingerbreadPersonEntity> {
	@Override
	public ResourceLocation getModelLocation(GingerbreadPersonEntity object) {
		return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/gingerbread_man.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(GingerbreadPersonEntity object) {
		if (object instanceof SoggyGingerbreadManEntity) {
			return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entities/soggy_gingerbread_man.png");
		} else {
			return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entities/gingerbread_man.png");
		}
	}

	@Override
	public ResourceLocation getAnimationFileLocation(GingerbreadPersonEntity animatable) {
		return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/gingerbread_man.animation.json");
	}
}