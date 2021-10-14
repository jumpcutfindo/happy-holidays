package com.jumpcutfindo.happyholidays.data.client;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {
    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, HappyHolidaysMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlock(ChristmasBlocks.GINGERBREAD_BLOCK.get());
    }

    private String blockId(Block block) {
        return block.getRegistryName().getPath();
    }

    private ResourceLocation resourceOfBlock(String id) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "block/" + id);
    }

    private ModelFile modelFileOf(Block block) {
        return new ModelFile.ExistingModelFile(resourceOfBlock(blockId(block)), models().existingFileHelper);
    }
}
