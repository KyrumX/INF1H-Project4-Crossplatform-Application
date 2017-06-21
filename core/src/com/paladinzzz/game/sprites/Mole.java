package com.paladinzzz.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.paladinzzz.game.util.Constants;

/**
 * Created by aaron on 21-Jun-17.
 */

public class Mole extends Sprite {
    public World world;
    public Body body;

    public Mole(World world) {
        this.world = world;
        defineMole();
    }

    public void defineMole() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / Constants.PPM, 64 / Constants.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        System.out.println(bodyDef);
        System.out.println(body);

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Constants.PPM);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }
}
