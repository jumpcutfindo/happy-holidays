package com.jumpcutfindo.happyholidays.data.client;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ConnectedOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.OrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.WallDecorationShape;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {
    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, HappyHolidaysMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerBlocks();
        registerOrnaments();
    }

    private void registerBlocks() {
        // For blocks with custom models
        Set<Block> blocksWithModels = Set.of(
                ChristmasBlocks.BABY_PRESENT.get(),
                ChristmasBlocks.ADULT_PRESENT.get(),
                ChristmasBlocks.ELDER_PRESENT.get(),

                ChristmasBlocks.MUSIC_BOX.get()
        );

        // For default blocks where all sides are the same
        Set<Block> blocksWithoutModels = Set.of(
                ChristmasBlocks.CANDY_CANE_BLOCK.get(),

                ChristmasBlocks.GINGERBREAD_BLOCK.get(),
                ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK.get(),
                ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get(),

                ChristmasBlocks.GINGERBREAD_BRICKS.get(),
                ChristmasBlocks.GINGERBREAD_DOUGH_BRICKS.get(),
                ChristmasBlocks.SOGGY_GINGERBREAD_BRICKS.get()
        );

        for (Block block : blocksWithModels) blockOf(block);
        for (Block block : blocksWithoutModels) simpleBlock(block);
    }

    private void registerOrnaments() {
        Set<Block> ornamentBlocks = Set.of(
                ChristmasBlocks.BABY_PRESENT_ORNAMENT.get(),
                ChristmasBlocks.ADULT_PRESENT_ORNAMENT.get(),
                ChristmasBlocks.ELDER_PRESENT_ORNAMENT.get(),
                ChristmasBlocks.CANDY_CANE_ORNAMENT.get(),
                ChristmasBlocks.SANTA_ELF_ORNAMENT.get(),
                ChristmasBlocks.GINGERBREAD_MAN_ORNAMENT.get(),
                ChristmasBlocks.GRINCH_ORNAMENT.get(),

                ChristmasBlocks.RED_BAUBLE.get(),
                ChristmasBlocks.BLUE_BAUBLE.get(),
                ChristmasBlocks.YELLOW_BAUBLE.get(),
                ChristmasBlocks.GREEN_BAUBLE.get(),
                ChristmasBlocks.GOLD_BAUBLE.get(),
                ChristmasBlocks.SILVER_BAUBLE.get(),

                ChristmasBlocks.BIG_RED_BAUBLE.get(),
                ChristmasBlocks.BIG_BLUE_BAUBLE.get(),
                ChristmasBlocks.BIG_YELLOW_BAUBLE.get(),
                ChristmasBlocks.BIG_GREEN_BAUBLE.get(),
                ChristmasBlocks.BIG_GOLD_BAUBLE.get(),
                ChristmasBlocks.BIG_SILVER_BAUBLE.get(),

                ChristmasBlocks.CREEPER_HEAD_ORNAMENT.get(),
                ChristmasBlocks.SKELETON_HEAD_ORNAMENT.get(),
                ChristmasBlocks.WITHER_SKELETON_HEAD_ORNAMENT.get(),
                ChristmasBlocks.ZOMBIE_HEAD_ORNAMENT.get(),

                ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_A.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_B.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_C.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_D.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_E.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_F.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_G.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_H.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_I.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_J.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_K.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_L.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_M.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_N.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_O.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_P.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_Q.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_R.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_S.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_T.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_U.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_V.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_W.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_X.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_Y.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_Z.get()
        );

        Set<Block> connectedOrnamentBlocks = Set.of(
                ChristmasBlocks.RED_TINSEL.get(),
                ChristmasBlocks.BLUE_TINSEL.get(),
                ChristmasBlocks.YELLOW_TINSEL.get(),
                ChristmasBlocks.GREEN_TINSEL.get(),
                ChristmasBlocks.GOLD_TINSEL.get(),
                ChristmasBlocks.SILVER_TINSEL.get(),

                ChristmasBlocks.RED_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.BLUE_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.YELLOW_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.GREEN_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.GOLD_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.SILVER_CHRISTMAS_LIGHTS.get()
        );

        for (Block block : ornamentBlocks) ornamentOf(block);
        for (Block block : connectedOrnamentBlocks) connectedOrnamentOf(block);
    }

    // Creates blockstate for blocks
    private void blockOf(Block block) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();

            return builder.modelFile(modelFileOf(block)).build();
        }, BlockStateProperties.WATERLOGGED);
    }

    // Creates blockstate for ornament blocks
    private void ornamentOf(Block block) {
        String blockId = blockId(block);

        Function<Direction, Integer> getRotationY = (direction) -> switch (direction) {
            case EAST -> 270;
            case NORTH -> 180;
            case WEST -> 90;
            default -> 0;
        };

        getVariantBuilder(block).forAllStatesExcept(state -> {
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();

            Direction facingDirection = state.getValue(OrnamentBlock.FACING);
            AttachFace attachFace = state.getValue(OrnamentBlock.ATTACH_FACE);

            switch (attachFace) {
            case WALL -> builder = builder.modelFile(modelFileOf(blockId + "_side"));
            case CEILING -> builder = builder.modelFile(modelFileOf(blockId + "_hanging"));
            case FLOOR -> builder = builder.modelFile(modelFileOf(blockId));
            }

            return builder.rotationY(getRotationY.apply(facingDirection)).build();
        }, OrnamentBlock.WATERLOGGED);
    }

    // Creates blockstate for connected ornament blocks
    private void connectedOrnamentOf(Block block) {
        String blockId = blockId(block);

        BiFunction<WallDecorationShape, Direction, Integer> getRotationY = (wallShape, direction) -> {
            switch (wallShape) {
                case STRAIGHT, SIDE_FACE -> {
                    return direction == Direction.EAST ? 270
                            : direction == Direction.NORTH ? 180
                            : direction == Direction.SOUTH ? 0
                            : 90;
                }
                case LEFT_FACE ->  {
                    return direction == Direction.EAST ? 0
                            : direction == Direction.NORTH ? 270
                            : direction == Direction.SOUTH ? 90
                            : 180;
                }
                case RIGHT_FACE -> {
                    return direction == Direction.EAST ? 180
                            : direction == Direction.NORTH ? 90
                            : direction == Direction.SOUTH ? 270
                            : 0;
                }
                default -> {
                    return 0;
                }
            }
        };

        getVariantBuilder(block).forAllStatesExcept(state -> {
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();

            WallDecorationShape wallShape = state.getValue(ConnectedOrnamentBlock.WALL_SHAPE);
            Direction facingDirection = state.getValue(ConnectedOrnamentBlock.FACING);

            switch (wallShape) {
            case LEFT_FACE -> builder = builder.modelFile(modelFileOf(blockId + "_left_face"));
            case RIGHT_FACE -> builder = builder.modelFile(modelFileOf(blockId + "_right_face"));
            case SIDE_FACE -> builder = builder.modelFile(modelFileOf(blockId + "_side_face"));
            case ALL_FACE -> builder = builder.modelFile(modelFileOf(blockId + "_all_face"));
            default -> builder = builder.modelFile(modelFileOf(blockId));
            }

            return builder.rotationY(getRotationY.apply(wallShape, facingDirection)).build();
        }, ConnectedOrnamentBlock.WATERLOGGED);
    }

    private String blockId(Block block) {
        return block.getRegistryName().getPath();
    }

    private ResourceLocation resourceOfBlock(String id) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "block/" + id);
    }

    private ModelFile modelFileOf(String modelId) {
        return new ModelFile.ExistingModelFile(resourceOfBlock(modelId), models().existingFileHelper);
    }

    private ModelFile modelFileOf(Block block) {
        return new ModelFile.ExistingModelFile(resourceOfBlock(blockId(block)), models().existingFileHelper);
    }
}
