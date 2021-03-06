package com.paladinzzz.game.screens.worldobjects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.paladinzzz.game.screens.worldobjects.visitor.objectVisitor;
import com.paladinzzz.game.sprites.Ant;

import java.util.ArrayList;

//Deze klas beschrijft waar de Ants worden geplaatst binnen onze wereld
//We roepen uit de TiledMap alle Rectangles op van de Ants layer, vervolgens spawnen we daar Ants

public class antObject implements IObject {
    private ArrayList<Ant> ants;
    private com.paladinzzz.game.screens.GameScreen screen;

    public antObject(com.paladinzzz.game.screens.GameScreen screen, World world, TiledMap map) {
        this.ants = new ArrayList<Ant>();
        this.screen = screen;
        defineObject(world, map);
    }

    @Override
    public void defineObject(World world, TiledMap map) {
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            this.ants.add(new Ant(world, screen, rect.getX() / com.paladinzzz.game.util.Constants.PPM, rect.getY() / com.paladinzzz.game.util.Constants.PPM));
        }
    }

    @Override
    public void getType() {
        System.out.println("I am a Ant!");
    }

    @Override
    public void visit(objectVisitor visitor) {
        visitor.onAnt(this);
    }

    public ArrayList<Ant> getAnts() {
        return ants;
    }
}
