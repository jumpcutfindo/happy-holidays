package com.jumpcutfindo.happyholidays.common.entity.christmas.santa;

import java.util.List;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.happy.HappySantaEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;

public class SantaGifts {
    public static final String NAME_HAPPY_SANTA = "entity.happyholidays.happy_santa";
    public static final String NAME_ANGRY_SANTA = "entity.happyholidays.angry_santa";

    public static final String NAME_BASIC_GIFT = "item.happyholidays.santa.basic_gift";
    public static final String NAME_RARE_GIFT ="item.happyholidays.santa.rare_gift";
    public static final String NAME_LEGENDARY_GIFT ="item.happyholidays.santa.legendary_gift";

    public static final ResourceLocation BASIC_GIFT_LOOT_TABLE = new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/santa_basic_gifts");
    public static final ResourceLocation RARE_GIFT_LOOT_TABLE = new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/santa_rare_gifts");
    public static final ResourceLocation LEGENDARY_GIFT_LOOT_TABLE = new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/santa_legendary_gifts");

    public static ItemStack generateGift(SantaGiftType type, BaseSantaEntity santaEntity, ServerLevel level,
                                         LootContext lootContext) {
        ItemStack giftItem = ItemStack.EMPTY;
        List<ItemStack> items = Lists.newArrayList();

        switch (type) {
            case BASIC: {
                giftItem = ChristmasItems.BLUE_CHRISTMAS_GIFT_ITEM.get().getDefaultInstance();
                giftItem.setHoverName(new TranslatableComponent(NAME_BASIC_GIFT));

                LootTable lootTable = level.getServer().getLootTables().get(BASIC_GIFT_LOOT_TABLE);
                items = lootTable.getRandomItems(lootContext);

                for (ItemStack item : items) {
                    if (item.getItem() instanceof BlockItem && BlockTags.LOGS.contains(((BlockItem) item.getItem()).getBlock())) {
                        item.setCount(santaEntity.getRandom().nextInt(12 - 6) + 6);
                    }
                }
                break;
            }
            case RARE: {
                giftItem = ChristmasItems.GREEN_CHRISTMAS_GIFT_ITEM.get().getDefaultInstance();
                giftItem.setHoverName(new TranslatableComponent(NAME_RARE_GIFT));


                LootTable lootTable = level.getServer().getLootTables().get(RARE_GIFT_LOOT_TABLE);
                items = lootTable.getRandomItems(lootContext);
                break;
            }
            case LEGENDARY: {
                giftItem = ChristmasItems.GOLD_CHRISTMAS_GIFT_ITEM.get().getDefaultInstance();
                giftItem.setHoverName(new TranslatableComponent(NAME_LEGENDARY_GIFT));

                LootTable lootTable = level.getServer().getLootTables().get(LEGENDARY_GIFT_LOOT_TABLE);
                items = lootTable.getRandomItems(lootContext);
                break;
            }
        }

        CompoundTag giftItemsNBT = new CompoundTag();
        NonNullList<ItemStack> nonNullList = NonNullList.withSize(items.size(), ItemStack.EMPTY);
        for (int i = 0; i < items.size(); i++) nonNullList.set(i, items.get(i));

        ContainerHelper.saveAllItems(giftItemsNBT, nonNullList);

        String name = santaEntity instanceof HappySantaEntity ? NAME_HAPPY_SANTA : NAME_ANGRY_SANTA;

        CompoundTag giftTag = giftItem.getOrCreateTag();
        giftTag.put("Gifts", giftItemsNBT);
        giftTag.putString("WrappedBy", new TranslatableComponent(name).getString());

        return giftItem;
    }
}
