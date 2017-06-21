package com.paladinzzz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.paladinzzz.game.CrossplatformApp;
import com.paladinzzz.game.util.Constants;

/**
 * Created by Ralph on 20-6-2017.
 */

public class MenuScreen implements Screen {

    private CrossplatformApp game;
    private Stage stage;
    private ImageButton exitButton, playButton, optionsButton;
    private Texture exitTexture, playTexture, optionsTexture, backGround;
    private Drawable drawableExit, drawablePlay, drawableOptions;
    private OrthographicCamera camera;




    public MenuScreen(CrossplatformApp menu) {
        this.game = menu;
        this.camera = new OrthographicCamera();
        this.playTexture = new Texture("StartGameButton.png");
        this.exitTexture = new Texture("ExitGameButton.png");
        this.optionsTexture = new Texture("OptionsButton.png");
        this.backGround = new Texture("Titlescreen.png");
        this.stage = new Stage(new FillViewport(Constants.WIDTH, Constants.HEIGHT, camera));

    }

    @Override
    public void show() {


        drawableExit = new TextureRegionDrawable(new TextureRegion(exitTexture));
        exitButton = new ImageButton(drawableExit);
        exitButton.setPosition(Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2, Gdx.graphics.getHeight()/2);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });


        drawablePlay = new TextureRegionDrawable(new TextureRegion(playTexture));
        playButton = new ImageButton(drawablePlay);
        playButton.setPosition(Gdx.graphics.getWidth()/2 - playButton.getWidth()/2, Gdx.graphics.getHeight() - 150);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((CrossplatformApp)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game));
                stage.dispose();
            }
        });


        drawableOptions = new TextureRegionDrawable(new TextureRegion(optionsTexture));
        optionsButton = new ImageButton(drawableOptions);
        optionsButton.setPosition(10, 300);
        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((CrossplatformApp)Gdx.app.getApplicationListener()).setScreen(new OptionScreen(game));
                stage.dispose();
            }
        });


        //Hiermee kunnen elementen nu aan de stage worden toegevoegd
        Gdx.input.setInputProcessor(stage);


        //Maak een table waar we de buttons aan toevoegen
//        Table table = new Table();
//        table.setFillParent(true);

//        table.add(playButton);
//        table.row();
//        table.add(exitButton);
//        table.row();
//        table.add(optionsButton);

        stage.addActor(exitButton);
        stage.addActor(playButton);
        stage.addActor(optionsButton);



    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(backGround, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

    }
}
