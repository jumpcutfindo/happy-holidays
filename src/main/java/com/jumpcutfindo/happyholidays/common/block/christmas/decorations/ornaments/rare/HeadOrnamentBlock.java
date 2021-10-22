package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.rare;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.OrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.RareOrnament;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HeadOrnamentBlock extends OrnamentBlock implements RareOrnament {
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

    public static final VoxelShape[][] HEAD_ORNAMENT_SHAPES = new VoxelShape[][] {
            new VoxelShape[] { Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D) },
            new VoxelShape[] { Block.box(4.0D, 4.5D, 4.0D, 12.0D, 12.5D, 12.0D) },
            new VoxelShape[] { Block.box(4.0D, 0.0D, 0.0D, 12.0D, 10.0D, 8.0D) }
    };

    public HeadOrnamentBlock() {
        super(HEAD_ORNAMENT_SHAPES);
    }
}
