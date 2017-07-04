package com.paladinzzz.game.sprites;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private enum State{WALKING};
    private State currentState;
    private State previousState;
    private World world;
    private Animation<TextureRegion> antWalk;
    private Body body;
    private boolean movingForward;
    private float stateTimer;
    private boolean flipped;

    public Ant(World world, GameScreen screen, float x, float y) {
        super(screen.getAntAtlas().findRegion("Ant"));
        this.world = world;
        currentState = State.WALKING;
        previousState = State.WALKING;
        stateTimer = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 9; i++) {
            frames.add(new TextureRegion(getTexture(), i * 34, 0, 32, 32));
        }
        antWalk = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        setPosition(x, y);
        defineSprite();
        setBounds(0, 0, 32 / Constants.PPM, 32 / Constants.PPM);
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

        fixtureDef.filter.categoryBits = Constants.ANT_BIT; //De Ants zijn de ANT_BITs
        fixtureDef.filter.maskBits = Constants.MOLE_BIT | com.paladinzzz.game.util.Constants.GROUND_BIT | com.paladinzzz.game.util.Constants.ANT_STOP_BIT;

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        body.setUserData(this);
    }

    public void update(float deltaT) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2 + 0.08f);
        if(body.getLinearVelocity().x == 0 && movingForward) {
            movingForward = false;
        }
        else if (body.getLinearVelocity().x == 0 && !movingForward) {
            movingForward = true;
        }
        if(movingForward) {
            body.setLinearVelocity(new Vector2(0.5f, 0f));
        }
        else {
            body.setLinearVelocity(new Vector2(-0.5f, 0f));
        }
        setRegion(getFrame(deltaT));
    }

    private TextureRegion getFrame(float deltaT) {
        TextureRegion region;
        if (movingForward) {
            //When the Ant turns around, flipped is triggered once.
            if (flipped) antWalk.getKeyFrame(stateTimer).flip(true, false);
            flipped = false;
            region = antWalk.getKeyFrame(stateTimer);
        } else {
            if (!flipped) antWalk.getKeyFrame(stateTimer).flip(true, false);
            flipped = true;
            region = antWalk.getKeyFrame(stateTimer);
        }
        stateTimer = currentState == previousState ? stateTimer + deltaT : 0;
        return region;
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}
