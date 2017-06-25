package com.paladinzzz.game.screens.worldobjects.factory;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.paladinzzz.game.screens.worldobjects.IObject;
import com.paladinzzz.game.screens.worldobjects.bounceObject;
import com.paladinzzz.game.screens.worldobjects.groundObject;
import com.paladinzzz.game.screens.worldobjects.polygonObject;
import com.paladinzzz.game.sprites.Mole;

public abstract class objectFactory {
    public static IObject createObject(int objectNum, Mole mole) {
        switch (objectNum) {
            case 1: return new groundObject(mole);
            case 2: return new polygonObject();
            case 3: return new bounceObject();
            default: return null;
        }
    }
}
