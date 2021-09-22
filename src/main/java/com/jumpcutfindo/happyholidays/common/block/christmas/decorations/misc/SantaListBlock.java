package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc;

import java.util.Optional;
import java.util.UUID;

import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNice;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.INaughtyNiceHandler;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SantaListBlock extends WallDecorationBlock {
    public static final String CHAT_NICE_MAX = "chat.happyholidays.santa_list.max_nice";
    public static final String CHAT_NICE = "chat.happyholidays.santa_list.nice";
    public static final String CHAT_NEUTRAL = "chat.happyholidays.santa_list.neutral";
    public static final String CHAT_NAUGHTY = "chat.happyholidays.santa_list.naughty";
    public static final String CHAT_NAUGHTY_MAX = "chat.happyholidays.santa_list.max_naughty";

    public static final String BLOCK_ID = "santa_list";

    public static final Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.WOOL)
                    .strength(0.1f)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
                    .noCollission();

    public static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0 ,16.0, 16.0,0.5);

    public SantaListBlock() {
        super(BLOCK_PROPERTIES, SHAPE);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos pos, Player playerEntity, InteractionHand hand, BlockHitResult rayTraceResult) {
        Optional<INaughtyNiceHandler> naughtyNiceOptional =
            playerEntity.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

        if (!world.isClientSide()) {
            if (naughtyNiceOptional.isPresent()) {
                NaughtyNiceMeter naughtyNiceMeter = (NaughtyNiceMeter) naughtyNiceOptional.get();

                if (naughtyNiceMeter.isMaxNaughty()) {
                    playerEntity.sendMessage(new TranslatableComponent(CHAT_NAUGHTY_MAX).withStyle(ChatFormatting.RED), UUID.randomUUID());
                } else if (naughtyNiceMeter.isNaughty()) {
                    playerEntity.sendMessage(new TranslatableComponent(CHAT_NAUGHTY).withStyle(ChatFormatting.RED), UUID.randomUUID());
                } else if (naughtyNiceMeter.isNeutral()) {
                    playerEntity.sendMessage(new TranslatableComponent(CHAT_NEUTRAL).withStyle(ChatFormatting.DARK_GRAY), UUID.randomUUID());
                } else if (naughtyNiceMeter.isMaxNice()) {
                    playerEntity.sendMessage(new TranslatableComponent(CHAT_NICE_MAX).withStyle(ChatFormatting.AQUA), UUID.randomUUID());
                } else if (naughtyNiceMeter.isNice()) {
                    playerEntity.sendMessage(new TranslatableComponent(CHAT_NICE).withStyle(ChatFormatting.AQUA), UUID.randomUUID());
                }
            } else {
                playerEntity.sendMessage(new TranslatableComponent(CHAT_NEUTRAL).withStyle(ChatFormatting.DARK_GRAY), UUID.randomUUID());
            }
        }

        return InteractionResult.sidedSuccess(world.isClientSide());
    }


}
