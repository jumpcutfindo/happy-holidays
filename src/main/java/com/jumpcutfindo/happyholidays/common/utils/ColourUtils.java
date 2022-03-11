package com.jumpcutfindo.happyholidays.common.utils;

import com.jumpcutfindo.happyholidays.server.data.structs.Availability;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

public class ColourUtils {
    public static int rgbToInt(int r, int g, int b) {
        return ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF);
    }

    public static int rgbaToInt(int r, int g, int b, int a) {
        return ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF);
    }

    public static Component formatHolidayCode(String code) {
        return new TextComponent(code).withStyle(ChatFormatting.GRAY);
    }

    public static Component formatAvailability(Availability availability) {
        if (availability == Availability.ALWAYS) return new TextComponent(Availability.ALWAYS.toString()).withStyle(ChatFormatting.GREEN);
        else if (availability == Availability.NEVER) return new TextComponent(Availability.NEVER.toString()).withStyle(ChatFormatting.RED);
        else return new TextComponent(Availability.INTERVAL_ONLY.toString()).withStyle(ChatFormatting.AQUA);
    }
}
