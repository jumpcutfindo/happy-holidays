package com.jumpcutfindo.happyholidays.common.block.christmas.misc;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.MusicBoxBlockEntity;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class MusicBoxBlock extends ChristmasBlock implements EntityBlock {
    public static final String BLOCK_ID = "music_box";

    public static final BooleanProperty HAS_SHEET_MUSIC = BooleanProperty.create("has_sheet_music");

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
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(HAS_SHEET_MUSIC, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(HAS_SHEET_MUSIC);
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
        return this.defaultBlockState()
                .setValue(HAS_SHEET_MUSIC, false);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos blockPos, Player playerEntity, InteractionHand hand, BlockHitResult rayTraceResult) {
        if (blockState.getValue(HAS_SHEET_MUSIC)) {
            this.dropSheetMusic(world, blockPos);
            blockState = blockState.setValue(HAS_SHEET_MUSIC, false);
            world.setBlock(blockPos, blockState, 2);
            return InteractionResult.sidedSuccess(world.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    public void setSheetMusic(LevelAccessor world, BlockPos blockPos, BlockState blockState, ItemStack itemStack) {
        BlockEntity blockEntity = world.getBlockEntity(blockPos);
        if (blockEntity instanceof MusicBoxBlockEntity) {
            ((MusicBoxBlockEntity) blockEntity).setSheetMusic(itemStack.copy(), true);
            world.setBlock(blockPos, blockState.setValue(HAS_SHEET_MUSIC, true), 2);
        }
    }

    private void dropSheetMusic(Level world, BlockPos blockPos) {
        if (!world.isClientSide) {
            BlockEntity blockEntity = world.getBlockEntity(blockPos);
            if (blockEntity instanceof MusicBoxBlockEntity) {
                MusicBoxBlockEntity musicBoxBlockEntity = (MusicBoxBlockEntity) blockEntity;
                ItemStack itemstack = musicBoxBlockEntity.getSheetMusic();
                if (!itemstack.isEmpty()) {
                    musicBoxBlockEntity.clearContent();
                    float f = 0.7F;
                    double d0 = (double)(world.random.nextFloat() * 0.7F) + (double)0.15F;
                    double d1 = (double)(world.random.nextFloat() * 0.7F) + (double)0.060000002F + 0.6D;
                    double d2 = (double)(world.random.nextFloat() * 0.7F) + (double)0.15F;
                    ItemStack itemstack1 = itemstack.copy();
                    ItemEntity itementity = new ItemEntity(world, (double)blockPos.getX() + d0, (double)blockPos.getY() + d1, (double)blockPos.getZ() + d2, itemstack1);
                    itementity.setDefaultPickUpDelay();
                    world.addFreshEntity(itementity);
                }
            }
        }
    }

    public void onRemove(BlockState blockState, Level world, BlockPos blockPos, BlockState blockState1, boolean b) {
        if (!blockState.is(blockState1.getBlock())) {
            this.dropSheetMusic(world, blockPos);

            super.onRemove(blockState, world, blockPos, blockState1, b);
        }
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos blockPos, BlockState blockState,
                                  Player playerEntity) {
        super.playerWillDestroy(world, blockPos, blockState, playerEntity);

        BlockEntity blockEntity = world.getBlockEntity(blockPos);
        if (blockEntity instanceof MusicBoxBlockEntity) {
            MusicBoxBlockEntity musicBoxBlockEntity = (MusicBoxBlockEntity) blockEntity;
            musicBoxBlockEntity.stopMusic();
        }
    }
}
