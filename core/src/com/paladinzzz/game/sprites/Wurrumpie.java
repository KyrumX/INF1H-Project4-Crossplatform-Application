package com.paladinzzz.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.paladinzzz.game.util.Constants;
import com.paladinzzz.game.screens.GameScreen;

public class Wurrumpie extends Sprite {
    public World world;
    public Body body;
    private GameScreen gameScreen;
    private Texture texture;
    public boolean destroyed = false;
    private TextureRegion wurmStand;
    private int x;
    private int y;

    public Wurrumpie(World world, GameScreen screen, int x, int y) {
//        this.world = world;
//        this.gameScreen = screen;
//        this.texture = new Texture(Gdx.files.internal("Mole/Movement0.png"));
//
//        defineWurm();

        super(screen.getAtlas().findRegion("MoleRun"));
        this.gameScreen = screen;
        this.world = world;
        this.x = x;
        this.y = y;

        defineWurm();
        wurmStand = new TextureRegion(getTexture(),0, 0, 32, 32);
        setBounds(0, 0, 32 / Constants.PPM, 32 / Constants.PPM);
        setRegion(wurmStand);
    }

    public void defineWurm() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x / Constants.PPM, y / Constants.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7 / Constants.PPM);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        body.setUserData(this);
    }

    public void update(float deltaT, Mole player) {
        // if worm position - 1 < player position < worm position + 1: worm dies.
        if ((this.body.getPosition().x - (15 / Constants.PPM) <= player.body.getPosition().x) && (player.body.getPosition().x <= this.body.getPosition().x + (15 / Constants.PPM) &&
                (this.body.getPosition().y - (15 / Constants.PPM) <= player.body.getPosition().y) && (player.body.getPosition().y <= this.body.getPosition().y + (15 / Constants.PPM)))) {
            killWurrumpie();
        }
    }

    public void killWurrumpie() {
        this.destroyed = true;
        System.out.println("Wurrumpie neeeeeee!");
        System.out.println("Score + 10");
        Constants.score += 10;
        System.out.println(Constants.score);
        //destroy this
    }

    public void draw(SpriteBatch batch) {
        if (!destroyed) super.draw(batch);
    }
}
