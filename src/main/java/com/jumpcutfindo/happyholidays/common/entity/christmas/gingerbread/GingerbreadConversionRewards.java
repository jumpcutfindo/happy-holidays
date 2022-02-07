package com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread;

import java.util.List;
import java.util.Random;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.ChristmasRewards;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;

public class GingerbreadConversionRewards {
    private static final ResourceLocation GINGERBREAD_CONVERSION_LOOT_TABLE = new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/gingerbread_conversion");

    public static final double CONVERSION_REWARD_MODIFIER_STEP = 0.1d;
    private static final double CONVERSION_ORNAMENT_DROP_BASE_CHANCE = 0.01D;

    public static List<ItemStack> generateRewards(GingerbreadPersonEntity gingerbreadPersonEntity, LootContext lootContext) {
        Level level = gingerbreadPersonEntity.level;
        double modifier = ChristmasRewards.computeModifier(level, gingerbreadPersonEntity.position(), CONVERSION_REWARD_MODIFIER_STEP);

        // Generate loot
        LootTable lootTable = level.getServer().getLootTables().get(GINGERBREAD_CONVERSION_LOOT_TABLE);
        List<ItemStack> loot = generateLoot(lootTable, lootContext, modifier, gingerbreadPersonEntity.getRandom());

        // Generate ornament
        ItemStack gingerbreadOrnament = generateOrnament(modifier, gingerbreadPersonEntity.getRandom());

        loot.add(gingerbreadOrnament);

        return loot;
    }

    private static List<ItemStack> generateLoot(LootTable lootTable, LootContext ctx, double modifier, Random random) {
        // FIXME: This is a temporary solution to the issue where items listed in tags inside loot tables have
        //  incorrect counts (see MC-212671)
        List<ItemStack> loot = lootTable.getRandomItems(ctx);
        loot.forEach(itemStack -> {
            if (ChristmasItems.isBasicOrnamentItem(itemStack)) {
                itemStack.setCount((random.nextInt(2 - 1) + 1) + 1);
            }

            itemStack.setCount((int) (itemStack.getCount() * modifier));

            // Apply modifier
            if (!ChristmasRewards.isTool(itemStack)) itemStack.setCount((int) (itemStack.getCount() * modifier));
        });

        return loot;
    }


    private static ItemStack generateOrnament(double modifier, Random random) {
        // Drop ornament block
        double ornamentDropChance = CONVERSION_ORNAMENT_DROP_BASE_CHANCE * modifier;
        if (random.nextDouble() <= ornamentDropChance) {
            return ChristmasItems.GINGERBREAD_MAN_ORNAMENT.get().getDefaultInstance();
        } else {
            return ItemStack.EMPTY;
        }
    }
}
