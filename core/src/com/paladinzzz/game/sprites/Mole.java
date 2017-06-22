package com.paladinzzz.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.paladinzzz.game.util.Constants;

public class Mole extends Sprite {
    public World world;
    public Body body;
    public Texture texture;

    public Mole(World world, Texture texture) {
        this.world = world;
        this.texture = texture;
        defineMole();
    }

    public void defineMole() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / Constants.PPM, 310 / Constants.PPM);
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

    public void draw (Batch batch) {
        batch.draw(texture, this.body.getPosition().x + 2, this.body.getPosition().y*2);
    }
}
