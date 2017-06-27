package com.paladinzzz.game.sprites;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.paladinzzz.game.util.Constants;
import com.paladinzzz.game.screens.GameScreen;

public class Wurrumpie extends Sprite {
    public World world;
    public Body body;
    private GameScreen gameScreen;
    private Texture texture;
    public boolean destroyed = false;
    private TextureRegion wurmStand;

    public Wurrumpie(World world, GameScreen screen) {

        super(screen.getAtlas().findRegion("MoleRun"));
        this.gameScreen = screen;
        this.world = world;

        defineWurm();
        wurmStand = new TextureRegion(getTexture(),0, 0, 32, 32);
        setBounds(0, 0, 32 / Constants.PPM, 32 / Constants.PPM);
        setRegion(wurmStand);
    }

    public void defineWurm() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(64 / Constants.PPM, 310 / Constants.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7 / Constants.PPM);

        fixtureDef.filter.categoryBits = Constants.WURRUMPIE_BIT;

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        body.setUserData(this);
    }

    public void update(float deltaT) {
    }

    public void killWurrumpie() {
        if (!destroyed) {
            System.out.println("Wurrumpie neeeeeee!");
            System.out.println("Score + 10");
            this.destroyed = true;
        }
        //destroy this
    }

    public void draw(SpriteBatch batch) {
        if (!destroyed) super.draw(batch);
    }
}
