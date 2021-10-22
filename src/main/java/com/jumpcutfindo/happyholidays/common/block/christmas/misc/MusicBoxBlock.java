package com.jumpcutfindo.happyholidays.common.block.christmas.misc;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.MusicBoxBlockEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

public class MusicBoxBlock extends ChristmasBlock implements EntityBlock {
    public static final String BLOCK_ID = "music_box";

    public static final Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.DECORATION)
                    .strength(0.25f)
                    .sound(SoundType.GLASS)
                    .noOcclusion();

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public MusicBoxBlockEntity musicBoxBlockEntity;

    public MusicBoxBlock() {
        super(BLOCK_PROPERTIES);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
    }

    @Override
    public void configureBlock() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.translucent());
    }

    @Override
    public RenderShape getRenderShape(BlockState p_149645_1_) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        this.musicBoxBlockEntity = ChristmasBlockEntities.MUSIC_BOX_ENTITY_TYPE.get().create(pos, state);
        return musicBoxBlockEntity;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos blockPos, Player playerEntity, InteractionHand hand, BlockHitResult rayTraceResult) {
        if (world.isClientSide()) return InteractionResult.SUCCESS;
        else {
            BlockEntity te = world.getBlockEntity(blockPos);
            if (te instanceof MusicBoxBlockEntity) {
                NetworkHooks.openGui((ServerPlayer) playerEntity, (MusicBoxBlockEntity) te, blockPos);
            }
            return InteractionResult.CONSUME;
        }
    }

    public void onRemove(BlockState blockState, Level world, BlockPos blockPos, BlockState blockState1, boolean b) {
        if (!blockState.is(blockState1.getBlock())) {
            super.onRemove(blockState, world, blockPos, blockState1, b);
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState,
                                  Player playerEntity) {
        super.playerWillDestroy(level, blockPos, blockState, playerEntity);

        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof MusicBoxBlockEntity) {
            MusicBoxBlockEntity musicBoxBlockEntity = (MusicBoxBlockEntity) blockEntity;
            musicBoxBlockEntity.stopMusic();

            NonNullList<ItemStack> containerItems = musicBoxBlockEntity.getItems();
            for (ItemStack itemStack : musicBoxBlockEntity.getItems()) {
                popResource(level, blockPos, itemStack);
            }
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createMusicBoxTicker(level, blockEntityType, ChristmasBlockEntities.MUSIC_BOX_ENTITY_TYPE.get());
    }

    public static <T extends BlockEntity> BlockEntityTicker<T> createMusicBoxTicker(Level level, BlockEntityType<T> blockEntityType, BlockEntityType<? extends MusicBoxBlockEntity> otherEntityType) {
        return level.isClientSide() ? null : ChristmasBlock.createTickerHelper(blockEntityType, otherEntityType, MusicBoxBlockEntity::serverTick);
    }
}
