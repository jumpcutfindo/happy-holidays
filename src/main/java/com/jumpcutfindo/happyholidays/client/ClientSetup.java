package com.jumpcutfindo.happyholidays.client;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.entity.model.outfits.SantaOutfitRenderer;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.alphabets.AlphabetBlockColor;
import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.OutfitItem;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumBlueParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumGoldParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumGreenParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumRedParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumSilverParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumYellowParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.santa.SantaGreenSpawnParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.santa.SantaRedSpawnParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.santa.SantaSpawnParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallBlueParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallGoldParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallGreenParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallRedParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallSilverParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallYellowParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.star.ChristmasStarParticle;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasParticles;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public void setupClient(FMLClientSetupEvent event) {
        HappyHolidaysMod.PROXY.initClient();
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerOutfitRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(OutfitItem.class, new SantaOutfitRenderer());
    }

    @SubscribeEvent
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_RED_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasMediumRedParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_BLUE_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasMediumBlueParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_YELLOW_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasMediumYellowParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_GREEN_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasMediumGreenParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_GOLD_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasMediumGoldParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_SILVER_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasMediumSilverParticle.COLOR));

        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_RED_PARTICLE.get(),
                (sprites) -> new ChristmasSmallParticle.Factory(sprites, ChristmasSmallRedParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_BLUE_PARTICLE.get(),
                (sprites) -> new ChristmasSmallParticle.Factory(sprites, ChristmasSmallBlueParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_YELLOW_PARTICLE.get(),
                (sprites) -> new ChristmasSmallParticle.Factory(sprites, ChristmasSmallYellowParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_GREEN_PARTICLE.get(),
                (sprites) -> new ChristmasSmallParticle.Factory(sprites, ChristmasSmallGreenParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_GOLD_PARTICLE.get(),
                (sprites) -> new ChristmasSmallParticle.Factory(sprites, ChristmasSmallGoldParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_SILVER_PARTICLE.get(),
                (sprites) -> new ChristmasSmallParticle.Factory(sprites, ChristmasSmallSilverParticle.COLOR));

        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SANTA_RED_SPAWN_PARTICLE.get(),
                (sprites) -> new SantaSpawnParticle.Factory(sprites, SantaRedSpawnParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SANTA_GREEN_SPAWN_PARTICLE.get(),
                (sprites) -> new SantaSpawnParticle.Factory(sprites, SantaGreenSpawnParticle.COLOR));

        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_STAR_PARTICLE.get(),
                ChristmasStarParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerColors(ColorHandlerEvent.Block event) {
        AlphabetBlockColor alphabetBlockColor = new AlphabetBlockColor();

        event.getBlockColors().register(alphabetBlockColor,
                ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_A.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_B.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_C.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_D.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_E.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_F.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_G.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_H.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_I.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_J.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_K.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_L.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_M.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_N.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_O.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_P.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_Q.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_R.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_S.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_T.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_U.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_V.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_W.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_X.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_Y.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_Z.get()
        );
    }
}
