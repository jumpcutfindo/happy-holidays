package com.bayobayobayo.happyholidays.common.item.christmas.music;

import java.util.List;

import javax.annotation.Nullable;

import com.bayobayobayo.happyholidays.common.block.christmas.misc.MusicBoxBlock;
import com.bayobayobayo.happyholidays.common.handlers.modules.ModuleHandler;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasRarity;
import com.bayobayobayo.happyholidays.common.registry.BlockRegistry;
import com.bayobayobayo.happyholidays.common.sound.christmas.MusicBoxSound;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.tileentity.JukeboxTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SheetMusicItem extends ChristmasItem {
    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP)
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

        if (blockstate.is(BlockRegistry.MUSIC_BOX_BLOCK.get()) && !blockstate.getValue(MusicBoxBlock.HAS_SHEET_MUSIC)) {
            ItemStack itemstack = itemUseContext.getItemInHand();
            if (!world.isClientSide) {
                ((MusicBoxBlock) BlockRegistry.MUSIC_BOX_BLOCK.get()).setSheetMusic(world, blockpos, blockstate, itemstack);
                itemstack.shrink(1);
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
