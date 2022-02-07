package com.jumpcutfindo.happyholidays.common.entity.christmas.grinch;

import java.util.List;
import java.util.Random;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.ChristmasRewards;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;

public class GrinchRewards {
    private static final ResourceLocation GRINCH_APPEASEMENT_LOOT_TABLE = new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/grinch_appeasement");

    public static final double APPEASEMENT_REWARD_MODIFIER_STEP = 0.1d;
    public static final double APPEASEMENT_ORNAMENT_DROP_BASE_CHANCE = 0.2d;
    public static final int APPEASEMENT_EXPERIENCE = 50;

    public static ExperienceOrb generateAppeasementXP(Level level, Vec3 pos) {
        return new ExperienceOrb(level, pos.x, pos.y, pos.z, APPEASEMENT_EXPERIENCE);
    }

    public static List<ItemStack> generateRewards(GrinchEntity grinchEntity, LootContext lootContext) {
        Level level = grinchEntity.level;
        double modifier = ChristmasRewards.computeModifier(grinchEntity.level, grinchEntity.position(), APPEASEMENT_REWARD_MODIFIER_STEP);

        // Generate present scraps (from when Grinch broke the presents)
        ItemStack presentScraps = computePresentScraps(grinchEntity.getPresentsBrokenCount());

        // Generate and modify rewards
        LootTable lootTable = level.getServer().getLootTables().get(GRINCH_APPEASEMENT_LOOT_TABLE);
        List<ItemStack> loot = generateLoot(lootTable, lootContext, modifier, grinchEntity.getRandom());

        // Generate legendary ornament
        ItemStack legendaryOrnament = generateOrnament(modifier, grinchEntity.getRandom());

        loot.add(presentScraps);
        loot.add(legendaryOrnament);

        return loot;
    }

    private static ItemStack computePresentScraps(int[] presentsBrokenCount) {
        ItemStack scraps = ChristmasItems.PRESENT_SCRAPS.get().getDefaultInstance();
        scraps.setCount(presentsBrokenCount[0] + presentsBrokenCount[1] * 2 + presentsBrokenCount[2] * 3);

        return scraps;
    }

    private static List<ItemStack> generateLoot(LootTable lootTable, LootContext ctx, double modifier, Random random) {
        List<ItemStack> loot = lootTable.getRandomItems(ctx);
        loot.forEach(itemStack -> {
            // FIXME: This is a temporary solution to the issue where items listed in tags inside loot tables have
            //  incorrect counts (see MC-212671)

            if (ChristmasItems.isBasicOrnamentItem(itemStack)) {
                itemStack.setCount((random.nextInt(8 - 2) + 1) + 2);
            } else if (ChristmasItems.isRareOrnamentItem(itemStack)) {
                itemStack.setCount((random.nextInt(4 - 2) + 1) + 2);
            } else if (ItemStack.isSame(itemStack, ChristmasItems.PRESENT_SCRAPS.get().getDefaultInstance())) {
                itemStack.setCount((random.nextInt(4 - 2) + 1) + 2);
            } else if (ChristmasItems.isFoodItem(itemStack)) {
                if (ChristmasItems.isLargeFoodItem(itemStack)) {
                    itemStack.setCount((random.nextInt(4 - 2) + 1) + 2);
                } else {
                    itemStack.setCount((random.nextInt(6 - 2) + 1) + 2);
                }
            }

            if (!ChristmasRewards.isTool(itemStack)) itemStack.setCount((int) (itemStack.getCount() * modifier));
        });

        return loot;
    }

    private static ItemStack generateOrnament(double modifier, Random random) {
        double ornamentDropChance = APPEASEMENT_ORNAMENT_DROP_BASE_CHANCE * modifier;
        if (random.nextDouble() <= ornamentDropChance) {
            return ChristmasItems.GRINCH_ORNAMENT.get().getDefaultInstance();
        }

        return ItemStack.EMPTY;
    }
}
