package com.jumpcutfindo.happyholidays.proxies;

import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.MusicBoxBlockEntity;
import com.jumpcutfindo.happyholidays.common.handlers.GuideHandler;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.ChristmasMusic;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.sound.christmas.MusicBoxSound;
import com.jumpcutfindo.happyholidays.common.sound.christmas.SantaSummonSound;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, value = Dist.CLIENT)
public class ClientProxy implements Proxy {
    private Map<BlockPos, MusicBoxSound> christmasMusicMap = Maps.newHashMap();
    private Map<UUID, SantaSummonSound> happySantaSummonSoundMap = Maps.newHashMap();

    public ClientProxy() {
    }

    @Override
    public void initClient() {

    }

    @Override
    public void openGuideGUI(ItemStack itemStack) {
        if (itemStack.is(ChristmasItems.CHRISTMAS_GUIDE_BOOK.get())) {
            Minecraft.getInstance().setScreen(new GuideScreen(GuideHandler.getGuide("christmas")));
        }
    }

    @Override
    public void playChristmasMusic(LevelAccessor level, BlockPos blockPos, ChristmasMusic christmasMusic) {
        if (level.getBlockEntity(blockPos) instanceof MusicBoxBlockEntity) {
            MusicBoxSound musicSound = new MusicBoxSound(ChristmasMusic.getSound(christmasMusic), ChristmasMusic.getSoundDuration(christmasMusic), blockPos);
            Minecraft.getInstance().getSoundManager().play(musicSound);

            christmasMusicMap.put(blockPos, musicSound);
        }
    }

    @Override
    public void stopChristmasMusic(LevelAccessor level, BlockPos blockPos) {
        MusicBoxSound musicSound = christmasMusicMap.get(blockPos);
        if (musicSound != null) {
            musicSound.stopTrack();
            christmasMusicMap.remove(blockPos);
        }
    }

    @Override
    public void playHappySantaSummoningSound(UUID santaUUID, BlockPos pos) {
        if (happySantaSummonSoundMap.containsKey(santaUUID)) return;

        SantaSummonSound summonSound = new SantaSummonSound(pos);
        happySantaSummonSoundMap.put(santaUUID, summonSound);
        Minecraft.getInstance().getSoundManager().play(summonSound);
    }

    @Override
    public void stopHappySantaSummoningSound(UUID santaUUID) {
        SantaSummonSound summonSound = happySantaSummonSoundMap.get(santaUUID);
        if (summonSound != null) {
            summonSound.stopTrack();
            happySantaSummonSoundMap.remove(santaUUID);
        }
    }
}
