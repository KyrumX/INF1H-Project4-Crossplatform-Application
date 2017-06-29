package com.paladinzzz.game.screens.worldobjects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class antStopObject implements IObject {
    private Body body;
    private Rectangle rect;
    private FixtureDef fdef;
    private BodyDef bdef;
    private PolygonShape shape;

    public antStopObject() {
        bdef = new BodyDef();
        shape = new PolygonShape();
        fdef = new FixtureDef();
        shape = new PolygonShape();
    }

    @Override
    public void defineObject(World world, TiledMap map) {
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            this.rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;

            //De positie delen we door 2, omdat libgdx begint in het midden van elke vorm.
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / com.paladinzzz.game.util.Constants.PPM, (rect.getY() + rect.getHeight() / 2) / com.paladinzzz.game.util.Constants.PPM);
            this.body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / com.paladinzzz.game.util.Constants.PPM, rect.getHeight() / 2 / com.paladinzzz.game.util.Constants.PPM);

            fdef.filter.categoryBits = com.paladinzzz.game.util.Constants.ANT_STOP_BIT; //De grond is dus een GROUND_BITs

            fdef.shape = shape;
            body.createFixture(fdef);
            body.setUserData(this);
        }
    }
}