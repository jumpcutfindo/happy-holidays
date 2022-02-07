package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.rare;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.RareOrnament;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HeadOrnamentBlock extends DecorationBlock implements ChristmasLike, ChristmasBlock, RareOrnament {
    public static final String ZOMBIE_HEAD_ORNAMENT_ID = "zombie_head_ornament";
    public static final String SKELETON_HEAD_ORNAMENT_ID = "skeleton_head_ornament";
    public static final String WITHER_SKELETON_HEAD_ORNAMENT_ID = "wither_skeleton_head_ornament";
    public static final String CREEPER_HEAD_ORNAMENT_ID = "creeper_head_ornament";
    public static final String DROWNED_HEAD_ORNAMENT_ID = "drowned_head_ornament";
    public static final String BLAZE_HEAD_ORNAMENT_ID = "blaze_head_ornament";
    public static final String GHAST_HEAD_ORNAMENT_ID = "ghast_head_ornament";
    public static final String PHANTOM_HEAD_ORNAMENT_ID = "phantom_head_ornament";

    public static final String PIG_HEAD_ORNAMENT_ID = "pig_head_ornament";
    public static final String CHICKEN_HEAD_ORNAMENT_ID = "chicken_head_ornament";
    public static final String COW_HEAD_ORNAMENT_ID = "cow_head_ornament";
    public static final String SHEEP_HEAD_ORNAMENT_ID = "sheep_head_ornament";

    public HeadOrnamentBlock() {
        super();
    }

    @Override
    public @NotNull VoxelShape getFloorShape() {
        return Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
    }

    @Override
    public @NotNull VoxelShape getCeilingShape() {
        return Block.box(4.0D, 4.5D, 4.0D, 12.0D, 12.5D, 12.0D);
    }

    @Override
    public @NotNull VoxelShape getWallShape() {
        return Block.box(4.0D, 0.0D, 0.0D, 12.0D, 10.0D, 8.0D);
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutout());
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.RARE;
    }
}
