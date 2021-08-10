package com.jumpcutfindo.happyholidays.common.item.christmas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.BaubleOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigBaubleOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.head.HeadOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.LegendaryOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.lights.ChristmasLightBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.TinselBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.PresentBlock;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.ChristmasFoodBlockItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.ChristmasFoodItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.SheetMusicItem;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChristmasItem extends Item implements HappyHolidaysItem {
    public final String itemId;
    public final Item.Properties properties;

    public ChristmasRarity christmasRarity;
    public List<String> tooltipDescriptions;

    public ChristmasItem(String itemId, Item.Properties properties) {
        super(properties);

        this.itemId = itemId;
        this.properties = properties;

        this.christmasRarity = ChristmasRarity.COMMON;
        this.tooltipDescriptions = new ArrayList<>();
    }

    @Override
    public Item.Properties getProperties() {
        return properties;
    }

    @Override
    public void configureItem() {
    }

    @Override
    public ITextComponent getName(ItemStack itemStack) {
        TranslationTextComponent name = new TranslationTextComponent(this.getDescriptionId(itemStack));

        switch (christmasRarity) {
        case RARE:
            return name.withStyle(ChristmasRarity.RARE.color);
        case LEGENDARY:
            return name.withStyle(ChristmasRarity.LEGENDARY.color);
        case UNIQUE:
            return name.withStyle(ChristmasRarity.UNIQUE.color);
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

    public static boolean isSheetMusicItem(ItemStack itemStack) {
        return itemStack.getItem() instanceof SheetMusicItem;
    }

    public static boolean isFoodItem(ItemStack itemStack) {
        return itemStack.getItem() instanceof ChristmasFoodItem
                || itemStack.getItem() instanceof ChristmasFoodBlockItem;
    }

    public static boolean isLargeFoodItem(ItemStack itemStack) {
        return itemStack.getItem() instanceof ChristmasFoodBlockItem;
    }

    public static boolean isOrnamentItem(ItemStack item) {
        return isBasicOrnamentItem(item) || isRareOrnamentItem(item) || isLegendaryOrnamentItem(item);
    }

    public static boolean isBasicOrnamentItem(ItemStack item) {
        if (item.getItem() instanceof ChristmasBlockItem) {
            ChristmasBlockItem blockItem = (ChristmasBlockItem) item.getItem();

            return blockItem.getBlock() instanceof BaubleOrnamentBlock
                    || blockItem.getBlock() instanceof BigBaubleOrnamentBlock
                    || blockItem.getBlock() instanceof ChristmasLightBlock
                    || blockItem.getBlock() instanceof TinselBlock;
        }

        return false;
    }

    public static boolean isRareOrnamentItem(ItemStack item) {
        if (item.getItem() instanceof ChristmasBlockItem) {
            ChristmasBlockItem blockItem = (ChristmasBlockItem) item.getItem();

            return blockItem.getBlock() instanceof HeadOrnamentBlock;
        }

        return false;
    }

    public static boolean isLegendaryOrnamentItem(ItemStack item) {
        if (item.getItem() instanceof ChristmasBlockItem) {
            ChristmasBlockItem blockItem = (ChristmasBlockItem) item.getItem();

            return blockItem.getBlock() instanceof LegendaryOrnamentBlock;
        }

        return false;
    }

    public static boolean isPresentItem(ItemStack item) {
        return item.getItem() instanceof BlockItem && ((BlockItem) item.getItem()).getBlock() instanceof PresentBlock;
    }
}
