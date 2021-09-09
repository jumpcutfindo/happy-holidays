package com.jumpcutfindo.happyholidays.common.item.christmas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.item.IHappyHolidaysItem;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChristmasBlockItem extends BlockItem implements IHappyHolidaysItem {
    public Block block;
    public Properties properties;

    public ChristmasRarity christmasRarity;
    public List<String> tooltipDescriptions;

    public ChristmasBlockItem(Block block, Properties properties) {
        super(block, properties);

        this.block = block;
        this.properties = properties;

        this.christmasRarity = ChristmasRarity.COMMON;
        this.tooltipDescriptions = new ArrayList<>();
    }

    @Override
    public ITextComponent getName(ItemStack itemStack) {
        TranslationTextComponent name = new TranslationTextComponent(this.getDescriptionId(itemStack));

        switch (christmasRarity) {
        case RARE:
            return name.withStyle(ChristmasRarity.RARE.color);
        case LEGENDARY:
            return name.withStyle(ChristmasRarity.LEGENDARY.color);
        default:
            return name.withStyle(ChristmasRarity.COMMON.color);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        for (String description : tooltipDescriptions) {
            textComponents.add(new StringTextComponent(description));
        }
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public void configureItem() {
    }

    public ChristmasBlockItem setChristmasRarity(ChristmasRarity rarity) {
        this.christmasRarity = rarity;
        return this;
    }
}
