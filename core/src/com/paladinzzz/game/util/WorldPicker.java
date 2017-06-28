package com.paladinzzz.game.util;

public class WorldPicker {
    public static String pickWorld(int worldNum, int levelNum) {
        if(worldNum == 1)
            switch (levelNum) {
                case 1: return "Worlds/world1/level1/level1-1.tmx";
                case 2: return "Worlds/world1/level2/level1-2.tmx";
                default: return "Worlds/world1/level1/level1-1.tmx";
            }
        else {
            return "Worlds/level2/World2.tmx";
        }
    }
}
