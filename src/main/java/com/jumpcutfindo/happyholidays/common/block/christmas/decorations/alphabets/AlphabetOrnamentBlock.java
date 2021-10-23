package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.alphabets;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ChristmasBlockColor;
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
import net.minecraft.world.phys.shapes.VoxelShape;

public class AlphabetOrnamentBlock extends DecorationBlock {
    public static final EnumProperty<ChristmasBlockColor> BLOCK_COLOR = EnumProperty.create("christmas_color",
            ChristmasBlockColor.class);

    public static final String ALPHABET_TEMPLATE_ID = "alphabet_ornament_template";
    public static final String ALPHABET_A_ID = "alphabet_ornament_a";
    public static final String ALPHABET_B_ID = "alphabet_ornament_b";
    public static final String ALPHABET_C_ID = "alphabet_ornament_c";
    public static final String ALPHABET_D_ID = "alphabet_ornament_d";
    public static final String ALPHABET_E_ID = "alphabet_ornament_e";
    public static final String ALPHABET_F_ID = "alphabet_ornament_f";
    public static final String ALPHABET_G_ID = "alphabet_ornament_g";
    public static final String ALPHABET_H_ID = "alphabet_ornament_h";
    public static final String ALPHABET_I_ID = "alphabet_ornament_i";
    public static final String ALPHABET_J_ID = "alphabet_ornament_j";
    public static final String ALPHABET_K_ID = "alphabet_ornament_k";
    public static final String ALPHABET_L_ID = "alphabet_ornament_l";
    public static final String ALPHABET_M_ID = "alphabet_ornament_m";
    public static final String ALPHABET_N_ID = "alphabet_ornament_n";
    public static final String ALPHABET_O_ID = "alphabet_ornament_o";
    public static final String ALPHABET_P_ID = "alphabet_ornament_p";
    public static final String ALPHABET_Q_ID = "alphabet_ornament_q";
    public static final String ALPHABET_R_ID = "alphabet_ornament_r";
    public static final String ALPHABET_S_ID = "alphabet_ornament_s";
    public static final String ALPHABET_T_ID = "alphabet_ornament_t";
    public static final String ALPHABET_U_ID = "alphabet_ornament_u";
    public static final String ALPHABET_V_ID = "alphabet_ornament_v";
    public static final String ALPHABET_W_ID = "alphabet_ornament_w";
    public static final String ALPHABET_X_ID = "alphabet_ornament_x";
    public static final String ALPHABET_Y_ID = "alphabet_ornament_y";
    public static final String ALPHABET_Z_ID = "alphabet_ornament_z";

    public static final VoxelShape[][] ORNAMENT_SHAPES = new VoxelShape[][] {
            new VoxelShape[] {
                    Block.box(2.0D, 0.0D, 1.0D, 14.0D, 1.0D, 2.0D),
                    Block.box(1.0D, 0.0D, 2.0D, 15.0D, 1.0D, 14.0D),
                    Block.box(2.0D, 0.0D, 14.0D, 14.0D, 1.0D, 15.0D)
            },
            new VoxelShape[] {
                    Block.box(2.0D, 13.75D, 7.5D, 14.0D, 14.75D, 8.5D),
                    Block.box(1.0D, 1.75D, 7.5D, 15.0D, 13.75D, 8.5D),
                    Block.box(2.0D, 0.75D, 7.5D, 14.0D, 1.75D, 8.5D)
            },
            new VoxelShape[] { Block.box(1.0D, 1.0D, 0.0D, 15.0D, 15.0D, 5.0D) }
    };

    public AlphabetOrnamentBlock() {
        super(ORNAMENT_SHAPES);

        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(ATTACH_FACE, AttachFace.FLOOR)
                .setValue(FACING, Direction.NORTH)
                .setValue(BLOCK_COLOR, ChristmasBlockColor.NONE)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(BLOCK_COLOR, ATTACH_FACE, FACING, WATERLOGGED);
    }

    @Override
    public void configureBlock() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.translucent());
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
}
