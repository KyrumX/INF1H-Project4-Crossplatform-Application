package com.paladinzzz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.paladinzzz.game.CrossplatformApp;

/**
 * Created by Ralph on 20-6-2017.
 */

public class MenuScreen implements Screen {

    private CrossplatformApp game;
    private Stage stage;
    private Skin skin;
    private TextButton exitButton, playButton, optionsButton;

    public MenuScreen(CrossplatformApp menu) {
        this.game = menu;
    }

    @Override
    public void show() {


        //Geef de images aan de variable skin mee
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        stage = new Stage();
        stage.clear();

        //Hiermee kunnen elementen nu aan de stage worden toegevoegd
        Gdx.input.setInputProcessor(stage);

        //Maak een table waar we de buttons aan toevoegen
        Table table = new Table();
        table.setFillParent(true);
        table.top();

        //Exitbutton die het programma afsluit
        exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        //
        playButton = new TextButton("Play", skin);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((CrossplatformApp)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game));
            }
        });

        optionsButton = new TextButton("Options", skin);
        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((CrossplatformApp)Gdx.app.getApplicationListener()).setScreen(new OptionScreen(game));
            }
        });



        table.add(playButton).size(200, 100);
        table.row();
        table.add(optionsButton);
        table.row();
        table.add(exitButton);
        stage.addActor(table);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
