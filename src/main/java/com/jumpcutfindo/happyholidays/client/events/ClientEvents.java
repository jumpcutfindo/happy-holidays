package com.jumpcutfindo.happyholidays.client.events;

import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.screen.guide.GuideScreen;
import com.jumpcutfindo.happyholidays.client.sound.christmas.MusicBoxSound;
import com.jumpcutfindo.happyholidays.client.sound.christmas.SantaSummonSound;
import com.jumpcutfindo.happyholidays.common.Holiday;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.MusicBoxBlockEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.happy.HappySantaEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.MusicBoxEvent;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaEvent;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.ChristmasMusic;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.server.resources.guide.GuideManager;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
    private static final Map<BlockPos, MusicBoxSound> christmasMusicMap = Maps.newHashMap();
    private static final Map<UUID, SantaSummonSound> happySantaSummonSoundMap = Maps.newHashMap();

    @SubscribeEvent
    public static void onOpenGuideBook(GuideBookEvent.Open openEvent) {
        ItemStack guideBook = openEvent.getGuideBook();
        if (guideBook.is(ChristmasItems.CHRISTMAS_GUIDE_BOOK.get())) {
            Minecraft.getInstance().setScreen(new GuideScreen(GuideManager.getGuide(Holiday.CHRISTMAS)));
        }
    }

    @SubscribeEvent
    public static void onPlayMusicBox(MusicBoxEvent.Play playEvent) {
        Level level = playEvent.getLevel();
        BlockPos blockPos = playEvent.getMusicBox().getBlockPos();
        ChristmasMusic christmasMusic = playEvent.getMusic();

        if (level.getBlockEntity(blockPos) instanceof MusicBoxBlockEntity) {
            MusicBoxSound musicSound = new MusicBoxSound(ChristmasMusic.getSound(christmasMusic), ChristmasMusic.getSoundDuration(christmasMusic), blockPos);
            Minecraft.getInstance().getSoundManager().play(musicSound);

            christmasMusicMap.put(blockPos, musicSound);
        }
    }

    @SubscribeEvent
    public static void onStopMusicBox(MusicBoxEvent.Stop stopEvent) {
        BlockPos blockPos = stopEvent.getMusicBox().getBlockPos();

        MusicBoxSound musicSound = christmasMusicMap.get(blockPos);
        if (musicSound != null) {
            musicSound.stopTrack();
            christmasMusicMap.remove(blockPos);
        }
    }

    @SubscribeEvent
    public static void onHappySantaSoundChange(SantaEvent.SoundChange soundEvent) {
        UUID santaUUID = soundEvent.getSantaEntity().getUUID();

        if (soundEvent.isPlaying()) {
            // Start playing sound
            if (happySantaSummonSoundMap.containsKey(santaUUID)) return;
            BlockPos santaPos = ((HappySantaEntity) soundEvent.getSantaEntity()).getSummoningPos();
            if (santaPos == null) santaPos = soundEvent.getSantaEntity().getOnPos();

            SantaSummonSound summonSound = new SantaSummonSound(santaPos);
            happySantaSummonSoundMap.put(santaUUID, summonSound);
            Minecraft.getInstance().getSoundManager().play(summonSound);
        } else {
            // Stop playing sound
            SantaSummonSound summonSound = happySantaSummonSoundMap.get(santaUUID);
            if (summonSound != null) {
                summonSound.stopTrack();
                happySantaSummonSoundMap.remove(santaUUID);
            }
        }
    }
}
