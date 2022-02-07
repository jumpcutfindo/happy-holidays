package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.BasicOrnament;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChristmasBellBlock extends DecorationBlock implements ChristmasLike, ChristmasBlock, BasicOrnament {
    public static final String RED_BELLS_ID = "red_christmas_bells";
    public static final String BLUE_BELLS_ID = "blue_christmas_bells";
    public static final String YELLOW_BELLS_ID = "yellow_christmas_bells";
    public static final String GREEN_BELLS_ID = "green_christmas_bells";
    public static final String GOLD_BELLS_ID = "gold_christmas_bells";
    public static final String SILVER_BELLS_ID = "silver_christmas_bells";

    public ChristmasBellBlock() {
        super();
    }

    @Override
    public @NotNull VoxelShape getFloorShape() {
        return Shapes.or(
                Shapes.or(
                        Block.box(3.0, 0.0, 8.0, 8.0, 1.0, 13.0),
                        Block.box(3.5, 1.0, 8.5, 7.5, 5.5, 12.5),
                        Block.box(4.0, 4.5, 9.0, 7.0, 5.5, 12.0),
                        Block.box(4.5, 5.0, 9.5, 6.5, 6.0, 11.5)
                ),
                Shapes.or(
                        Block.box(8.0, 0.0, 3.0, 13.0, 1.0, 8.0),
                        Block.box(8.5, 1.0, 3.5, 12.5, 5.0, 7.5),
                        Block.box(9.0, 4.5, 4.0, 12.0, 5.5, 7.0),
                        Block.box(9.5, 5.0, 4.5, 11.5, 6.0, 6.5)
                )
        );
    }

    @Override
    public @NotNull VoxelShape getCeilingShape() {
        return Block.box(1.0, 9.0, 5.5, 15.0, 16.0, 10.5);
    }

    @Override
    public @NotNull VoxelShape getWallShape() {
        return Block.box(2.0, 4.0, 0.0, 14.0, 11.0, 5.0);
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.COMMON;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            ((ServerLevel) level).playSound(null, blockPos, ChristmasSounds.CHRISTMAS_BELL_RING.get(), SoundSource.BLOCKS, 1.0f, 0.75f + (RANDOM.nextFloat() * 0.25f));
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}
