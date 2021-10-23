package com.jumpcutfindo.happyholidays.data.client.christmas;

import java.util.Set;
import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.FestiveCandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.FestiveCandyShape;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.OrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.StockingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.WallDecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.ChristmasHamBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.ChristmasPuddingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.LogCakeBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.MilkAndCookiesBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.ChristmasStarBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.ChristmasStarTier;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ChristmasBlockStates extends BlockStateProvider {
    public ChristmasBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, HappyHolidaysMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerBlocks();
        registerOrnaments();
        registerDecorations();
        registerCustom();
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
                ChristmasBlocks.SOGGY_GINGERBREAD_BRICKS.get(),

                ChristmasBlocks.GINGERBREAD_TILES.get(),
                ChristmasBlocks.GINGERBREAD_DOUGH_TILES.get(),
                ChristmasBlocks.SOGGY_GINGERBREAD_TILES.get()
        );

        // For blocks with horizontal directions only
        Set<Block> blocksWithHorizontals = Set.of(
                ChristmasBlocks.GIFT_WRAPPING_STATION.get()
        );

        // For blocks that are stairs
        Set<Pair<Block, ResourceLocation>> stairBlocks = Set.of(
                resourcePair(ChristmasBlocks.GINGERBREAD_DOUGH_STAIRS.get(), ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK.get()),
                resourcePair(ChristmasBlocks.GINGERBREAD_STAIRS.get(), ChristmasBlocks.GINGERBREAD_BLOCK.get()),
                resourcePair(ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS.get(), ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get()),

                resourcePair(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_STAIRS.get(), ChristmasBlocks.GINGERBREAD_DOUGH_BRICKS.get()),
                resourcePair(ChristmasBlocks.GINGERBREAD_BRICK_STAIRS.get(), ChristmasBlocks.GINGERBREAD_BRICKS.get()),
                resourcePair(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_STAIRS.get(), ChristmasBlocks.SOGGY_GINGERBREAD_BRICKS.get()),

                resourcePair(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_STAIRS.get(), ChristmasBlocks.GINGERBREAD_DOUGH_TILES.get()),
                resourcePair(ChristmasBlocks.GINGERBREAD_TILE_STAIRS.get(), ChristmasBlocks.GINGERBREAD_TILES.get()),
                resourcePair(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_STAIRS.get(), ChristmasBlocks.SOGGY_GINGERBREAD_TILES.get())
        );

        // For blocks that are slabs
        Set<Pair<Block, ResourceLocation>> slabBlocks = Set.of(
                resourcePair(ChristmasBlocks.GINGERBREAD_DOUGH_SLAB.get(), ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK.get()),
                resourcePair(ChristmasBlocks.GINGERBREAD_SLAB.get(), ChristmasBlocks.GINGERBREAD_BLOCK.get()),
                resourcePair(ChristmasBlocks.SOGGY_GINGERBREAD_SLAB.get(), ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get()),

                resourcePair(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_SLAB.get(), ChristmasBlocks.GINGERBREAD_DOUGH_BRICKS.get()),
                resourcePair(ChristmasBlocks.GINGERBREAD_BRICK_SLAB.get(), ChristmasBlocks.GINGERBREAD_BRICKS.get()),
                resourcePair(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_SLAB.get(), ChristmasBlocks.SOGGY_GINGERBREAD_BRICKS.get()),

                resourcePair(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_SLAB.get(), ChristmasBlocks.GINGERBREAD_DOUGH_TILES.get()),
                resourcePair(ChristmasBlocks.GINGERBREAD_TILE_SLAB.get(), ChristmasBlocks.GINGERBREAD_TILES.get()),
                resourcePair(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_SLAB.get(), ChristmasBlocks.SOGGY_GINGERBREAD_TILES.get())
        );

        // For blocks that are walls
        Set<Pair<Block, ResourceLocation>> wallBlocks = Set.of(
                resourcePair(ChristmasBlocks.GINGERBREAD_DOUGH_WALL.get(), ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK.get()),
                resourcePair(ChristmasBlocks.GINGERBREAD_WALL.get(), ChristmasBlocks.GINGERBREAD_BLOCK.get()),
                resourcePair(ChristmasBlocks.SOGGY_GINGERBREAD_WALL.get(), ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get()),

                resourcePair(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_WALL.get(), ChristmasBlocks.GINGERBREAD_DOUGH_BRICKS.get()),
                resourcePair(ChristmasBlocks.GINGERBREAD_BRICK_WALL.get(), ChristmasBlocks.GINGERBREAD_BRICKS.get()),
                resourcePair(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_WALL.get(), ChristmasBlocks.SOGGY_GINGERBREAD_BRICKS.get()),

                resourcePair(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_WALL.get(), ChristmasBlocks.GINGERBREAD_DOUGH_TILES.get()),
                resourcePair(ChristmasBlocks.GINGERBREAD_TILE_WALL.get(), ChristmasBlocks.GINGERBREAD_TILES.get()),
                resourcePair(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_WALL.get(), ChristmasBlocks.SOGGY_GINGERBREAD_TILES.get())
        );

        for (Block block : blocksWithoutModels) simpleBlock(block);
        for (Block block : blocksWithModels) blockOf(block);
        for (Block block : blocksWithHorizontals) horizontalBlock(block, modelFileOf(block));

        for (Pair<Block, ResourceLocation> blockPair : stairBlocks) stairsBlock((StairBlock) blockPair.getKey(), blockPair.getValue());
        for (Pair<Block, ResourceLocation> blockPair : slabBlocks) slabBlock((SlabBlock) blockPair.getKey(), blockPair.getValue(), blockPair.getValue());
        for (Pair<Block, ResourceLocation> blockPair : wallBlocks) wallBlock((WallBlock) blockPair.getKey(), blockPair.getValue());

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

                ChristmasBlocks.RED_CHRISTMAS_BELLS.get(),
                ChristmasBlocks.BLUE_CHRISTMAS_BELLS.get(),
                ChristmasBlocks.YELLOW_CHRISTMAS_BELLS.get(),
                ChristmasBlocks.GREEN_CHRISTMAS_BELLS.get(),
                ChristmasBlocks.GOLD_CHRISTMAS_BELLS.get(),
                ChristmasBlocks.SILVER_CHRISTMAS_BELLS.get(),

                ChristmasBlocks.CREEPER_HEAD_ORNAMENT.get(),
                ChristmasBlocks.SKELETON_HEAD_ORNAMENT.get(),
                ChristmasBlocks.WITHER_SKELETON_HEAD_ORNAMENT.get(),
                ChristmasBlocks.ZOMBIE_HEAD_ORNAMENT.get(),
                ChristmasBlocks.DROWNED_HEAD_ORNAMENT.get(),
                ChristmasBlocks.BLAZE_HEAD_ORNAMENT.get(),
                ChristmasBlocks.GHAST_HEAD_ORNAMENT.get(),
                ChristmasBlocks.PHANTOM_HEAD_ORNAMENT.get(),
                ChristmasBlocks.PIG_HEAD_ORNAMENT.get(),
                ChristmasBlocks.COW_HEAD_ORNAMENT.get(),
                ChristmasBlocks.CHICKEN_HEAD_ORNAMENT.get(),
                ChristmasBlocks.SHEEP_HEAD_ORNAMENT.get(),

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

    private void registerDecorations() {
        Set<Block> decorations = Set.of(
                ChristmasBlocks.SANTA_LIST.get(),
                ChristmasBlocks.CHRISTMAS_WREATH.get()
        );

        Set<Block> variedDecorations = Set.of(
                ChristmasBlocks.FROST.get()
        );

        for (Block block : decorations) wallDecorationOf(block);
        for (Block block : variedDecorations) wallDecorationWithVariants(block, 3);
    }

    private void registerCustom() {
        // Register festive candy cane block
        Block festiveCandyCaneBlock = ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get();
        getVariantBuilder(festiveCandyCaneBlock).forAllStates(state -> {
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
            String modelId = blockId(festiveCandyCaneBlock);

            FestiveCandyShape shape = state.getValue(FestiveCandyCaneBlock.CANDY_SHAPE);

            if (shape == FestiveCandyShape.O_X) modelId += "_ox";
            else modelId += "_xo";

            return builder.modelFile(modelFileOf(modelId)).build();
        });

        Function<Direction, Integer> getRotationY = (direction) -> switch (direction) {
            case EAST -> 270;
            case NORTH -> 180;
            case WEST -> 90;
            default -> 0;
        };

        // Register stockings
        Set<Block> stockings = Set.of(
                ChristmasBlocks.RED_STOCKING.get(),
                ChristmasBlocks.BLUE_STOCKING.get(),
                ChristmasBlocks.YELLOW_STOCKING.get(),
                ChristmasBlocks.GREEN_STOCKING.get(),
                ChristmasBlocks.GOLD_STOCKING.get(),
                ChristmasBlocks.SILVER_STOCKING.get()
        );

        for (Block block : stockings) {
            getVariantBuilder(block).forAllStatesExcept(state -> {
                ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
                String modelId = blockId(block);

                Direction facingDirection = state.getValue(WallDecorationBlock.FACING);
                boolean isFilled = state.getValue(StockingBlock.FILLED);
                boolean isEnchanted = state.getValue(StockingBlock.ENCHANTED);

                if (isEnchanted) modelId += "_enchanted";
                if (isFilled) modelId += "_filled";

                return builder.modelFile(modelFileOf(modelId)).rotationY(getRotationY.apply(facingDirection)).build();
            }, BlockStateProperties.WATERLOGGED);
        }

        // Register foods
        Set<Block> foodBlocks = Set.of(
                ChristmasBlocks.CHRISTMAS_HAM.get(),
                ChristmasBlocks.CHRISTMAS_PUDDING.get(),
                ChristmasBlocks.LOG_CAKE.get(),
                ChristmasBlocks.MILK_AND_COOKIES.get()
        );

        Function<Block, IntegerProperty> getBiteProperty = (block) -> {
            if (block instanceof ChristmasHamBlock || block instanceof LogCakeBlock) {
                return ChristmasHamBlock.BITES;
            } else if (block instanceof ChristmasPuddingBlock) {
                return ChristmasPuddingBlock.BITES;
            } else {
                return MilkAndCookiesBlock.BITES;
            }
        };

        for (Block block : foodBlocks) {
            getVariantBuilder(block).forAllStatesExcept(state -> {
                ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
                String modelId = blockId(block);

                Direction facingDirection = state.getValue(WallDecorationBlock.FACING);
                int bites = state.getValue(getBiteProperty.apply(block));

                switch (bites) {
                case 3 -> modelId += "_three_bites";
                case 2 -> modelId += "_two_bites";
                case 1 -> modelId += "_one_bite";
                }

                return builder.modelFile(modelFileOf(modelId)).rotationY(getRotationY.apply(facingDirection)).build();
            });
        }

        // Register Christmas Star
        Block christmasStarBlock = ChristmasBlocks.CHRISTMAS_STAR.get();
        getVariantBuilder(christmasStarBlock).forAllStates(state -> {
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
            String modelId = blockId(christmasStarBlock);

            Direction.Axis axis = state.getValue(ChristmasStarBlock.HORIZONTAL_AXIS);
            ChristmasStarTier starTier = state.getValue(ChristmasStarBlock.STAR_TIER);

            switch (starTier) {
            case TIER_0 -> modelId += "_tier_0";
            case TIER_1 -> modelId += "_tier_1";
            case TIER_2 -> modelId += "_tier_2";
            case TIER_3 -> modelId += "_tier_3";
            case TIER_4 -> modelId += "_tier_4";
            case TIER_5 -> modelId += "_tier_5";
            }

            if (axis == Direction.Axis.X) return builder.modelFile(modelFileOf(modelId)).rotationY(90).build();
            else return builder.modelFile(modelFileOf(modelId)).build();
        });
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
        ModelFile modelFile = modelFileOf(blockId);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);

        PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
            Direction dir = e.getKey();
            if (dir.getAxis().isHorizontal()) {
                builder.part().modelFile(modelFile).rotationY((((int) dir.toYRot()) + 180) % 360).uvLock(true).addModel()
                        .condition(e.getValue(), true)
                        .end();

                builder.part().modelFile(modelFile).rotationY((((int) dir.toYRot()) + 180) % 360).uvLock(true).addModel()
                        .condition(PipeBlock.NORTH, false)
                        .condition(PipeBlock.SOUTH, false)
                        .condition(PipeBlock.WEST, false)
                        .condition(PipeBlock.EAST, false);
            }
        });
    }

    // Creates blockstate for wall decoration blocks
    private void wallDecorationOf(Block block) {
        String blockId = blockId(block);

        Function<Direction, Integer> getRotationY = (direction) -> switch (direction) {
            case EAST -> 270;
            case NORTH -> 180;
            case WEST -> 90;
            default -> 0;
        };

        getVariantBuilder(block).forAllStatesExcept(state -> {
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();

            Direction facingDirection = state.getValue(WallDecorationBlock.FACING);

            return builder.modelFile(modelFileOf(block)).rotationY(getRotationY.apply(facingDirection)).build();
        }, BlockStateProperties.WATERLOGGED);
    }

    // Creates blockstate for wall decorations, where we have rotations along x-axis as well
    private void wallDecorationWithVariants(Block block, int variationCount) {
        String blockId = blockId(block);

        Function<Direction, Integer> getRotationY = (direction) -> switch (direction) {
            case EAST -> 270;
            case NORTH -> 180;
            case WEST -> 90;
            default -> 0;
        };

        getVariantBuilder(block).forAllStatesExcept(state -> {
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();

            Direction facingDirection = state.getValue(WallDecorationBlock.FACING);

            int rotY = getRotationY.apply(facingDirection);

            builder = builder.modelFile(modelFileOf(blockId + "_" + 0)).rotationY(rotY);

            for (int i = 1; i < variationCount; i++) {
                ModelFile modelFile = modelFileOf(blockId + "_" + i);
                builder = builder.nextModel().modelFile(modelFile).rotationY(rotY);
            }

            return builder.build();
        }, BlockStateProperties.WATERLOGGED);
    }

    private Pair<Block, ResourceLocation> resourcePair(Block block, Block resourceBlock) {
        return Pair.of(block, resourceOfBlock(blockId(resourceBlock)));
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
