package com.jumpcutfindo.happyholidays.client;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.renderer.outfit.*;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.AlphabetBlockColor;
import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.subs.*;
import com.jumpcutfindo.happyholidays.client.particle.christmas.ChristmasParticle;
import com.jumpcutfindo.happyholidays.client.particle.christmas.ChristmasParticleColor;
import com.jumpcutfindo.happyholidays.client.particle.christmas.ChristmasStarParticle;
import com.jumpcutfindo.happyholidays.client.particle.christmas.SantaSpawnParticle;
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
        GeoArmorRenderer.registerArmorRenderer(SantaElfOutfitItem.class, new SantaElfOutfitRenderer());
        GeoArmorRenderer.registerArmorRenderer(SantaOutfitItem.class, new SantaOutfitRenderer());
        GeoArmorRenderer.registerArmorRenderer(SnowmanOutfitItem.class, new SnowmanOutfitRenderer());
        GeoArmorRenderer.registerArmorRenderer(CandyCaneOutfitItem.class, new CandyCaneOutfitRenderer());
        GeoArmorRenderer.registerArmorRenderer(ReindeerOutfitItem.class, new ReindeerOutfitRenderer());
        GeoArmorRenderer.registerArmorRenderer(NutcrackerOutfitItem.class, new NutcrackerOutfitRenderer());
        GeoArmorRenderer.registerArmorRenderer(GingerbreadOutfitItem.class, new GingerbreadOutfitRenderer());
    }

    @SubscribeEvent
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_RED.get(),
                (sprites) -> new ChristmasParticle.Factory(sprites, ChristmasParticleColor.RED.getColor()));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_BLUE.get(),
                (sprites) -> new ChristmasParticle.Factory(sprites, ChristmasParticleColor.BLUE.getColor()));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_YELLOW.get(),
                (sprites) -> new ChristmasParticle.Factory(sprites, ChristmasParticleColor.YELLOW.getColor()));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_GREEN.get(),
                (sprites) -> new ChristmasParticle.Factory(sprites, ChristmasParticleColor.GREEN.getColor()));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_GOLD.get(),
                (sprites) -> new ChristmasParticle.Factory(sprites, ChristmasParticleColor.GOLD.getColor()));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles._PARTICLE.get(),
                (sprites) -> new ChristmasParticle.Factory(sprites, ChristmasParticleColor.SILVER.getColor()));

        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_RED.get(),
                (sprites) -> new ChristmasParticle.Factory(sprites, ChristmasParticleColor.RED.getColor()));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_BLUE.get(),
                (sprites) -> new ChristmasParticle.Factory(sprites, ChristmasParticleColor.BLUE.getColor()));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_YELLOW.get(),
                (sprites) -> new ChristmasParticle.Factory(sprites, ChristmasParticleColor.YELLOW.getColor()));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_GREEN.get(),
                (sprites) -> new ChristmasParticle.Factory(sprites, ChristmasParticleColor.GREEN.getColor()));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_GOLD.get(),
                (sprites) -> new ChristmasParticle.Factory(sprites, ChristmasParticleColor.GOLD.getColor()));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_SILVER.get(),
                (sprites) -> new ChristmasParticle.Factory(sprites, ChristmasParticleColor.SILVER.getColor()));

        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SANTA_RED.get(),
                (sprites) -> new SantaSpawnParticle.Factory(sprites, ChristmasParticleColor.RED.getColor()));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SANTA_GREEN.get(),
                (sprites) -> new SantaSpawnParticle.Factory(sprites, ChristmasParticleColor.GREEN.getColor()));

        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_STAR.get(),
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
