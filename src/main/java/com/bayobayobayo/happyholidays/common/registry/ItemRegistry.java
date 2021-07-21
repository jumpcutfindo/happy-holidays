package com.bayobayobayo.happyholidays.common.registry;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigBlueBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigGoldBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigGreenBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigRedBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigSilverBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigYellowBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BlueBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.GoldBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.GreenBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.RedBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.SilverBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.YellowBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.head.CreeperHeadOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.head.SkeletonHeadOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.head.WitherSkeletonHeadOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.head.ZombieHeadOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.lights.RedChristmasLightBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.BlueTinselBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.GoldTinselBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.GreenTinselBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.RedTinselBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.SilverTinselBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.YellowTinselBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.gingerbread.GingerbreadBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.gingerbread.RawGingerbreadBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.gingerbread.SoggyGingerbreadBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.misc.ChristmasStarBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.presents.AdultPresentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.presents.BabyPresentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.presents.ElderPresentBlock;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;
import com.bayobayobayo.happyholidays.common.item.christmas.gingerbread.GingerbreadCookieItem;
import com.bayobayobayo.happyholidays.common.item.christmas.gingerbread.RawGingerbreadItem;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            HappyHolidaysMod.MOD_ID
    );

    public static final RegistryObject<ChristmasItem> RAW_GINGERBREAD =
            ITEMS.register(RawGingerbreadItem.ITEM_ID, RawGingerbreadItem::new);
    public static final RegistryObject<ChristmasItem> GINGERBREAD_COOKIE =
            ITEMS.register(GingerbreadCookieItem.ITEM_ID, GingerbreadCookieItem::new);


    public static final RegistryObject<BlockItem> BABY_PRESENT_BLOCK_ITEM =
            ITEMS.register(BabyPresentBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.BABY_PRESENT_BLOCK.get(),
                    BabyPresentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> ADULT_PRESENT_BLOCK_ITEM =
            ITEMS.register(AdultPresentBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.ADULT_PRESENT_BLOCK.get(),
                    AdultPresentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> ELDER_PRESENT_BLOCK_ITEM =
            ITEMS.register(ElderPresentBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.ELDER_PRESENT_BLOCK.get(),
                    ElderPresentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<BlockItem> RED_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(RedBallOrnamentBlock.BLOCK_ID,
                    () -> new BlockItem(BlockRegistry.RED_BALL_ORNAMENT_BLOCK.get(),
                    RedBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> BLUE_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BlueBallOrnamentBlock.BLOCK_ID,
                    () -> new BlockItem(BlockRegistry.BLUE_BALL_ORNAMENT_BLOCK.get(),
                    BlueBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> YELLOW_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(YellowBallOrnamentBlock.BLOCK_ID,
                    () -> new BlockItem(BlockRegistry.YELLOW_BALL_ORNAMENT_BLOCK.get(),
                    YellowBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> GREEN_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(GreenBallOrnamentBlock.BLOCK_ID,
                    () -> new BlockItem(BlockRegistry.GREEN_BALL_ORNAMENT_BLOCK.get(),
                    GreenBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> GOLD_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(GoldBallOrnamentBlock.BLOCK_ID,
                    () -> new BlockItem(BlockRegistry.GOLD_BALL_ORNAMENT_BLOCK.get(),
                    GoldBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> SILVER_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(SilverBallOrnamentBlock.BLOCK_ID,
                    () -> new BlockItem(BlockRegistry.SILVER_BALL_ORNAMENT_BLOCK.get(),
                    SilverBallOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<BlockItem> BIG_RED_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BigRedBallOrnamentBlock.BLOCK_ID,
                    () -> new BlockItem(BlockRegistry.BIG_RED_BALL_ORNAMENT_BLOCK.get(),
                            BigRedBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> BIG_BLUE_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BigBlueBallOrnamentBlock.BLOCK_ID,
                    () -> new BlockItem(BlockRegistry.BIG_BLUE_BALL_ORNAMENT_BLOCK.get(),
                            BigBlueBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> BIG_YELLOW_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BigYellowBallOrnamentBlock.BLOCK_ID,
                    () -> new BlockItem(BlockRegistry.BIG_YELLOW_BALL_ORNAMENT_BLOCK.get(),
                            BigYellowBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> BIG_GREEN_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BigGreenBallOrnamentBlock.BLOCK_ID,
                    () -> new BlockItem(BlockRegistry.BIG_GREEN_BALL_ORNAMENT_BLOCK.get(),
                            BigGreenBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> BIG_GOLD_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BigGoldBallOrnamentBlock.BLOCK_ID,
                    () -> new BlockItem(BlockRegistry.BIG_GOLD_BALL_ORNAMENT_BLOCK.get(),
                            BigGoldBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> BIG_SILVER_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BigSilverBallOrnamentBlock.BLOCK_ID,
                    () -> new BlockItem(BlockRegistry.BIG_SILVER_BALL_ORNAMENT_BLOCK.get(),
                            BigSilverBallOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<BlockItem> CREEPER_HEAD_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(CreeperHeadOrnamentBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.CREEPER_HEAD_ORNAMENT_BLOCK.get(),
                    CreeperHeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> SKELETON_HEAD_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(SkeletonHeadOrnamentBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.SKELETON_HEAD_ORNAMENT_BLOCK.get(),
                    SkeletonHeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> WITHER_SKELETON_HEAD_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(WitherSkeletonHeadOrnamentBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.WITHER_SKELETON_HEAD_ORNAMENT_BLOCK.get(),
                    WitherSkeletonHeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> ZOMBIE_HEAD_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(ZombieHeadOrnamentBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.ZOMBIE_HEAD_ORNAMENT_BLOCK.get(),
                    ZombieHeadOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<BlockItem> RED_TINSEL_BLOCK_ITEM =
            ITEMS.register(RedTinselBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.RED_TINSEL_BLOCK.get(),
                    RedTinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> BLUE_TINSEL_BLOCK_ITEM =
            ITEMS.register(BlueTinselBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.BLUE_TINSEL_BLOCK.get(),
                    BlueTinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> YELLOW_TINSEL_BLOCK_ITEM =
            ITEMS.register(YellowTinselBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.YELLOW_TINSEL_BLOCK.get(),
                    YellowTinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> GREEN_TINSEL_BLOCK_ITEM =
            ITEMS.register(GreenTinselBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.GREEN_TINSEL_BLOCK.get(),
                    GreenTinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> GOLD_TINSEL_BLOCK_ITEM =
            ITEMS.register(GoldTinselBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.GOLD_TINSEL_BLOCK.get(),
                    GoldTinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> SILVER_TINSEL_BLOCK_ITEM =
            ITEMS.register(SilverTinselBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.SILVER_TINSEL_BLOCK.get(),
                    SilverTinselBlock.ITEM_PROPERTIES));

    public static final RegistryObject<BlockItem> RED_CHRISTMAS_LIGHT_BLOCK_ITEM =
            ITEMS.register(RedChristmasLightBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.RED_CHRISTMAS_LIGHT_BLOCK.get(),
                    RedChristmasLightBlock.ITEM_PROPERTIES));

    public static final RegistryObject<BlockItem> RAW_GINGERBREAD_BLOCK_ITEM =
            ITEMS.register(RawGingerbreadBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.RAW_GINGERBREAD_BLOCK.get(),
                            RawGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<BlockItem> GINGERBREAD_BLOCK_ITEM =
            ITEMS.register(GingerbreadBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.GINGERBREAD_BLOCK.get(),
                    GingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<BlockItem> SOGGY_GINGERBREAD_BLOCK_ITEM =
            ITEMS.register(SoggyGingerbreadBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.SOGGY_GINGERBREAD_BLOCK.get(),
                    SoggyGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<BlockItem> CHRISTMAS_STAR_BLOCK_ITEM =
            ITEMS.register(ChristmasStarBlock.BLOCK_ID, () -> new BlockItem(BlockRegistry.CHRISTMAS_STAR_BLOCK.get(),
                    ChristmasStarBlock.ITEM_PROPERTIES));
}
