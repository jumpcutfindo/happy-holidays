package com.jumpcutfindo.happyholidays.common.entity.christmas.elf;

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

public class SantaElfRewards {
    private static final ResourceLocation SANTA_ELF_REQUEST_LOOT_TABLE = new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/santa_elf_request_rewards");

    public static final double REQUEST_REWARD_MULTIPLIER_STEP = 0.1d;
    private static final double REQUEST_ORNAMENT_DROP_BASE_CHANCE = 0.2d;
    public static final int COMPLETION_EXPERIENCE = 36;

    public static ExperienceOrb generateCompletionXP(Level level, Vec3 position) {
        return new ExperienceOrb(level, position.x, position.y, position.z, COMPLETION_EXPERIENCE);
    }

    public static List<ItemStack> generateRewards(SantaElfEntity santaElfEntity, LootContext lootContext) {
        Level level = santaElfEntity.level;
        double modifier = ChristmasRewards.computeModifier(level, santaElfEntity.position(), REQUEST_REWARD_MULTIPLIER_STEP);

        // Generate loot
        LootTable lootTable = level.getServer().getLootTables().get(SANTA_ELF_REQUEST_LOOT_TABLE);
        List<ItemStack> loot = generateLoot(lootTable, lootContext, modifier, santaElfEntity.getRandom());

        // Generate ornament
        ItemStack elfOrnament = generateOrnament(modifier, santaElfEntity.getRandom());

        loot.add(elfOrnament);

        return loot;
    }

    private static List<ItemStack> generateLoot(LootTable lootTable, LootContext ctx, double modifier, Random random) {
        // FIXME: This is a temporary solution to the issue where items listed in tags inside loot tables have
        //  incorrect counts (see MC-212671)
        List<ItemStack> loot = lootTable.getRandomItems(ctx);
        loot.forEach(itemStack -> {
            if (ChristmasItems.isBasicOrnamentItem(itemStack)) {
                itemStack.setCount((random.nextInt(36 - 12) + 1) + 12);
            } else if (ChristmasItems.isRareOrnamentItem(itemStack)) {
                itemStack.setCount((random.nextInt(8 - 4) + 1) + 4);
            } else if (ItemStack.isSame(itemStack, ChristmasItems.PRESENT_SCRAPS.get().getDefaultInstance())) {
                itemStack.setCount((random.nextInt(18 - 12) + 1) + 12);
            }

            // Apply modifier
            if (!ChristmasRewards.isTool(itemStack)) itemStack.setCount((int) (itemStack.getCount() * modifier));
        });

        return loot;
    }

    private static ItemStack generateOrnament(double modifier, Random random) {
        // Drop ornament block
        double ornamentDropChance = REQUEST_ORNAMENT_DROP_BASE_CHANCE * modifier;
        if (random.nextDouble() <= ornamentDropChance) {
            return ChristmasItems.SANTA_ELF_ORNAMENT.get().getDefaultInstance();
        } else {
            return ItemStack.EMPTY;
        }
    }
}
