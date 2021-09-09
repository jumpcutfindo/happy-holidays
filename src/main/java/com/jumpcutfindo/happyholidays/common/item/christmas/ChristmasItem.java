package com.jumpcutfindo.happyholidays.common.item.christmas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.BaubleOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.BigBaubleOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.rare.HeadOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.LegendaryOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.ChristmasLightBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.TinselBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.PresentBlock;
import com.jumpcutfindo.happyholidays.common.item.IHappyHolidaysItem;
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

public class ChristmasItem extends Item implements IHappyHolidaysItem {
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
}
