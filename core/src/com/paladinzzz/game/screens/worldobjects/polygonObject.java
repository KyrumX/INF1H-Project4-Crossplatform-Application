package com.paladinzzz.game.screens.worldobjects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.paladinzzz.game.screens.worldobjects.visitor.objectVisitor;

//Deze klas beschrijft waar de Polygon objecten worden geplaatst binnen onze wereld, denk hierbij aan ramps e.d.
//We roepen uit de TiledMap alle Polygons op van de Polygon layer, vervolgens spawnen we daar Polygons
//Deze objecten zijn net als de grond maar hebben geen vierkante vorm.

public class polygonObject implements IObject{
    private PolygonShape shape;
    private BodyDef bdef;
    private FixtureDef fdef;

    public polygonObject() {
        bdef = new BodyDef();
        shape = new PolygonShape();
        fdef = new FixtureDef();
    }

    @Override
    public void defineObject(World world, TiledMap map) {
        //De ramp objecten zijn het 4e object in onze map editor, beginnend bij 0 is dat het 3e object in code
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(PolygonMapObject.class)) {
            shape = new PolygonShape();
            float[] vertices = ((PolygonMapObject) object).getPolygon().getTransformedVertices();

            float[] worldVertices = new float[vertices.length];

            for (int i = 0; i < vertices.length; ++i) {
                worldVertices[i] = vertices[i] / com.paladinzzz.game.util.Constants.PPM;
            }

            shape.set(worldVertices);

            fdef.filter.categoryBits = com.paladinzzz.game.util.Constants.POLYGON_BIT; //De ramp objecten zijn dus POLYGON_BITs

            fdef.shape = shape;
            world.createBody(bdef).createFixture(fdef);
        }
    }

    @Override
    public void getType() {
        System.out.println("I am a polygonObject!");
    }

    @Override
    public void visit(objectVisitor visitor) {
    }
}
