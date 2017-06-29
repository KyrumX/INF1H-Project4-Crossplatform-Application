package com.paladinzzz.game.screens.worldobjects.factory;

import com.paladinzzz.game.screens.worldobjects.groundObject;

public abstract class objectFactory {
    public static com.paladinzzz.game.screens.worldobjects.IObject createObject(int objectNum, com.paladinzzz.game.sprites.Mole mole) {
        switch (objectNum) {
            case 1: return new groundObject(mole);
            case 2: return new com.paladinzzz.game.screens.worldobjects.polygonObject();
            case 3: return new com.paladinzzz.game.screens.worldobjects.bounceObject();
            case 4: return new com.paladinzzz.game.screens.worldobjects.fluidKillable();
            default: return null;
        }
    }
}
