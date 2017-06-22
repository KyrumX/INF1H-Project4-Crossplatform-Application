package com.paladinzzz.game.sprites;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.paladinzzz.game.util.Constants;
import com.paladinzzz.game.screens.GameScreen;

public class Mole extends Sprite {
    public enum State{JUMPING, STANDING, RUNNING};
    public State currentState;
    public State previousState;
    public World world;
    public Body body;
    private TextureRegion moleStand;
    private Animation<TextureRegion> moleRun;
    private Animation<TextureRegion> moleJump;
    private float stateTimer;

    public Mole(World world, GameScreen screen) {
        super(screen.getAtlas().findRegion("MoleRun"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 4; i ++) {
            frames.add(new TextureRegion(getTexture(), i * 33, 0, 32, 32));
        }
        moleRun = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();
        for(int i=0; i< 4; i++){
            frames.add(new TextureRegion(getTexture(), i * 33, 0, 32, 32));
        }
        moleJump = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        defineMole();
        moleStand = new TextureRegion(getTexture(),0, 0, 32, 32);
        setBounds(0, 0, 32 / Constants.PPM, 32 / Constants.PPM);
        setRegion(moleStand);
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
        shape.setRadius(7 / Constants.PPM);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }

    public void update(float deltaT) {
        setPosition((body.getPosition().x - getWidth() / 2), (body.getPosition().y - getHeight() / 2) + 0.05f);
        setRegion(getFrame(deltaT));
    }

    public TextureRegion getFrame(float deltaT) {
        currentState = getState();

        TextureRegion region;
        switch(currentState) {
            case JUMPING:
                region = moleJump.getKeyFrame(stateTimer);
                break;

            case RUNNING:
                region = moleRun.getKeyFrame(stateTimer, true);
                break;

            default:
                region = moleStand;
                break;
        }

        stateTimer = currentState == previousState ? stateTimer + deltaT : 0;
        previousState = currentState;
        return region;
    }

    public State getState() {
        if(body.getLinearVelocity().y > 0) {
            return State.JUMPING;
        }
        else if (body.getLinearVelocity().x != 0) {
            return State.RUNNING;
        } else {
            return State.STANDING;
        }
    }
}
