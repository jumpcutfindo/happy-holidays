package com.bayobayobayo.happyholidays.client.entity.model;

import com.bayobayobayo.happyholidays.common.entity.christmas.GingerbreadEntities;
import com.bayobayobayo.happyholidays.common.entity.christmas.GingerbreadPersonEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GingerbreadPersonModel<T extends GingerbreadPersonEntity> extends EntityModel<T> {
	private final ModelRenderer man;
	private final ModelRenderer legs;
	private final ModelRenderer arms;
	private final ModelRenderer head;
	private final ModelRenderer face;
	private final ModelRenderer body;

	public GingerbreadPersonModel() {
		this.texWidth = 48;
		this.texHeight = 48;

		man = new ModelRenderer(this);
		man.setPos(0.0F, 24.0F, 0.0F);

		legs = new ModelRenderer(this);
		legs.setPos(10.0F, -19.5F, 0.5F);
		man.addChild(legs);
		legs.texOffs(0, 24).addBox(-9.0F, 11.5F, -0.5F, 5.0F, 8.0F, 1.0F, 0.0F, false);
		legs.texOffs(12, 24).addBox(-16.0F, 11.5F, -0.5F, 5.0F, 8.0F, 1.0F, 0.0F, false);

		arms = new ModelRenderer(this);
		arms.setPos(0.0F, 0.0F, 0.0F);
		man.addChild(arms);
		arms.texOffs(26, 9).addBox(-14.0F, -22.0F, 0.0F, 8.0F, 5.0F, 1.0F, 0.0F, false);
		arms.texOffs(26, 15).addBox(6.0F, -22.0F, 0.0F, 8.0F, 5.0F, 1.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 0.0F, 0.0F);
		man.addChild(head);
		head.texOffs(6, 0).addBox(-3.0F, -30.0F, 0.0F, 6.0F, 8.0F, 1.0F, 0.0F, false);
		head.texOffs(20, 0).addBox(3.0F, -29.0F, 0.0F, 2.0F, 7.0F, 1.0F, 0.0F, false);
		head.texOffs(0, 0).addBox(-5.0F, -29.0F, 0.0F, 2.0F, 7.0F, 1.0F, 0.0F, false);
		head.texOffs(26, 6).addBox(-0.5F, -20.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(30, 6).addBox(-0.5F, -16.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(34, 6).addBox(-0.5F, -12.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		face = new ModelRenderer(this);
		face.setPos(0.0F, 2.0F, 0.0F);
		head.addChild(face);
		face.texOffs(26, 2).addBox(2.0F, -28.75F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		face.texOffs(26, 0).addBox(-2.75F, -28.75F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		face.texOffs(26, 4).addBox(-3.25F, -27.0F, -0.5F, 6.5F, 1.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		man.addChild(body);
		body.texOffs(0, 9).addBox(-6.0F, -22.0F, 0.0F, 12.0F, 14.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_,
						  float p_225597_6_) {
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.parts().forEach((part) -> {
			part.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		});
	}

	private Iterable<ModelRenderer> parts() {
		return ImmutableList.of(man);
	}
}