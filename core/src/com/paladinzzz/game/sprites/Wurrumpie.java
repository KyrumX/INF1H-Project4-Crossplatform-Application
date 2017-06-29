package com.paladinzzz.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.paladinzzz.game.util.Constants;
import com.paladinzzz.game.screens.GameScreen;

public class Wurrumpie extends Sprite {
    private enum State{STANDING, DYING};
    private State currentState;
    private State previousState;
    public World world;
    public Body body;
    private GameScreen gameScreen;
    private Animation<TextureRegion> wurmStasis;
    private Animation<TextureRegion> wurmDeath;
    private boolean destroyed = false;
    private TextureRegion wurmStand;
    private float stateTimer;

    public Wurrumpie (World world, GameScreen screen, float x, float y) {
        super(screen.getWurmAtlas().findRegion("Wurrumpie"));
        this.gameScreen = screen;
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 4; i ++) {
            frames.add(new TextureRegion(getTexture(), i * 33, 0, 32, 32));
        }
        wurmStasis = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for(int i = 0; i < 4; i ++) {
            frames.add(new TextureRegion(getTexture(), i * 33, 0, 32, 32));
        }
        wurmDeath = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        setPosition(x, y);
        defineWurm();
        setBounds(0, 0, 32 / Constants.PPM, 32 / Constants.PPM);
    }

    private void defineWurm() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.StaticBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(8 / Constants.PPM);

        fixtureDef.filter.categoryBits = Constants.WURRUMPIE_BIT;

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        body.setUserData(this);
    }

    public void update(float deltaT) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2 + 0.08f);
        if (!destroyed) {
            setRegion(getFrame(deltaT));
        }
    }

    private TextureRegion getFrame(float deltaT) {
        currentState = getState();

        TextureRegion region;
        switch(currentState) {
            case DYING:
                region = wurmDeath.getKeyFrame(stateTimer);
                break;

            default:
                region = wurmStasis.getKeyFrame(stateTimer);
                break;
        }
        stateTimer = currentState == previousState ? stateTimer + deltaT : 0;
        previousState = currentState;
        return region;
    }

    private State getState() {
        if(destroyed) {
            return State.DYING;
        } else {
            return State.STANDING;
        }
    }

    public void killWurrumpie() {
        if (!destroyed) {
            System.out.println("Wurrumpie neeeeeee!");
            System.out.println("Score + 10");
            this.destroyed = true;
        }
        Gdx.app.postRunnable(new Runnable() {

            @Override
            public void run () {
                world.destroyBody(body);
            }
        });
    }

    public void draw(SpriteBatch batch) {
        if (!destroyed) super.draw(batch);
    }
}
