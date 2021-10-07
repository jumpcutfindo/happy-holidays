package com.jumpcutfindo.happyholidays.common.item.christmas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChristmasItem extends Item implements IChristmasItem {
    public final Item.Properties properties;

    public ChristmasRarity christmasRarity = ChristmasRarity.COMMON;
    public List<String> tooltipDescriptions;

    public ChristmasItem(Item.Properties properties) {
        super(properties);

        this.properties = properties;

        this.tooltipDescriptions = new ArrayList<>();
    }

    @Override
    public Item.Properties getProperties() {
        return properties;
    }

    @Override
    public Component getName(ItemStack itemStack) {
        TranslatableComponent name = new TranslatableComponent(this.getDescriptionId(itemStack));
        return IChristmasItem.createStyledComponent(name, getChristmasRarity());
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        for (String description : tooltipDescriptions) {
            textComponents.add(new TextComponent(description));
        }
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.COMMON;
    }
}
