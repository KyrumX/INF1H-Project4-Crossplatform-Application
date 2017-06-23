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
import com.paladinzzz.game.audio.MusicHandler;

import static com.paladinzzz.game.screens.GameScreen.showtext;


public class MenuScreen implements Screen {

    private CrossplatformApp game;
    private Stage stage;
    private ImageButton exitButton, playButton, optionsButton, highscoreButton;
    private Texture exitTexture, playTexture, optionsTexture, highscoreTexture, background;
    private Drawable drawableExit, drawablePlay, drawableOptions, drawableHighscore;
    private OrthographicCamera camera;
    private MusicHandler musicHandler;

    public static boolean inPlayscreen = false;
    private Table table;


    public MenuScreen(CrossplatformApp game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new FillViewport(Constants.WIDTH, Constants.HEIGHT, camera));
        this.exitTexture = new Texture("Screens/TitleScreen/ExitGameButton.png");
        this.playTexture = new Texture("Screens/TitleScreen/LevelButton.png");
        this.optionsTexture = new Texture("Screens/TitleScreen/OptionsButton.png");
        this.highscoreTexture = new Texture("Screens/TitleScreen/HighscoresButton.png");
        this.background = new Texture("Screens/TitleScreen/MainScreen.png");
        this.musicHandler = new MusicHandler("Music/Main_Menu_Theme.ogg", true);
        musicHandler.playMusic();
    }

    @Override
    public void show() {

        //Geef de texture van de exitButton mee aan de ImageButton
        drawableExit = new TextureRegionDrawable(new TextureRegion(exitTexture));
        exitButton = new ImageButton(drawableExit);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        //Geef de texture van de playButton mee aan de ImageButton
        drawablePlay = new TextureRegionDrawable(new TextureRegion(playTexture));
        playButton = new ImageButton(drawablePlay);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LevelScreen(game, musicHandler));
                inPlayscreen = true;
            }
        });

        //Geef de texture van de optionsbutton mee aan de ImageButton
        drawableOptions = new TextureRegionDrawable(new TextureRegion(optionsTexture));
        optionsButton = new ImageButton(drawableOptions);
        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new OptionScreen(game));
                showtext = true;
            }
        });

        drawableHighscore = new TextureRegionDrawable(new TextureRegion(highscoreTexture));
        highscoreButton = new ImageButton(drawableHighscore);
        highscoreButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Clicked");
            }
        });



        //Hiermee kunnen elementen nu aan de stage worden toegevoegd
        Gdx.input.setInputProcessor(stage);

        //Een table wordt aangemaakt om buttons aan toe te voegen.
        table = new Table();
        table.top();
        table.setFillParent(true);
        table.add(playButton).padTop(59);
        table.row();
        table.add(optionsButton).padTop(5).padRight(5);
        table.row();
        table.add(highscoreButton).padTop(2).padRight(7);
        table.row();
        table.add(exitButton).padTop(5).padRight(10);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
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
