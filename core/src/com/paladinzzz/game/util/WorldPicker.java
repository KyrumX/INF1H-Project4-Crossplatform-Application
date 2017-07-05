package com.paladinzzz.game.util;

//De class die we gebruiken om de juiste wereld de af te spelen

public class WorldPicker {
    public static String pickWorld(int worldNum, int levelNum) {
        if (worldNum == 1)
            switch (levelNum) {
                case 1:
                    return "Worlds/world1/level1/level1-1.tmx";
                case 2:
                    return "Worlds/world1/level2/level1-2.tmx";
                default:
                    return "Worlds/world1/level1/level1-1.tmx";
            }
        else if (worldNum == 2) {
            switch (levelNum) {
                case 1:
                    return "Worlds/world2/level1/level2-1.tmx";
                case 2:
                    return "Worlds/world2/level2/level2-2.tmx";
                default:
                    return "Worlds/world2/level1/level2-1.tmx";
            }
        } else if (worldNum == 3) {
            switch (levelNum) {
                case 1:
                    return "Worlds/world3/level1/level3-1.tmx";
                case 2:
                    return "Worlds/world3/level2/level3-2.tmx";
                default:
                    return "Worlds/world3/level3/level3-1.tmx";
            }
        }
        return "Worlds/world1/level1/level1-1.tmx";
    }
}
