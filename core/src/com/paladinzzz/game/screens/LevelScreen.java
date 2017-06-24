package com.paladinzzz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.paladinzzz.game.CrossplatformApp;
import com.paladinzzz.game.util.Constants;
import com.paladinzzz.game.audio.MusicHandler;


public class LevelScreen implements Screen {

    private CrossplatformApp game;
    private Stage stage;
    private TextButton level1, level2, level3, backButton;
    private OrthographicCamera camera;
    private Table table;
    private Skin skin;

    public LevelScreen(CrossplatformApp game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new FillViewport(Constants.WIDTH, Constants.HEIGHT, camera));
        this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    }

    @Override
    public void show() {


        level1 = new TextButton("Level 1", skin);
        level1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        backButton = new TextButton("back", skin);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });


        //Hiermee kunnen elementen nu aan de stage worden toegevoegd
        Gdx.input.setInputProcessor(stage);


        //Een table wordt aangemaakt om buttons aan toe te voegen.
        table = new Table();
        table.setFillParent(true);
        table.add(level1).size(100, 100);
        table.row();
        table.add(backButton).size(100, 100);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.end();
        stage.draw();
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
        stage.dispose();

    }
}
