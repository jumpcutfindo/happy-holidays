package com.jumpcutfindo.happyholidays.common.block.christmas.gifts;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.handlers.modules.ModuleHandler;
import com.jumpcutfindo.happyholidays.common.registry.TileEntityRegistry;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.GiftWrapperTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class GiftWrapperBlock extends ChristmasBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final String BLOCK_ID = "gift_wrapper_block";

    public static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.WOOD)
                    .harvestLevel(-1)
                    .strength(0.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion();

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public GiftWrapperBlock() {
        super(BLOCK_ID, BLOCK_PROPERTIES, ITEM_PROPERTIES);
    }

    @Override
    public void configureBlock() {
        RenderTypeLookup.setRenderLayer(this, RenderType.cutoutMipped());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityRegistry.GIFT_WRAPPER_ENTITY_TYPE.get().create();
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos,
                                PlayerEntity playerEntity, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (world.isClientSide()) return ActionResultType.SUCCESS;
        else {
            TileEntity te = world.getBlockEntity(blockPos);
            if (te instanceof GiftWrapperTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) playerEntity, (GiftWrapperTileEntity) te, blockPos);
            }
            return ActionResultType.CONSUME;
        }
    }

    @Override
    public void playerWillDestroy(World world, BlockPos blockPos, BlockState blockState,
                                  PlayerEntity playerEntity) {
        super.playerWillDestroy(world, blockPos, blockState, playerEntity);
        TileEntity te = world.getBlockEntity(blockPos);
        if (te instanceof GiftWrapperTileEntity) {
            InventoryHelper.dropContents(world, blockPos, (GiftWrapperTileEntity) te);
        }
    }
}
