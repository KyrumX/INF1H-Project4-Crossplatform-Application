package com.paladinzzz.game.screens.worldobjects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.paladinzzz.game.screens.GameScreen;
import com.paladinzzz.game.sprites.Ant;
import com.paladinzzz.game.util.Constants;

import java.util.ArrayList;

public class antObject implements IObject {
    private ArrayList<Ant> ants;
    private GameScreen screen;

    public antObject(GameScreen screen, World world, TiledMap map) {
        this.ants = new ArrayList<Ant>();
        this.screen = screen;
        defineObject(world, map);
    }

    @Override
    public void defineObject(World world, TiledMap map) {
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            this.ants.add(new Ant(world, screen, rect.getX() / Constants.PPM, rect.getY() / Constants.PPM));
        }
    }

    public ArrayList<Ant> getAnts() {
        return ants;
    }
}
