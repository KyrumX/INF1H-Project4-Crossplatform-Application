package com.paladinzzz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.paladinzzz.game.CrossplatformApp;
import com.paladinzzz.game.database.Database;
import com.paladinzzz.game.util.Constants;


public class HighScoresScreen implements Screen{

    private CrossplatformApp game;
    private Stage stage;
    private TextButton backButton;
    private OrthographicCamera camera;
    private Table table;
    BitmapFont font = new BitmapFont();
    private Skin skin;
    private Texture background;
    private String[] data;
    private String[] playerscores;
    String nm = "";
    String sc = "";

    public HighScoresScreen (CrossplatformApp game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new FillViewport(Constants.WIDTH, Constants.HEIGHT, camera));
        this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        this.background = new Texture("Screens/HighscoresScreen/highscores.png");
    }


    @Override
    public void show() {

        backButton = new TextButton("back", skin);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
                MenuScreen.musicHandler.stopMusic();
            }
        });

        //Hiermee kunnen elementen nu aan de stage worden toegevoegd
        Gdx.input.setInputProcessor(stage);

        Database database = new Database();
        data = database.getNames(database.connect());
        playerscores = database.getScores(database.connect());

        // geeft de namen weer
        for (String s : data){
            if (s != null) {
                nm += s;
                nm += "\n\n";
            } else {
                break;
            }
        }

        // geeft de scores weer
        for (String s : playerscores){
            if (s != null) {
                sc += s;
                sc += " Points\n\n";
            } else {
                break;
            }
        }


        //Een table wordt aangemaakt om buttons aan toe te voegen.
        table = new Table();
        table.setFillParent(true);
        table.bottom();
        table.add(backButton).size(100, 100);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        font.draw(game.batch, nm, 170, 300);
        font.draw(game.batch, sc, 480, 300);

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
