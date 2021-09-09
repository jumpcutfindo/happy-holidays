package com.jumpcutfindo.happyholidays.common.item.christmas.music;

import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.MusicBoxBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.sound.christmas.MusicBoxSound;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SheetMusicItem extends ChristmasItem {
    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(16);

    public ChristmasMusic music;

    public SheetMusicItem(String itemId) {
        super(itemId, ITEM_PROPERTIES);

        this.christmasRarity = ChristmasRarity.RARE;
    }

    @Override
    public ActionResultType useOn(ItemUseContext itemUseContext) {
        World world = itemUseContext.getLevel();
        BlockPos blockpos = itemUseContext.getClickedPos();
        BlockState blockstate = world.getBlockState(blockpos);

        if (blockstate.is(ChristmasBlocks.MUSIC_BOX.get()) && !blockstate.getValue(MusicBoxBlock.HAS_SHEET_MUSIC)) {
            ItemStack itemstack = itemUseContext.getItemInHand();
            if (!world.isClientSide) {
                ((MusicBoxBlock) ChristmasBlocks.MUSIC_BOX.get()).setSheetMusic(world, blockpos, blockstate, itemstack);
                itemstack.shrink(1);
            }

            if (itemUseContext.getPlayer() != null) {
                TextComponent chatComponent = new TranslationTextComponent("block.happyholidays.music_box"
                        + ".now_playing", (new TranslationTextComponent(this.getDescriptionId() + ".desc")));
                chatComponent.withStyle(TextFormatting.AQUA);
                itemUseContext.getPlayer().displayClientMessage(chatComponent, true);
            }

            return ActionResultType.sidedSuccess(world.isClientSide);
        } else {
            return ActionResultType.PASS;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        textComponents.add(this.getDisplayName().withStyle(TextFormatting.GRAY));
    }

    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent getDisplayName() {
        return new TranslationTextComponent(this.getDescriptionId() + ".desc");
    }

    public ChristmasMusic getMusic() {
        return this.music;
    }

    public static MusicBoxSound createMusicBoxSound(ChristmasMusic music, BlockPos pos) {
        return new MusicBoxSound(ChristmasMusic.getSound(music), ChristmasMusic.getSoundDuration(music), pos);
    }
}
