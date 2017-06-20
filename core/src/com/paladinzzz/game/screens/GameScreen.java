package com.paladinzzz.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.paladinzzz.game.CrossplatformApp;
import com.paladinzzz.game.scenes.HUD;
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
        this.viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT);
        this.levelHUD = new HUD(gameFile.batch);
        this.mapLoader = new TmxMapLoader();
        this.worldMap = mapLoader.load("");
        this.mapRenderer = new OrthogonalTiledMapRenderer(worldMap);
        this.camera.position.set(viewport.getWorldWidth() /2, viewport.getWorldHeight() / 2, 0);
        this.world = new World(new Vector2(0,0), true);
        this.debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
