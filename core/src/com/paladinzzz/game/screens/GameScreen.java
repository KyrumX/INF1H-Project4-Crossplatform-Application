package com.paladinzzz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.paladinzzz.game.screens.collision.CollisionListener;
import com.paladinzzz.game.screens.worldobjects.Iterator.ObjectIterator;
import com.paladinzzz.game.screens.worldobjects.factory.objectFactory;
import com.paladinzzz.game.sprites.Ant;
import com.paladinzzz.game.util.Constants;
import com.paladinzzz.game.util.WorldPicker;
import com.paladinzzz.game.util.playerMemory;

import static com.paladinzzz.game.screens.MenuScreen.musicHandler;

public class GameScreen implements Screen {
    static boolean inPause = false;
    public com.paladinzzz.game.CrossplatformApp game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private HUD levelHUD;
    private OrthogonalTiledMapRenderer mapRenderer;
    private World world;
    private Sound jump = Gdx.audio.newSound(Gdx.files.internal("Audio/jump.wav"));

    BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
    static boolean showtext = true;
    private TextureAtlas moleAtlas;
    private TextureAtlas wurmAtlas;

    //World Debugger:
    private Box2DDebugRenderer debugRenderer;
    private TiledMap worldMap;

    //Playable character and AI:
    private com.paladinzzz.game.sprites.Mole player;
    private com.paladinzzz.game.sprites.Wurrumpie wurrumpie;
    private com.paladinzzz.game.screens.worldobjects.antObject antsObject;

    private ObjectIterator objectList;
    private com.paladinzzz.game.screens.worldobjects.IObject ground, fluid, ramp, bounceBlocks, antStoppers, finishBlocks;


    public GameScreen(com.paladinzzz.game.CrossplatformApp gameFile) {
        this.game = gameFile;
        this.camera = new OrthographicCamera();
        this.viewport = new FillViewport(Constants.V_WIDTH / Constants.PPM, Constants.V_HEIGHT / Constants.PPM, camera);
        this.levelHUD = new HUD(gameFile.batch, WorldPicker.getWorldName(playerMemory.player.worldAndLevelData.getCurrentWorld(), playerMemory.player.worldAndLevelData.getCurrentLevel()));

        TmxMapLoader mapLoader = new TmxMapLoader();

        //Hier bepalen we welke wereld het wordt:
        this.worldMap = mapLoader.load(WorldPicker.pickWorld(playerMemory.player.worldAndLevelData.getCurrentWorld(), playerMemory.player.worldAndLevelData.getCurrentLevel()));

        this.mapRenderer = new OrthogonalTiledMapRenderer(worldMap, 1  / Constants.PPM);
        this.camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
      
        this.world = new World(new Vector2(0,-10), true);
        this.moleAtlas = new TextureAtlas("Mole3.0/Mole_3.0.pack");
        this.wurmAtlas = new TextureAtlas("Wurrumpie/Wurrumpie.pack");
        this.player = new com.paladinzzz.game.sprites.Mole(world, this);

        //Maak en bepaal of de debugger aan is.
        if(Constants.DEBUGGER_ON) {
            this.debugRenderer = new Box2DDebugRenderer();
            debugRenderer.SHAPE_STATIC.set(1, 0, 0, 1);
        }

        //Het maken van map objecten:
        ground = objectFactory.createObject(1, this.player);
        ramp = objectFactory.createObject(2, player);
        bounceBlocks = objectFactory.createObject(3, player);
        fluid = objectFactory.createObject(4, player);
        antStoppers = new com.paladinzzz.game.screens.worldobjects.antStopObject();
        antsObject = new com.paladinzzz.game.screens.worldobjects.antObject(this, world, worldMap);
        finishBlocks = new com.paladinzzz.game.screens.worldobjects.finishObject(world, worldMap);

        //Voeg de objecten toe aan een iterator:
        this.objectList = new ObjectIterator();
        this.objectList.add(ground);
        this.objectList.add(ramp);
        this.objectList.add(bounceBlocks);
        this.objectList.add(fluid);

        //Iterate door de objecten om ze te definiëren:
        while (objectList.hasNext()) {
            objectList.getNext().defineObject(world, worldMap);
        }
        antStoppers.defineObject(world, worldMap);


        world.setContactListener(new CollisionListener(this.game));

        musicHandler.stopMusic();
        musicHandler = new MusicHandler("Music/Town_Theme_1.ogg", true);
        musicHandler.playMusic();
    }

    @Override
    public void show() {
    }

    private void handleInput(float deltaT) {
        if (Constants.DEBUGGER_ON) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && (!(player.body.getLinearVelocity().y > 0 || player.body.getLinearVelocity().y < 0))) {
                jump.play(0.33f);
                player.body.applyLinearImpulse(new Vector2(0, 4f), player.body.getWorldCenter(), true);
                HUD.spacepressed = true;
            }
            if ((Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.D)) && player.body.getLinearVelocity().x <= 2) {
                player.body.applyLinearImpulse(new Vector2(0.1f, 0), player.body.getWorldCenter(), true);
            }
            if ((Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.A)) && player.body.getLinearVelocity().x >= -2) {
                player.body.applyLinearImpulse(new Vector2(-0.1f, 0), player.body.getWorldCenter(), true);
            }
        } else {
            if (player.body.getLinearVelocity().x <= 1)
                player.body.applyLinearImpulse(new Vector2(0.2f, 0f), player.body.getWorldCenter(), true);
            if (Gdx.input.isTouched() && player.body.getLinearVelocity().y == 0) {
                jump.play(1.0f);
                player.body.applyLinearImpulse(new Vector2(0, 4f), player.body.getWorldCenter(), true);
                HUD.spacepressed = true;
            }
//        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
//            inPause = true;
//            game.setScreen(new PauseScreen(this, game));
//        }
        }
    }

    private void update(float deltaT) {
        handleInput(deltaT);

        world.step(1/60f, 6, 2);

        System.out.println(player.body.getPosition().x);

        if(!(player.body.getPosition().x - (170 / Constants.PPM) >= (75 / Constants.PPM) - (170 /  Constants.PPM)))
            camera.position.x = (75 / Constants.PPM) + (170 / Constants.PPM);
        else
            camera.position.x = player.body.getPosition().x + (170 / Constants.PPM);

        if(!(player.body.getPosition().y <= 133 / Constants.PPM))
            camera.position.y = player.body.getPosition().y;

        player.update(deltaT);
        wurrumpie.update(deltaT);
        for(Ant ant : antsObject.getAnts()) {
            ant.update(deltaT);
        }
        camera.update();
        mapRenderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        update(delta);

        //Voordat we beginnen met tekenen maken we het scherm leeg:
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            levelHUD.removeSpaceText();
        }

        player.draw(game.batch);
        for(Ant ant : antsObject.getAnts()) {
            ant.draw(game.batch);
        }
        wurrumpie.draw(game.batch);

        game.batch.end();
    }

    public TextureAtlas getMoleAtlas(){
        return moleAtlas;
    }

    public TextureAtlas getWurmAtlas() {return wurmAtlas;}

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
        this.worldMap.dispose();
        this.mapRenderer.dispose();
        this.world.dispose();
        this.debugRenderer.dispose();
        this.levelHUD.dispose();
    }

    public CrossplatformApp getGame() {
        return game;
    }
}
