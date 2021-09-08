package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc;

import java.util.Optional;
import java.util.UUID;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.WallDecorationBlock;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNice;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.INaughtyNiceHandler;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class SantaListBlock extends WallDecorationBlock {
    public static final String CHAT_NICE_MAX = "chat.happyholidays.santa_list_block.max_nice";
    public static final String CHAT_NICE = "chat.happyholidays.santa_list_block.nice";
    public static final String CHAT_NEUTRAL = "chat.happyholidays.santa_list_block.neutral";
    public static final String CHAT_NAUGHTY = "chat.happyholidays.santa_list_block.naughty";
    public static final String CHAT_NAUGHTY_MAX = "chat.happyholidays.santa_list_block.max_naughty";

    public static final String BLOCK_ID = "santa_list_block";

    public static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.WOOL)
                    .harvestLevel(-1)
                    .strength(0.1f)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
                    .noCollission();

    public static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0 ,16.0, 16.0,0.5);

    public SantaListBlock() {
        super(BLOCK_PROPERTIES, SHAPE);
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult rayTraceResult) {
        Optional<INaughtyNiceHandler> naughtyNiceOptional =
            playerEntity.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

        if (!world.isClientSide()) {
            if (naughtyNiceOptional.isPresent()) {
                NaughtyNiceMeter naughtyNiceMeter = (NaughtyNiceMeter) naughtyNiceOptional.get();

                if (naughtyNiceMeter.isMaxNaughty()) {
                    playerEntity.sendMessage(new TranslationTextComponent(CHAT_NAUGHTY_MAX).withStyle(TextFormatting.RED), UUID.randomUUID());
                } else if (naughtyNiceMeter.isNaughty()) {
                    playerEntity.sendMessage(new TranslationTextComponent(CHAT_NAUGHTY).withStyle(TextFormatting.RED), UUID.randomUUID());
                } else if (naughtyNiceMeter.isNeutral()) {
                    playerEntity.sendMessage(new TranslationTextComponent(CHAT_NEUTRAL).withStyle(TextFormatting.DARK_GRAY), UUID.randomUUID());
                } else if (naughtyNiceMeter.isMaxNice()) {
                    playerEntity.sendMessage(new TranslationTextComponent(CHAT_NICE_MAX).withStyle(TextFormatting.AQUA), UUID.randomUUID());
                } else if (naughtyNiceMeter.isNice()) {
                    playerEntity.sendMessage(new TranslationTextComponent(CHAT_NICE).withStyle(TextFormatting.AQUA), UUID.randomUUID());
                }
            } else {
                playerEntity.sendMessage(new TranslationTextComponent(CHAT_NEUTRAL).withStyle(TextFormatting.DARK_GRAY), UUID.randomUUID());
            }
        }

        return ActionResultType.sidedSuccess(world.isClientSide());
    }


}
