package com.jumpcutfindo.happyholidays.common.item.christmas;

public enum WalnutAmmo {
    PLAIN, EXPLOSIVE, SUGARED, METALLIC, HALVED;

    public static int id(WalnutAmmo walnutAmmo) {
        switch (walnutAmmo) {
        case EXPLOSIVE -> {
            return 1;
        }
        case SUGARED -> {
            return 2;
        }
        case METALLIC -> {
            return 3;
        }
        case HALVED -> {
            return 4;
        }
        default -> {
            return 0;
        }
        }
    }
}
