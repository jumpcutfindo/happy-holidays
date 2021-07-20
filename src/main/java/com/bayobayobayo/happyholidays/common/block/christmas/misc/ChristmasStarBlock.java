package com.bayobayobayo.happyholidays.common.block.christmas.misc;

import javax.annotation.Nullable;

import com.bayobayobayo.happyholidays.common.block.christmas.ChristmasBlock;
import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;
import com.bayobayobayo.happyholidays.common.registry.TileEntityRegistry;
import com.bayobayobayo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ChristmasStarBlock extends ChristmasBlock {
    public static final String BLOCK_ID = "christmas_star_block";

    public static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.DECORATION)
                    .harvestLevel(-1)
                    .strength(0.25f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .noCollission();

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public ChristmasStarBlock() {
        super(BLOCK_ID, BLOCK_PROPERTIES, ITEM_PROPERTIES);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityRegistry.CHRISTMAS_STAR_ENTITY_TYPE.get().create();
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos,
                                PlayerEntity playerEntity, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (world.isClientSide()) return ActionResultType.SUCCESS;
        else {
            TileEntity te = world.getBlockEntity(blockPos);
            if (te instanceof ChristmasStarTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) playerEntity, (ChristmasStarTileEntity) te, blockPos);
            }
            return ActionResultType.CONSUME;
        }
    }

    @Override
    public void playerWillDestroy(World world, BlockPos blockPos, BlockState blockState,
                                  PlayerEntity playerEntity) {
        TileEntity te = world.getBlockEntity(blockPos);
        if (te instanceof ChristmasStarTileEntity) {
            InventoryHelper.dropContents(world, blockPos, (ChristmasStarTileEntity) te);
        }
    }
}
