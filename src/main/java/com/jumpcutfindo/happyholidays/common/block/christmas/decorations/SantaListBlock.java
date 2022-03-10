package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.WallDecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNice;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.INaughtyNiceHandler;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.utils.message.GameplayMessage;
import com.jumpcutfindo.happyholidays.common.utils.message.MessageType;
import com.jumpcutfindo.happyholidays.common.utils.message.Messenger;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
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

public class SantaListBlock extends WallDecorationBlock implements ChristmasBlock {
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

    public SantaListBlock() {
        super(BLOCK_PROPERTIES);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos pos, Player playerEntity, InteractionHand hand, BlockHitResult rayTraceResult) {
        Optional<INaughtyNiceHandler> naughtyNiceOptional =
            playerEntity.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

        if (!world.isClientSide()) {
            if (naughtyNiceOptional.isPresent()) {
                NaughtyNiceMeter naughtyNiceMeter = (NaughtyNiceMeter) naughtyNiceOptional.get();
                NaughtyNiceMeter.State meterState = naughtyNiceMeter.getState();

                GameplayMessage message = null;

                switch (meterState) {
                case MAX_NAUGHTY -> message = new GameplayMessage(MessageType.NAUGHTY, CHAT_NAUGHTY_MAX);
                case NAUGHTY -> message = new GameplayMessage(MessageType.NAUGHTY, CHAT_NAUGHTY);
                case NEUTRAL -> message = new GameplayMessage(MessageType.NEUTRAL, CHAT_NEUTRAL);
                case MAX_NICE -> message = new GameplayMessage(MessageType.NICE, CHAT_NICE_MAX);
                case NICE -> message = new GameplayMessage(MessageType.NICE, CHAT_NICE);
                }

                if (message != null) Messenger.sendChatMessage(message, playerEntity);
            } else {
                GameplayMessage message = new GameplayMessage(MessageType.NEUTRAL, CHAT_NEUTRAL);
                Messenger.sendChatMessage(message, playerEntity);
            }
        }

        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }

    @Override
    public @NotNull VoxelShape getVoxelShape() {
        return Block.box(0.0, 0.0, 0.0 ,16.0, 16.0,0.5);
    }
}
