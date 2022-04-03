package com.jumpcutfindo.happyholidays.handlers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceProvider;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerInventory;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityAttachHandler {
    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            NaughtyNiceProvider provider = new NaughtyNiceProvider();
            event.addCapability(new ResourceLocation(HappyHolidaysMod.MOD_ID, "naughty_nice_meter"), provider);
        }

        if (event.getObject() instanceof NutcrackerEntity) {
            NutcrackerInventory nutcrackerInventory = new NutcrackerInventory();
            LazyOptional<IItemHandler> optionalInventory = LazyOptional.of(() -> nutcrackerInventory);

            ICapabilityProvider provider = new ICapabilitySerializable() {
                @Override
                public Tag serializeNBT() {
                    return nutcrackerInventory.serializeNBT();
                }

                @Override
                public void deserializeNBT(Tag nbt) {
                    nutcrackerInventory.deserializeNBT((CompoundTag) nbt);
                }

                @NotNull
                @Override
                public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                    if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return optionalInventory.cast();
                    return LazyOptional.empty();
                }
            };

            event.addCapability(new ResourceLocation(HappyHolidaysMod.MOD_ID, "nutcracker_inventory"), provider);
            event.addListener(optionalInventory::invalidate);
        }
    }
}
