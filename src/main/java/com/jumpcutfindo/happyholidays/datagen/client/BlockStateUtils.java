package com.jumpcutfindo.happyholidays.datagen.client;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;

public class BlockStateUtils {
    public static String blockId(Block block) {
        return block.getRegistryName().getPath();
    }

    public static int getRotationY(Direction direction) {
        switch (direction) {
        case EAST -> {
            return 270;
        }
        case NORTH -> {
            return 180;
        }
        case WEST -> {
            return 90;
        }
        default -> {
            return 0;
        }
        }
    }

    public static ResourceLocation resourceOfBlock(String id) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "block/" + id);
    }

    public static ModelFile modelFileOf(BlockStateProvider provider, String modelId) {
        return new ModelFile.ExistingModelFile(resourceOfBlock(modelId), provider.models().existingFileHelper);
    }

    public static ModelFile modelFileOf(BlockStateProvider provider, Block block) {
        return new ModelFile.ExistingModelFile(resourceOfBlock(blockId(block)), provider.models().existingFileHelper);
    }
}
