package com.jumpcutfindo.happyholidays.common.item.christmas;

import java.util.List;
import java.util.UUID;

import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.SwaggerStickEvent;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.MinecraftForge;

public class SwaggerStickItem extends ChristmasItem {
    public static Item.Properties ITEM_PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .tab(HappyHolidaysTabs.CHRISTMAS_GROUP);

    public SwaggerStickItem() {
        super(ITEM_PROPERTIES, ChristmasRarity.LEGENDARY);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int ticks, boolean p_41408_) {
        super.inventoryTick(itemStack, level, entity, ticks, p_41408_);

        if (level.getGameTime() % 100L == 0 && !level.isClientSide() && entity instanceof ServerPlayer player && isHoldingStick(player)) {
            AABB boundingArea = new AABB(player.blockPosition()).inflate(20.0D);
            final UUID playerUUID = player.getUUID();
            List<NutcrackerEntity> nutcrackersAround = level.getEntitiesOfClass(NutcrackerEntity.class, boundingArea, (nutcracker) -> {
                return nutcracker.isTame() && nutcracker.getOwnerUUID() != null && nutcracker.getOwnerUUID().equals(playerUUID);
            });

            if (nutcrackersAround.size() > 0) {
                MinecraftForge.EVENT_BUS.post(new SwaggerStickEvent.Held(player));
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        return InteractionResultHolder.success(p_41433_.getItemInHand(p_41434_));
    }

    private static boolean isHoldingStick(Player player) {
        return player.getMainHandItem().is(ChristmasItems.SWAGGER_STICK.get()) || player.getOffhandItem().is(ChristmasItems.SWAGGER_STICK.get());
    }
}
