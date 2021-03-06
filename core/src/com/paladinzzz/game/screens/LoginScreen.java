package com.paladinzzz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
import com.paladinzzz.game.util.Constants;
import com.paladinzzz.game.database.JSONfunctions;
import com.paladinzzz.game.util.TempMS;

import com.paladinzzz.game.util.playerMemory;

import static com.badlogic.gdx.Gdx.input;


public class LoginScreen implements Screen {

    private com.paladinzzz.game.CrossplatformApp game;
    private Stage stage;
    private ImageButton backButton, playButton, emptyButton;
    private Texture backTexture, playTexture, emptyTexture, background;
    private Drawable drawableBack, drawablePlay, drawableEmpty;
    private OrthographicCamera camera;
    BitmapFont font = new BitmapFont();
    int[] x = new int[255];
    public static boolean inPlayscreen = false;
    public static String playername = "";
    private boolean isConverted = false;
    private Table table, table2;
    private Sound click = Gdx.audio.newSound(Gdx.files.internal("Audio/click.wav"));
    private int[] letters = new int[26];
    private TempMS tempMS;

    public LoginScreen(com.paladinzzz.game.CrossplatformApp game, TempMS tempMS) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new FillViewport(com.paladinzzz.game.util.Constants.WIDTH, com.paladinzzz.game.util.Constants.HEIGHT, camera));
        this.playTexture = new Texture("Screens/LoginScreen/LvlSelection.png");
        this.backTexture = new Texture("Screens/BackButton.png");
        this.background = new Texture("Screens/LoginScreen/loginscreen.png");
        this.emptyTexture = new Texture("Screens/LoginScreen/LongEmpty.png");
        this.playername = "";
        this.tempMS = tempMS;
    }

    @Override
    public void show() {
        //Geeft de texture van de backButton mee aan een nieuwe ImageButton
        drawableBack = new TextureRegionDrawable(new TextureRegion(backTexture));
        backButton = new ImageButton(drawableBack);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play(1.0f * Constants.soundLevel);
                game.setScreen(tempMS.menuScreen);
            }
        });

        //Geeft de texture van de playButton mee aan een nieuwe ImageButton
        drawablePlay = new TextureRegionDrawable(new TextureRegion(playTexture));
        playButton = new ImageButton(drawablePlay);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play(1.0f * Constants.soundLevel);
                playerMemory.player = null;
                playerMemory.player = new com.paladinzzz.game.player.Player(playername);
                playerMemory.player.addPlayer();
                game.setScreen(new LevelScreen(game, tempMS));
                inPlayscreen = true;
            }
        });

        drawableEmpty = new TextureRegionDrawable(new TextureRegion(emptyTexture));
        emptyButton = new ImageButton(drawableEmpty);
        emptyButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setOnscreenKeyboardVisible(true);
            }
        });


        //Hiermee kunnen elementen nu aan de stage worden toegevoegd
        input.setInputProcessor(stage);

        //Een table wordt aangemaakt om de button te maken die naar de level selection leidt
        table = new Table();
        table.bottom();
        table.setFillParent(true);
        table.add(emptyButton).padBottom(20);
        table.row();
        table.add(playButton).padBottom(40);
        stage.addActor(table);

        //Een table wordt aangemaakt om de back button toe te voegen naar het main menu
        table2 = new Table();
        table2.bottom();
        table2.setFillParent(true);
        table2.add(backButton).padBottom(13).padRight(640);
        table2.row();
        stage.addActor((table2));

        for (int lel = 0 ; lel < 255; lel++){
            x[lel] = lel;
        }

        int m = 29;
        for (int i = 0; i <= 25; i++){
            letters[i] = m;
            m++;
        }

        for (int i : letters){
            System.out.println(i);
        }

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        //Er wordt een mogelijkheid gemaakt om de naam van de speler in te voeren
        game.batch.draw(background, 0, 0, Constants.WIDTH, Constants.HEIGHT);
//        font.setColor(Color.WHITE);

        for (int i : x) {
            if (input.isKeyJustPressed(i)) {
                System.out.println(i);
                if (i != 62 && i != 67) {
                    for(int d : letters){
                        if (d == i){
                            playername += Input.Keys.toString(d).toLowerCase();
                        }
                    }
                } else if (i == 67 & playername.length() > 0) {
                    playername = playername.substring(0, playername.length() - 1).toLowerCase();
                } else {
                    playername += " ";
                }

            }
        }

        font.draw(game.batch, playername, 180, 110);

        game.batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.batch.setProjectionMatrix(stage.getCamera().combined);
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
        backTexture.dispose();
        playTexture.dispose();
        background.dispose();
    }
}