package com.jumpcutfindo.happyholidays.common.item.christmas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChristmasBlockItem extends BlockItem {
    public Block block;
    public Properties properties;

    public final ChristmasRarity christmasRarity;
    public List<String> tooltipDescriptions;

    public ChristmasBlockItem(Block block, Properties properties) {
        this(block, properties, ChristmasRarity.COMMON);
    }

    public ChristmasBlockItem(Block block, Properties properties, ChristmasRarity rarity) {
        super(block, properties);

        this.block = block;
        this.properties = properties;

        this.christmasRarity = rarity;

        this.tooltipDescriptions = new ArrayList<>();
    }

    @Override
    public Component getName(ItemStack itemStack) {
        TranslatableComponent name = new TranslatableComponent(this.getDescriptionId(itemStack));
        return ChristmasItem.createStyledComponent(name, this.christmasRarity);
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        for (String description : tooltipDescriptions) {
            textComponents.add(new TextComponent(description));
        }
    }
}
