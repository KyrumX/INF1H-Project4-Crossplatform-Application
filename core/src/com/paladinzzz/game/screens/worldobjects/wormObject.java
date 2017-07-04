package com.paladinzzz.game.screens.worldobjects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.paladinzzz.game.screens.worldobjects.visitor.objectVisitor;
import com.paladinzzz.game.sprites.Wurrumpie;

import java.util.ArrayList;

public class wormObject implements IObject {
    private ArrayList<Wurrumpie> worms;
    private com.paladinzzz.game.screens.GameScreen screen;

    public wormObject(com.paladinzzz.game.screens.GameScreen screen, World world, TiledMap map) {
        this.worms = new ArrayList<Wurrumpie>();
        this.screen = screen;
        defineObject(world, map);
    }

    @Override
    public void defineObject(World world, TiledMap map) {
        for(MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            this.worms.add(new Wurrumpie(world, screen, rect.getX() / com.paladinzzz.game.util.Constants.PPM, rect.getY() / com.paladinzzz.game.util.Constants.PPM));
        }
    }

    public ArrayList<Wurrumpie> getWorms() {
        return worms;
    }

    @Override
    public void getType() {
        System.out.println("I am a Worm!");
    }

    @Override
    public void visit(objectVisitor visitor) {
        visitor.onWorm(this);
    }
}
