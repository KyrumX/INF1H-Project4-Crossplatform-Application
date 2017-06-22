package com.paladinzzz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.paladinzzz.game.CrossplatformApp;
import com.paladinzzz.game.scenes.HUD;
import com.paladinzzz.game.screens.worldobjects.*;
import com.paladinzzz.game.screens.worldobjects.factory.objectFactory;
import com.paladinzzz.game.sprites.Mole;
import com.paladinzzz.game.util.Constants;

public class GameScreen implements Screen {
    private CrossplatformApp game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private HUD levelHUD;
    private OrthogonalTiledMapRenderer mapRenderer;
    private World world;

    //World Debugger:
    private Box2DDebugRenderer debugRenderer;

    //Playable character:
    private Mole player;

    private IObject ground;
    private IObject ramp;
    private IObject bounceBlocks;


    public GameScreen(CrossplatformApp gameFile) {
        this.game = gameFile;
        System.out.println(gameFile);
        this.camera = new OrthographicCamera();
        this.viewport = new FillViewport(Constants.V_WIDTH / Constants.PPM, Constants.V_HEIGHT / Constants.PPM, camera);
        this.levelHUD = new HUD(gameFile.batch, "hoi");

        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap worldMap = mapLoader.load("Worlds/level1/World1.tmx");
        this.mapRenderer = new OrthogonalTiledMapRenderer(worldMap, 1  / Constants.PPM);
        this.camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        this.world = new World(new Vector2(0,-10), true);
        this.player = new Mole(world);

        //Music Player
        //MusicHandler musicHandler = new MusicHandler("Music/Town_Theme_1.ogg", true);
        //musicHandler.playMusic();


        //Maak en bepaal of de debugger aan is
        if(Constants.DEBUGGER_ON) {
            this.debugRenderer = new Box2DDebugRenderer();
            debugRenderer.SHAPE_STATIC.set(1, 0, 0, 1);
        }

        //Het maken van map objecten:
        this.ground = objectFactory.createObject(1, world, worldMap, this.player);
        this.ramp = objectFactory.createObject(2, world, worldMap, player);
        this.bounceBlocks = objectFactory.createObject(3, world, worldMap, player);

    }

    @Override
    public void show() {

    }

    public void handleInput(float deltaT) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && (!(player.body.getLinearVelocity().y > 0 || player.body.getLinearVelocity().y < 0))) {
//            if (ground.collides()) {
//                player.body.applyLinearImpulse(new Vector2(0, 4f), player.body.getWorldCenter(), true);
//            }        }
            player.body.applyLinearImpulse(new Vector2(0, 4f), player.body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W) && player.body.getLinearVelocity().x <= 2) {
            player.body.applyLinearImpulse(new Vector2(0.1f, 0), player.body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) && player.body.getLinearVelocity().x >= -2) {
            player.body.applyLinearImpulse(new Vector2(-0.1f, 0), player.body.getWorldCenter(), true);
        }

    }

    public void update(float deltaT) {
        handleInput(deltaT);

//        if (player.body.getLinearVelocity().x <= 4f)
//            player.body.applyLinearImpulse(new Vector2(4f / Constants.PPM, 0), player.body.getWorldCenter(), true);

        world.step(1/60f, 6, 2);
//        System.out.println(player.body.getPosition().y);
//        if(!(player.body.getPosition().y < 0.55))
//            camera.position.y = player.body.getPosition().y + (float) 0.71;
//        if(!(player.body.getPosition().x < 0.5384443))
//            camera.position.x = player.body.getPosition().x + 2;

        camera.position.x = player.body.getPosition().x + (170 / Constants.PPM);
        camera.position.y = player.body.getPosition().y;

        camera.update();
        mapRenderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        update(delta);

        //Voordat we beginnen met tekenen maken we het scherm leeg:
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Render de map
        mapRenderer.render();

        //Render de debug renderer lijntjes:
        if(Constants.DEBUGGER_ON)
            debugRenderer.render(world, camera.combined);

        //Zet de positie van de camera:
        game.batch.setProjectionMatrix(levelHUD.hudStage.getCamera().combined);

        //Teken de HUD:
        levelHUD.hudStage.draw();

//        // Set level boundariesw
//        if (player.body.getPosition().x <= 0) {
//            player.body.setTransform(0, player.body.getPosition().y, 0);
//        } else {
//            player.body.setTransform(player.body.getPosition().x + 0.03f, player.body.getPosition().y, 0);
//        }
//        if (player.body.getPosition().x >= 8){
//                player.body.setTransform(8, player.body.getPosition().y, 0);
//        }
//        if (player.body.getPosition().y >= 2){
//            player.body.setTransform(player.body.getPosition().x, 2, 0);
//        }

        //Open de batch en teken alles:
        game.batch.begin();
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
