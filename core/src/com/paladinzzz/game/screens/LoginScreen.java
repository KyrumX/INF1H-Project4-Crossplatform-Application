package com.paladinzzz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.paladinzzz.game.CrossplatformApp;
import com.paladinzzz.game.util.Constants;
import com.paladinzzz.game.audio.MusicHandler;
import static com.badlogic.gdx.Gdx.input;
import static com.paladinzzz.game.screens.MenuScreen.musicHandler;


public class LoginScreen implements Screen {

    private CrossplatformApp game;
    private Stage stage;
    private ImageButton exitButton, playButton, optionsButton, highscoreButton;
    private Texture exitTexture, playTexture, optionsTexture, highscoreTexture, background;
    private Drawable drawableExit, drawablePlay, drawableOptions, drawableHighscore;
    private OrthographicCamera camera;
    BitmapFont font = new BitmapFont();
    int[] x = new int[255];
    public static boolean inPlayscreen = false;
    private Table table;
    public static String playername = "";
    private boolean isConverted = false;


    public LoginScreen(CrossplatformApp game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new FillViewport(Constants.WIDTH, Constants.HEIGHT, camera));
        this.playTexture = new Texture("Screens/TitleScreen/LevelButton.png");
        this.background = new Texture("Screens/LoginScreen/loginscreen.png");
//        this.musicHandler = new MusicHandler("Music/Main_Menu_Theme.ogg", true);
//        musicHandler.setMusic("Music/Main_Menu_Theme.ogg", true);
//        musicHandler.playMusic();
    }

    @Override
    public void show() {

        //Geef de texture van de playButton mee aan de ImageButton
        drawablePlay = new TextureRegionDrawable(new TextureRegion(playTexture));
        playButton = new ImageButton(drawablePlay);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LevelScreen(game));
                inPlayscreen = true;
            }
        });

        //Hiermee kunnen elementen nu aan de stage worden toegevoegd
        input.setInputProcessor(stage);

        //Een table wordt aangemaakt om buttons aan toe te voegen.
        table = new Table();
        table.top();
        table.setFillParent(true);
        table.add(playButton).padTop(250);
        table.row();
        stage.addActor(table);

        for (int i = 0 ; i < 255; i++){
            x[i] = i;
        }

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (input.isKeyPressed(66)){
            game.setScreen(new LevelScreen(game));
            inPlayscreen = true;

            if (!isConverted){
                playername = playername.substring(0, 1).toUpperCase() + playername.substring(1, playername.length()).toLowerCase();
                isConverted = true;
            }
        }


        game.batch.begin();

        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for (int i : x) {
            if (input.isKeyJustPressed(i)){
                if (i != 62 && i != 67 && i!= 66) {
                    playername += Input.Keys.toString(i).toLowerCase();
                } else if(i == 67 & playername.length() > 0){
                    playername = playername.substring(0, playername.length() - 1).toLowerCase();
                } else {
                    playername += " ";
                }
            }
        }

        font.draw(game.batch, playername, 250, 200);

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

