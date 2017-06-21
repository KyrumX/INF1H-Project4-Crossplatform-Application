package com.paladinzzz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.paladinzzz.game.CrossplatformApp;
import com.paladinzzz.game.scenes.HUD;
import com.paladinzzz.game.screens.worldobjects.groundObject;
import com.paladinzzz.game.util.Constants;

/**
 * Created by aaron on 20-Jun-17.
 */

public class GameScreen implements Screen {
    private CrossplatformApp game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private HUD levelHUD;
    private TmxMapLoader mapLoader;
    private TiledMap worldMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private World world;

    //World Debugger:
    private Box2DDebugRenderer debugRenderer;


    public GameScreen(CrossplatformApp gameFile) {
        this.game = gameFile;
        this.camera = new OrthographicCamera();
        this.viewport = new FillViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        this.levelHUD = new HUD(gameFile.batch);
        this.mapLoader = new TmxMapLoader();
        this.worldMap = mapLoader.load("Worlds/TestWorld/TestWorld.tmx");
        this.mapRenderer = new OrthogonalTiledMapRenderer(worldMap);
        this.camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        this.world = new World(new Vector2(0,0), true);

        //Maak en bepaal of de debugger aan is
        if(Constants.DEBUGGER_ON) {
            this.debugRenderer = new Box2DDebugRenderer();
            debugRenderer.SHAPE_STATIC.set(1, 0, 0, 1);
        }

        //Het maken van map objecten:
        new groundObject(world, worldMap);

    }

    @Override
    public void show() {

    }

    public void handleInput(float deltaT) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.position.x += 100 * deltaT;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            camera.position.x -= 100 * deltaT;

    }

    public void update(float deltaT) {
        handleInput(deltaT);
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
