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
import com.paladinzzz.game.audio.MusicHandler;
import com.paladinzzz.game.scenes.HUD;
import com.paladinzzz.game.screens.worldobjects.groundObject;
import com.paladinzzz.game.sprites.Mole;
import com.paladinzzz.game.util.Constants;

/**
 * Created by aaron on 20-Jun-17.
 *
 * Edit by Jasper on 11:11 21/06
 *      Added MusicHandler
 *      Editted some Local Variables
 */

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

    private groundObject ground;

    public GameScreen(CrossplatformApp gameFile) {
        this.game = gameFile;
        System.out.println(gameFile);
        this.camera = new OrthographicCamera();
        this.viewport = new FillViewport(Constants.V_WIDTH / Constants.PPM, Constants.V_HEIGHT / Constants.PPM, camera);
        this.levelHUD = new HUD(gameFile.batch);

        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap worldMap = mapLoader.load("Worlds/TestWorld/TestWorld.tmx");
        this.mapRenderer = new OrthogonalTiledMapRenderer(worldMap, 1  / Constants.PPM);
        this.camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        this.world = new World(new Vector2(0,-10), true);
        this.player = new Mole(world);

        //Music Player
        MusicHandler musicHandler = new MusicHandler("Music/Town_Theme_1.mp3", true);
        musicHandler.playMusic();


        //Maak en bepaal of de debugger aan is
        if(Constants.DEBUGGER_ON) {
            this.debugRenderer = new Box2DDebugRenderer();
            debugRenderer.SHAPE_STATIC.set(1, 0, 0, 1);
        }

        //Het maken van map objecten:
        this.ground = new groundObject(world, worldMap, player);

    }

    @Override
    public void show() {

    }

    public void handleInput(float deltaT) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (ground.collides()) {
                player.body.applyLinearImpulse(new Vector2(0, 4f), player.body.getWorldCenter(), true);
            }        }
        if(Gdx.input.isKeyPressed(Input.Keys.W) && player.body.getLinearVelocity().x <= 2) {
            player.body.applyLinearImpulse(new Vector2(0.1f, 0), player.body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) && player.body.getLinearVelocity().x >= -2) {
            player.body.applyLinearImpulse(new Vector2(-0.1f, 0), player.body.getWorldCenter(), true);
        }

    }

    public void update(float deltaT) {
        handleInput(deltaT);

        world.step(1/60f, 6, 2);
        camera.position.x = player.body.getPosition().x;

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

        // Set level boundaries
        if (player.body.getPosition().x <= 0) {
            player.body.setTransform(0, player.body.getPosition().y, 0);
        } else if (player.body.getPosition().x >= 8){
            player.body.setTransform(8, player.body.getPosition().y, 0);
        }
        if (player.body.getPosition().y >= 2){
            player.body.setTransform(player.body.getPosition().x, 2, 0);
        }

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
