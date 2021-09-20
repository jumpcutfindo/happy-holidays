package com.jumpcutfindo.happyholidays.common.item.christmas.music;

import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.MusicBoxBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.sound.christmas.MusicBoxSound;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.BaseComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SheetMusicItem extends ChristmasItem {
    public static final String ANGELS_ON_HIGH_ID = "christmas_sheet_music_angels_on_high";
    public static final String CAROL_OF_BELLS_ID = "christmas_sheet_music_carol_of_the_bells";
    public static final String DECK_THE_HALLS_ID = "christmas_sheet_music_deck_the_halls";
    public static final String FROSTY_THE_SNOWMAN_ID = "christmas_sheet_music_frosty_the_snowman";
    public static final String GOD_REST_GENTLEMEN_ID = "christmas_sheet_music_god_rest_gentlemen";
    public static final String HERE_COMES_SANTA_ID = "christmas_sheet_music_here_comes_santa";
    public static final String JINGLE_BELL_ROCK_ID = "christmas_sheet_music_jingle_bell_rock";
    public static final String JINGLE_BELLS_ID = "christmas_sheet_music_jingle_bells";
    public static final String JOY_TO_THE_WORLD_ID = "christmas_sheet_music_joy_to_the_world";
    public static final String RUDOLPH_ID = "christmas_sheet_music_rudolph";
    public static final String SILENT_NIGHT_ID = "christmas_sheet_music_silent_night";
    public static final String SLEIGH_RIDE_ID = "christmas_sheet_music_sleigh_ride";
    public static final String THE_FIRST_NOEL_ID = "christmas_sheet_music_the_first_noel";
    public static final String WE_THREE_KINGS_ID = "christmas_sheet_music_we_three_kings";
    public static final String WE_WISH_YOU_ID = "christmas_sheet_music_we_wish_you";
    public static final String WHITE_CHRISTMAS_ID = "christmas_sheet_music_white_christmas";

    public static final String ANGELS_ON_HIGH_SOUND_ID = "item.christmas_music_angels_on_high";
    public static final String CAROL_OF_BELLS_SOUND_ID = "item.christmas_music_carol_of_the_bells";
    public static final String DECK_THE_HALLS_SOUND_ID = "item.christmas_music_deck_the_halls";
    public static final String FROSTY_THE_SNOWMAN_SOUND_ID = "item.christmas_music_frosty_the_snowman";
    public static final String GOD_REST_GENTLEMEN_SOUND_ID = "item.christmas_music_god_rest_gentlemen";
    public static final String HERE_COMES_SANTA_SOUND_ID = "item.christmas_music_here_comes_santa";
    public static final String JINGLE_BELL_ROCK_SOUND_ID = "item.christmas_music_jingle_bell_rock";
    public static final String JINGLE_BELLS_SOUND_ID = "item.christmas_music_jingle_bells";
    public static final String JOY_TO_THE_WORLD_SOUND_ID = "item.christmas_music_joy_to_the_world";
    public static final String RUDOLPH_SOUND_ID = "item.christmas_music_rudolph";
    public static final String SILENT_NIGHT_SOUND_ID = "item.christmas_music_silent_night";
    public static final String SLEIGH_RIDE_SOUND_ID = "item.christmas_music_sleigh_ride";
    public static final String THE_FIRST_NOEL_SOUND_ID = "item.christmas_music_the_first_noel";
    public static final String WE_THREE_KINGS_SOUND_ID = "item.christmas_music_we_three_kings";
    public static final String WE_WISH_YOU_SOUND_ID = "item.christmas_music_we_wish_you";
    public static final String WHITE_CHRISTMAS_SOUND_ID = "item.christmas_music_white_christmas";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(16);

    public ChristmasMusic music;

    public SheetMusicItem() {
        super(ITEM_PROPERTIES);

        this.setChristmasRarity(ChristmasRarity.RARE);
    }

    @Override
    public InteractionResult useOn(UseOnContext itemUseContext) {
        Level world = itemUseContext.getLevel();
        BlockPos blockpos = itemUseContext.getClickedPos();
        BlockState blockstate = world.getBlockState(blockpos);

        if (blockstate.is(ChristmasBlocks.MUSIC_BOX.get()) && !blockstate.getValue(MusicBoxBlock.HAS_SHEET_MUSIC)) {
            ItemStack itemstack = itemUseContext.getItemInHand();
            if (!world.isClientSide) {
                ((MusicBoxBlock) ChristmasBlocks.MUSIC_BOX.get()).setSheetMusic(world, blockpos, blockstate, itemstack);
                itemstack.shrink(1);
            }

            if (itemUseContext.getPlayer() != null) {
                BaseComponent chatComponent = new TranslatableComponent("block.happyholidays.music_box"
                        + ".now_playing", (new TranslatableComponent(this.getDescriptionId() + ".desc")));
                chatComponent.withStyle(ChatFormatting.AQUA);
                itemUseContext.getPlayer().displayClientMessage(chatComponent, true);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
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
