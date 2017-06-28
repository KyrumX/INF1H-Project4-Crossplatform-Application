package com.paladinzzz.game.util;

public class WorldPicker {
    public static String pickWorld(int worldNum) {
        switch (worldNum) {
            case 1: return "Worlds/world1/level1/level1-1.tmx";
            default: return "Worlds/world1/level1/level1-1.tmx";
        }
    }
}
