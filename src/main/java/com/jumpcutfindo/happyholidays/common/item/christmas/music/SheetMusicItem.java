package com.jumpcutfindo.happyholidays.common.item.christmas.music;

import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.client.sound.christmas.MusicBoxSound;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SheetMusicItem extends ChristmasItem {

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.CHRISTMAS_GROUP)
                    .stacksTo(16);

    public ChristmasMusic music;

    public SheetMusicItem() {
        super(ITEM_PROPERTIES, ChristmasRarity.RARE);
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        textComponents.add(this.getDisplayName().withStyle(ChatFormatting.GRAY));
    }

    @OnlyIn(Dist.CLIENT)
    public MutableComponent getDisplayName() {
        return new TranslatableComponent(this.getDescriptionId() + ".desc");
    }

    public SheetMusicItem setMusic(ChristmasMusic music) {
        this.music = music;
        return this;
    }

    public ChristmasMusic getMusic() {
        return this.music;
    }

    public static MusicBoxSound createMusicBoxSound(ChristmasMusic music, BlockPos pos) {
        return new MusicBoxSound(ChristmasMusic.getSound(music), ChristmasMusic.getSoundDuration(music), pos);
    }
}
