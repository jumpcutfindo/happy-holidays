package com.bayobayobayo.happyholidays.common.item.christmas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BaubleOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigBaubleOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.head.HeadOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.lights.ChristmasLightBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.TinselBlock;
import com.bayobayobayo.happyholidays.common.item.HappyHolidaysItem;
import com.bayobayobayo.happyholidays.common.item.christmas.food.ChristmasFoodBlockItem;
import com.bayobayobayo.happyholidays.common.item.christmas.food.ChristmasFoodItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.SheetMusicItem;
import com.bayobayobayo.happyholidays.common.registry.ItemRegistry;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

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
}
