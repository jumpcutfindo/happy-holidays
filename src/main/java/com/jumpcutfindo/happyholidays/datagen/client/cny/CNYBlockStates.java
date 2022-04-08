package com.jumpcutfindo.happyholidays.datagen.client.cny;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.cny.OfferingTableBlock;
import com.jumpcutfindo.happyholidays.common.registry.cny.CNYBlocks;
import com.jumpcutfindo.happyholidays.datagen.client.BlockStateUtils;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CNYBlockStates extends BlockStateProvider {
    public CNYBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, HappyHolidaysMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerCustom();
    }

    private void registerCustom() {
        // Create offering table
        getVariantBuilder(CNYBlocks.OFFERING_TABLE.get()).forAllStates(state -> {
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
            String modelId = BlockStateUtils.blockId(CNYBlocks.OFFERING_TABLE.get());

            OfferingTableBlock.OfferingTablePart part = state.getValue(OfferingTableBlock.PART);
            switch (part) {
            case RIGHT -> {
                modelId += "_right";
            }
            case LEFT -> {
                modelId += "_left";
            }
            case CENTER -> {
                modelId += "_center";
            }
            }

            Direction facing = state.getValue(OfferingTableBlock.FACING);

            return builder.modelFile(BlockStateUtils.modelFileOf(this, modelId)).rotationY(BlockStateUtils.getRotationY(facing)).build();
        });
    }
}
