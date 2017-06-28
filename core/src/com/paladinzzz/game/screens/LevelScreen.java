package com.paladinzzz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.paladinzzz.game.CrossplatformApp;
import com.paladinzzz.game.util.Constants;
import com.paladinzzz.game.audio.MusicHandler;


public class LevelScreen implements Screen {

    private CrossplatformApp game;
    private Stage stage;
    private OrthographicCamera camera;
    private Texture background, playTexture1, playTexture2, playTexture3, backTexture;
    private ImageButton playButton1, playButton2, playButton3, backButton;
    private Drawable drawableBack, drawablePlay1, drawablePlay2, drawablePlay3;
    BitmapFont font = new BitmapFont();
    int[] x = new int[255];
    public static boolean inPlayscreen = false;
    private Table table, table2, table3, table4;

    public LevelScreen(CrossplatformApp game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new FillViewport(Constants.WIDTH, Constants.HEIGHT, camera));
        this.playTexture1 = new Texture("Screens/LevelScreen/Button1.png");
        this.playTexture2 = new Texture("Screens/LevelScreen/Button2.png");
        this.playTexture3 = new Texture("Screens/LevelScreen/Button3.png");
        this.backTexture = new Texture("Screens/BackButton.png");
        this.background = new Texture("Screens/LevelScreen/LevelSelection.png");
    }

    @Override
    public void show() {

        //Geef de texture van de backButton mee aan een nieuwe ImageButton
        drawableBack = new TextureRegionDrawable(new TextureRegion(backTexture));
        backButton = new ImageButton(drawableBack);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoginScreen(game));
            }
        });

        //Geef de texture van de 1e playButton mee aan een nieuwe ImageButton
        drawablePlay1 = new TextureRegionDrawable(new TextureRegion(playTexture1));
        playButton1 = new ImageButton(drawablePlay1);
        playButton1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MenuScreen.musicHandler.stopMusic();
                game.setScreen(new GameScreen(game));
                inPlayscreen = true;
            }
        });

        //Geef de texture van de 2e playButton mee aan een nieuwe ImageButton
        drawablePlay2 = new TextureRegionDrawable(new TextureRegion(playTexture2));
        playButton2 = new ImageButton(drawablePlay2);
        playButton2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new GameScreen(game));
                inPlayscreen = true;
            }
        });

        //Geef de texture van de 3e playButton mee aan een nieuwe ImageButton
        drawablePlay3 = new TextureRegionDrawable(new TextureRegion(playTexture3));
        playButton3 = new ImageButton(drawablePlay3);
        playButton3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new GameScreen(game));
                inPlayscreen = true;
            }
        });


        //Hiermee kunnen elementen nu aan de stage worden toegevoegd
        Gdx.input.setInputProcessor(stage);

        //Een table wordt aangemaakt om met de button naar het 1e level te gaan
        table = new Table();
        table.bottom();
        table.setFillParent(true);
        table.add(playButton1).padBottom(123).padRight(285);
        stage.addActor(table);

        //Een table wordt aangemaakt om met de button naar het 2e level te gaan
        table2 = new Table();
        table2.bottom();
        table2.setFillParent(true);
        table2.add(playButton2).padBottom(123).padRight(15);
        stage.addActor(table2);

        //Een table wordt aangemaakt om met de button naar het 3e level te gaan
        table3 = new Table();
        table3.bottom();
        table3.setFillParent(true);
        table3.add(playButton3).padBottom(123).padLeft(270);
        stage.addActor(table3);

        //Een table wordt aangemaakt om de back button toe te voegen.
        table4 = new Table();
        table4.bottom();
        table4.setFillParent(true);
        table4.add(backButton).padBottom(13).padRight(640);
        stage.addActor(table4);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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
