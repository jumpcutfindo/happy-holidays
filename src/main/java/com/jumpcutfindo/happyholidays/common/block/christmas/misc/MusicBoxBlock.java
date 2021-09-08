package com.jumpcutfindo.happyholidays.common.block.christmas.misc;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasContainerBlock;
import com.jumpcutfindo.happyholidays.common.registry.TileEntityRegistry;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.MusicBoxTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class MusicBoxBlock extends ChristmasContainerBlock {
    public static final String BLOCK_ID = "music_box";

    public static final BooleanProperty HAS_SHEET_MUSIC = BooleanProperty.create("has_sheet_music");

    public static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.DECORATION)
                    .harvestLevel(-1)
                    .strength(0.25f)
                    .sound(SoundType.GLASS)
                    .noOcclusion();

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public MusicBoxTileEntity musicBoxTileEntity;

    public MusicBoxBlock() {
        super(BLOCK_PROPERTIES);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(HAS_SHEET_MUSIC, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(HAS_SHEET_MUSIC);
    }

    @Override
    public void configureBlock() {
        RenderTypeLookup.setRenderLayer(this, RenderType.translucent());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        this.musicBoxTileEntity = TileEntityRegistry.MUSIC_BOX_ENTITY_TYPE.get().create();
        return musicBoxTileEntity;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState()
                .setValue(HAS_SHEET_MUSIC, false);
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (blockState.getValue(HAS_SHEET_MUSIC)) {
            this.dropSheetMusic(world, blockPos);
            blockState = blockState.setValue(HAS_SHEET_MUSIC, false);
            world.setBlock(blockPos, blockState, 2);
            return ActionResultType.sidedSuccess(world.isClientSide);
        } else {
            return ActionResultType.PASS;
        }
    }

    public void setSheetMusic(IWorld world, BlockPos blockPos, BlockState blockState, ItemStack itemStack) {
        TileEntity tileentity = world.getBlockEntity(blockPos);
        if (tileentity instanceof MusicBoxTileEntity) {
            ((MusicBoxTileEntity) tileentity).setSheetMusic(itemStack.copy(), true);
            world.setBlock(blockPos, blockState.setValue(HAS_SHEET_MUSIC, true), 2);
        }
    }

    private void dropSheetMusic(World world, BlockPos blockPos) {
        if (!world.isClientSide) {
            TileEntity tileEntity = world.getBlockEntity(blockPos);
            if (tileEntity instanceof MusicBoxTileEntity) {
                MusicBoxTileEntity musicBoxTileEntity = (MusicBoxTileEntity) tileEntity;
                ItemStack itemstack = musicBoxTileEntity.getSheetMusic();
                if (!itemstack.isEmpty()) {
                    musicBoxTileEntity.clearContent();
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

    public void onRemove(BlockState blockState, World world, BlockPos blockPos, BlockState blockState1, boolean b) {
        if (!blockState.is(blockState1.getBlock())) {
            this.dropSheetMusic(world, blockPos);

            super.onRemove(blockState, world, blockPos, blockState1, b);
        }
    }

    @Override
    public void playerWillDestroy(World world, BlockPos blockPos, BlockState blockState,
                                  PlayerEntity playerEntity) {
        super.playerWillDestroy(world, blockPos, blockState, playerEntity);

        TileEntity tileEntity = world.getBlockEntity(blockPos);
        if (tileEntity instanceof MusicBoxTileEntity) {
            MusicBoxTileEntity musicBoxTileEntity = (MusicBoxTileEntity) tileEntity;
            musicBoxTileEntity.stopMusic();
        }
    }
}
