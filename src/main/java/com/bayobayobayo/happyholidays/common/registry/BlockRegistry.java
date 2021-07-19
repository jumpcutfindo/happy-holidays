package com.bayobayobayo.happyholidays.common.registry;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.block.christmas.ChristmasBlock;
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

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS,
            HappyHolidaysMod.MOD_ID
    );

    public static final RegistryObject<ChristmasBlock> BABY_PRESENT_BLOCK =
            BLOCKS.register(BabyPresentBlock.BLOCK_ID, BabyPresentBlock::new);
    public static final RegistryObject<ChristmasBlock> ADULT_PRESENT_BLOCK =
            BLOCKS.register(AdultPresentBlock.BLOCK_ID, AdultPresentBlock::new);
    public static final RegistryObject<ChristmasBlock> ELDER_PRESENT_BLOCK =
            BLOCKS.register(ElderPresentBlock.BLOCK_ID, ElderPresentBlock::new);

    public static final RegistryObject<ChristmasBlock> RED_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(RedBallOrnamentBlock.BLOCK_ID, RedBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BLUE_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BlueBallOrnamentBlock.BLOCK_ID, BlueBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> YELLOW_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(YellowBallOrnamentBlock.BLOCK_ID, YellowBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> GREEN_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(GreenBallOrnamentBlock.BLOCK_ID, GreenBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> GOLD_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(GoldBallOrnamentBlock.BLOCK_ID, GoldBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> SILVER_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(SilverBallOrnamentBlock.BLOCK_ID, SilverBallOrnamentBlock::new);


    public static final RegistryObject<ChristmasBlock> BIG_RED_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BigRedBallOrnamentBlock.BLOCK_ID, BigRedBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_BLUE_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BigBlueBallOrnamentBlock.BLOCK_ID, BigBlueBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_YELLOW_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BigYellowBallOrnamentBlock.BLOCK_ID, BigYellowBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_GREEN_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BigGreenBallOrnamentBlock.BLOCK_ID, BigGreenBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_GOLD_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BigGoldBallOrnamentBlock.BLOCK_ID, BigGoldBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_SILVER_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BigSilverBallOrnamentBlock.BLOCK_ID, BigSilverBallOrnamentBlock::new);

    public static final RegistryObject<ChristmasBlock> RED_TINSEL_BLOCK =
            BLOCKS.register(RedTinselBlock.BLOCK_ID, RedTinselBlock::new);
    public static final RegistryObject<ChristmasBlock> BLUE_TINSEL_BLOCK =
            BLOCKS.register(BlueTinselBlock.BLOCK_ID, BlueTinselBlock::new);
    public static final RegistryObject<ChristmasBlock> YELLOW_TINSEL_BLOCK =
            BLOCKS.register(YellowTinselBlock.BLOCK_ID, YellowTinselBlock::new);
    public static final RegistryObject<ChristmasBlock> GREEN_TINSEL_BLOCK =
            BLOCKS.register(GreenTinselBlock.BLOCK_ID, GreenTinselBlock::new);
    public static final RegistryObject<ChristmasBlock> GOLD_TINSEL_BLOCK =
            BLOCKS.register(GoldTinselBlock.BLOCK_ID, GoldTinselBlock::new);
    public static final RegistryObject<ChristmasBlock> SILVER_TINSEL_BLOCK =
            BLOCKS.register(SilverTinselBlock.BLOCK_ID, SilverTinselBlock::new);

    public static final RegistryObject<ChristmasBlock> RAW_GINGERBREAD_BLOCK =
            BLOCKS.register(RawGingerbreadBlock.BLOCK_ID, RawGingerbreadBlock::new);
    public static final RegistryObject<ChristmasBlock> GINGERBREAD_BLOCK =
            BLOCKS.register(GingerbreadBlock.BLOCK_ID, GingerbreadBlock::new);
    public static final RegistryObject<ChristmasBlock> SOGGY_GINGERBREAD_BLOCK =
            BLOCKS.register(SoggyGingerbreadBlock.BLOCK_ID, SoggyGingerbreadBlock::new);

    public static final RegistryObject<ChristmasBlock> CHRISTMAS_STAR_BLOCK =
            BLOCKS.register(ChristmasStarBlock.BLOCK_ID, ChristmasStarBlock::new);
}
