package com.paladinzzz.game.screens.worldobjects.factory;

import com.paladinzzz.game.screens.worldobjects.antStopObject;
import com.paladinzzz.game.screens.worldobjects.bounceObject;
import com.paladinzzz.game.screens.worldobjects.finishObject;
import com.paladinzzz.game.screens.worldobjects.fluidKillable;
import com.paladinzzz.game.screens.worldobjects.groundObject;
import com.paladinzzz.game.screens.worldobjects.polygonObject;

//De factory die we gebruiken om een groot deel van onze wereld objecten te maken

public abstract class objectFactory {
    public static com.paladinzzz.game.screens.worldobjects.IObject createObject(int objectNum) {
        switch (objectNum) {
            case 1: return new groundObject();
            case 2: return new polygonObject();
            case 3: return new bounceObject();
            case 4: return new fluidKillable();
            case 5: return new antStopObject();
            case 8: return new finishObject();
            default: return null;
        }
    }
}
