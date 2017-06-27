package com.paladinzzz.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.paladinzzz.game.screens.GameScreen;
import com.paladinzzz.game.util.Constants;

public class Ant extends Sprite implements ISprite{
    private World world;
    private GameScreen gameScreen;
    private Body body;
    private TextureRegion antStand;
    private boolean movingForward;

    public Ant(World world, GameScreen screen, float x, float y) {
        super(screen.getAtlas().findRegion("MoleRun"));
        this.gameScreen = screen;
        this.world = world;
        setPosition(x, y);
        defineSprite();
    }

    @Override
    public void defineSprite() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7 / Constants.PPM);

        //Hier bepalen we waar we mee kunnen botsen:
        fixtureDef.filter.categoryBits = Constants.ANT_BIT; //De Ants zijn het ANT_BITs
        fixtureDef.filter.maskBits = Constants.MOLE_BIT | Constants.GROUND_BIT | Constants.ANT_STOP_BIT;

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        body.setUserData(this);
    }

    public void update(float deltaT) {
        if(body.getLinearVelocity().x == 0 && movingForward == true) {
            movingForward = false;
        }
        else if (body.getLinearVelocity().x == 0 && movingForward == false) {
            movingForward = true;
        }
        if(movingForward)
            body.setLinearVelocity(new Vector2(0.5f, 0f));
        else
            body.setLinearVelocity(new Vector2(-0.5f, 0f));
    }
}
