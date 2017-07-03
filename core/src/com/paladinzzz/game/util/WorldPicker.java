package com.paladinzzz.game.util;

public class WorldPicker {
    public static String pickWorld(int worldNum, int levelNum) {
        if(worldNum == 1)
            switch (levelNum) {
                case 1: return "Worlds/world2/level1/level1-1.tmx";
                case 2: return "Worlds/world1/level2/level1-2.tmx";
                default: return "Worlds/world1/level1/level1-1.tmx";
            }
        else if (worldNum == 2)
            switch (levelNum) {
                case 1: return "Worlds/world2/level1/level2-1.tmx";
                case 2: return "Worlds/world1/level2/level2-2.tmx";
                default: return "Worlds/world1/level1/level2-1.tmx";
            }
        else {
            return "Worlds/level2/World2.tmx";
        }
    }

    public static String getWorldName(int worldNum, int levelNum) {
        if(worldNum == 1)
            switch (levelNum) {
                case 1: return "World 1-1";
                case 2: return "World 1-2";
                default: return "World 1-1";
            }
        else if(worldNum == 2)
            switch (levelNum) {
                case 1: return "World 2-1";
                case 2: return "World 2-2";
                default: return "World 2-1";
            }
        else {
            return "World 1-1";
        }
    }
}
