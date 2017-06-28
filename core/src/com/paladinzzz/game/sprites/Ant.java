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
    private enum State{STANDING, DYING};
    private State currentState;
    private State previousState;
    private World world;
    private GameScreen gameScreen;
    private Animation<TextureRegion> antStasis;
    private Animation<TextureRegion> antDeath;
    private boolean destroyed = false;
    private Body body;
    private TextureRegion antStand;
    private boolean movingForward;
    private float stateTimer;

    public Ant(World world, GameScreen screen, float x, float y) {
        super(screen.getMoleAtlas().findRegion("MoleRun"));
        this.gameScreen = screen;
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 4; i ++) {
            frames.add(new TextureRegion(getTexture(), i * 33, 0, 32, 32));
        }
        antStasis = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for(int i = 0; i < 4; i ++) {
            frames.add(new TextureRegion(getTexture(), i * 33, 0, 32, 32));
        }
        antDeath = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        setPosition(x, y);
        defineSprite();
        antStand = new TextureRegion(getTexture(),0, 0, 32, 32);
        setBounds(0, 0, 32 / Constants.PPM, 32 / Constants.PPM);
        setRegion(antStand);
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
        fixtureDef.filter.maskBits = Constants.MOLE_BIT | Constants.GROUND_BIT | Constants.ANT_STOP_BIT;

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        body.setUserData(this);
    }

    public void update(float deltaT) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2 + 0.08f);
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

        if (!destroyed) {
            setRegion(getFrame(deltaT));
        }
    }

    public TextureRegion getFrame(float deltaT) {
        currentState = getState();

        TextureRegion region;
        switch(currentState) {
            case DYING:
                region = antDeath.getKeyFrame(stateTimer);
                break;

            default:
                region = antStasis.getKeyFrame(stateTimer);
                break;
        }
        stateTimer = currentState == previousState ? stateTimer + deltaT : 0;
        previousState = currentState;
        return region;
    }

    public State getState() {
        if(destroyed) {
            return State.DYING;
        } else {
            return State.STANDING;
        }
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}
