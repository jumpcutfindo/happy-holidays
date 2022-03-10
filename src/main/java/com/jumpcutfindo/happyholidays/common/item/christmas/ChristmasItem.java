package com.jumpcutfindo.happyholidays.common.item.christmas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChristmasItem extends Item {
    public final Item.Properties properties;

    public final ChristmasRarity christmasRarity;
    public List<String> tooltipDescriptions;

    public ChristmasItem(Item.Properties properties) {
        this(properties, ChristmasRarity.COMMON);
    }

    public ChristmasItem(Item.Properties properties, ChristmasRarity rarity) {
        super(properties);
        this.properties = properties;
        this.christmasRarity = rarity;

        this.tooltipDescriptions = new ArrayList<>();
    }

    @Override
    public Component getName(ItemStack itemStack) {
        TranslatableComponent name = new TranslatableComponent(this.getDescriptionId(itemStack));
        return createStyledComponent(name, this.christmasRarity);
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        for (String description : tooltipDescriptions) {
            textComponents.add(new TextComponent(description));
        }
    }

    public static Component createStyledComponent(BaseComponent component, ChristmasRarity rarity) {
        return component.withStyle(rarity.color);
    }
}
