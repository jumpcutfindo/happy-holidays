package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AlphabetOrnamentBlock extends DecorationBlock implements BasicOrnament, ChristmasBlock {
    public static final EnumProperty<ChristmasBlockColor> BLOCK_COLOR = EnumProperty.create("christmas_color",
            ChristmasBlockColor.class);

    public AlphabetOrnamentBlock() {
        super();

        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(ATTACH_FACE, AttachFace.FLOOR)
                .setValue(FACING, Direction.NORTH)
                .setValue(BLOCK_COLOR, ChristmasBlockColor.NONE)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    public @NotNull VoxelShape getFloorShape() {
        return Shapes.or(
                Block.box(2.0D, 0.0D, 1.0D, 14.0D, 1.0D, 2.0D),
                Block.box(1.0D, 0.0D, 2.0D, 15.0D, 1.0D, 14.0D),
                Block.box(2.0D, 0.0D, 14.0D, 14.0D, 1.0D, 15.0D)
        );
    }

    @Override
    public @NotNull VoxelShape getCeilingShape() {
        return Shapes.or(
                Block.box(2.0D, 13.75D, 7.5D, 14.0D, 14.75D, 8.5D),
                Block.box(1.0D, 1.75D, 7.5D, 15.0D, 13.75D, 8.5D),
                Block.box(2.0D, 0.75D, 7.5D, 14.0D, 1.75D, 8.5D)
        );
    }

    @Override
    public @NotNull VoxelShape getWallShape() {
        return Block.box(1.0D, 1.0D, 0.0D, 15.0D, 15.0D, 5.0D);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(BLOCK_COLOR, ATTACH_FACE, FACING, WATERLOGGED);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos blockPos, Player playerEntity, InteractionHand hand, BlockHitResult rayTraceResult) {
        if (!world.isClientSide()) {
            ItemStack heldItem = playerEntity.getItemInHand(hand);

            if (heldItem.is(ChristmasItems.RED_CHRISTMAS_DYE.get())) stainGlass(world, blockState, blockPos, ChristmasBlockColor.RED);
            else if (heldItem.is(ChristmasItems.BLUE_CHRISTMAS_DYE.get())) stainGlass(world, blockState, blockPos, ChristmasBlockColor.BLUE);
            else if (heldItem.is(ChristmasItems.YELLOW_CHRISTMAS_DYE.get())) stainGlass(world, blockState, blockPos, ChristmasBlockColor.YELLOW);
            else if (heldItem.is(ChristmasItems.GREEN_CHRISTMAS_DYE.get())) stainGlass(world, blockState, blockPos, ChristmasBlockColor.GREEN);
            else if (heldItem.is(ChristmasItems.GOLD_CHRISTMAS_DYE.get())) stainGlass(world, blockState, blockPos, ChristmasBlockColor.GOLD);
            else if (heldItem.is(ChristmasItems.SILVER_CHRISTMAS_DYE.get())) stainGlass(world, blockState, blockPos, ChristmasBlockColor.SILVER);
        }

        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    private static void stainGlass(Level world, BlockState blockState, BlockPos blockPos, ChristmasBlockColor color) {
        world.setBlock(blockPos, blockState.setValue(BLOCK_COLOR, color), 0);
        world.playSound(null, blockPos, SoundEvents.DYE_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.translucent());
    }
}
