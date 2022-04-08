package com.jumpcutfindo.happyholidays.client.renderer.block;

import com.jumpcutfindo.happyholidays.common.block.cny.OfferingTableBlock;
import com.jumpcutfindo.happyholidays.common.block.entity.cny.OfferingTableBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OfferingTableRenderer implements BlockEntityRenderer<OfferingTableBlockEntity> {
    private ItemRenderer itemRenderer;

    public OfferingTableRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(OfferingTableBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        if (blockEntity.hasItem()) {
            BlockState blockState = blockEntity.getBlockState();
            ItemStack itemStack = blockEntity.getItem();

            int i = (int) blockEntity.getBlockPos().asLong();

            poseStack.pushPose();
            poseStack.translate(0.5D, 1.0D, 0.5D);

            poseStack.mulPose(Vector3f.YP.rotationDegrees(getRotationY(blockState.getValue(OfferingTableBlock.FACING))));
            poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));

            if (itemStack.getItem() instanceof BlockItem) poseStack.scale(0.6f, 0.6f, 0.6f);
            else poseStack.scale(0.40f, 0.40f, 0.40f);

            Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemTransforms.TransformType.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, i);
            poseStack.popPose();
        }
    }

    private static int getRotationY(Direction direction) {
        switch (direction) {
        case EAST -> {
            return 270;
        }
        case NORTH -> {
            return 0;
        }
        case WEST -> {
            return 90;
        }
        default -> {
            return 180;
        }
        }
    }
}
