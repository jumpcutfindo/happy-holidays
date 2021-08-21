package com.jumpcutfindo.happyholidays.common.entity.christmas.santa;

import java.util.List;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.common.registry.ItemRegistry;

import net.minecraft.block.AnvilBlock;
import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;

public class SantaGifts {
    public static final String NAME_HAPPY_SANTA = "entity.happyholidays.happy_santa";

    public static final String NAME_BASIC_GIFT = "item.happyholidays.santa.basic_gift";
    public static final String NAME_RARE_GIFT ="item.happyholidays.santa.rare_gift";
    public static final String NAME_LEGENDARY_GIFT ="item.happyholidays.santa.legendary_gift";

    public static final ResourceLocation BASIC_GIFT_LOOT_TABLE = new ResourceLocation("happyholidays:entities"
            + "/santa_basic_gift");
    public static final ResourceLocation RARE_GIFT_LOOT_TABLE = new ResourceLocation("happyholidays:entities"
            + "/santa_rare_gift");
    public static final ResourceLocation LEGENDARY_GIFT_LOOT_TABLE = new ResourceLocation("happyholidays:entities"
            + "/santa_legendary_gift");

    public static ItemStack generateGift(SantaGiftType type, BaseSantaEntity santaEntity, ServerWorld level,
                                         LootContext lootContext) {
        ItemStack giftItem = ItemStack.EMPTY;
        List<ItemStack> items = Lists.newArrayList();

        switch (type) {
            case BASIC: {
                giftItem = ItemRegistry.BLUE_CHRISTMAS_GIFT_ITEM.get().getDefaultInstance();
                giftItem.setHoverName(new TranslationTextComponent(NAME_BASIC_GIFT));

                LootTable lootTable = level.getServer().getLootTables().get(BASIC_GIFT_LOOT_TABLE);
                items = lootTable.getRandomItems(lootContext);

                for (ItemStack item : items) {
                    if (item.getItem() instanceof BlockItem && ((BlockItem) item.getItem()).getBlock().is(BlockTags.LOGS)) {
                        item.setCount(santaEntity.getRandom().nextInt(12 - 6) + 6);
                    }
                }
                break;
            }
            case RARE: {
                giftItem = ItemRegistry.GREEN_CHRISTMAS_GIFT_ITEM.get().getDefaultInstance();
                giftItem.setHoverName(new TranslationTextComponent(NAME_RARE_GIFT));


                LootTable lootTable = level.getServer().getLootTables().get(RARE_GIFT_LOOT_TABLE);
                items = lootTable.getRandomItems(lootContext);
                break;
            }
            case LEGENDARY: {
                giftItem = ItemRegistry.GOLD_CHRISTMAS_GIFT_ITEM.get().getDefaultInstance();
                giftItem.setHoverName(new TranslationTextComponent(NAME_LEGENDARY_GIFT));

                LootTable lootTable = level.getServer().getLootTables().get(LEGENDARY_GIFT_LOOT_TABLE);
                items = lootTable.getRandomItems(lootContext);
                break;
            }
        }

        CompoundNBT giftItemsNBT = new CompoundNBT();
        NonNullList<ItemStack> nonNullList = NonNullList.withSize(items.size(), ItemStack.EMPTY);
        for (int i = 0; i < items.size(); i++) nonNullList.set(i, items.get(i));

        ItemStackHelper.saveAllItems(giftItemsNBT, nonNullList);

        CompoundNBT giftTag = giftItem.getOrCreateTag();
        giftTag.put("Gifts", giftItemsNBT);
        giftTag.putString("WrappedBy", new TranslationTextComponent(NAME_HAPPY_SANTA).getString());

        return giftItem;
    }
}
