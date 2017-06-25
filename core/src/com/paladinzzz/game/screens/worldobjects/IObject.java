package com.paladinzzz.game.screens.worldobjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by aaron on 22-Jun-17.
 */

public interface IObject {
    void defineObject(World world, TiledMap map);
}
