package com.paladinzzz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.paladinzzz.game.database.Database;

import com.paladinzzz.game.database.JSONfunctions;
import com.paladinzzz.game.database.parseJSON;
import com.paladinzzz.game.scenes.HighScoresHUD;
import com.paladinzzz.game.util.Constants;


public class HighScoresScreen implements Screen{

    private com.paladinzzz.game.CrossplatformApp game;
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
    private Sound click = Gdx.audio.newSound(Gdx.files.internal("Audio/click.wav"));
    private Actor nameslbl = new Label(("test"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

    public HighScoresScreen (com.paladinzzz.game.CrossplatformApp game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new FillViewport(com.paladinzzz.game.util.Constants.WIDTH, com.paladinzzz.game.util.Constants.HEIGHT, camera));
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
                click.play(2.0f);
            }
        });

        //Hiermee kunnen elementen nu aan de stage worden toegevoegd
        Gdx.input.setInputProcessor(stage);

        JSONfunctions json = new JSONfunctions();
        parseJSON parse = new parseJSON(json.doInBackground());

        Table nametable = new Table();
        nametable.center().padRight(420f).padBottom(115f);
        nametable.setFillParent(true);
//        nametable.add(nameslbl);

        for (String i : parse.getNames()){
            Label nameslabel = new Label((i), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            if(i != null) {
                nametable.add(new Label((i), new Label.LabelStyle(new BitmapFont(), Color.WHITE))).padBottom(15f);
                nametable.row();
            } else {
                break;
            }
        }

        Table scoretable = new Table();
        scoretable.center().padLeft(450f).padBottom(115f);
        scoretable.setFillParent(true);
//        nametable.add(nameslbl);

        for (String i : parse.getScores()){
            Label scoreslabel = new Label((i), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            if(i != null) {
                scoretable.add(new Label((i), new Label.LabelStyle(new BitmapFont(), Color.WHITE))).padBottom(15f);
                scoretable.row();
            } else {
                break;
            }
        }

        backButton = new TextButton("back", skin);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
                MenuScreen.musicHandler.stopMusic();
            }
        });

        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.bottom();
        table.add(backButton).size(100, 100);
        stage.addActor(table);
        stage.addActor(nametable);
        stage.addActor(scoretable);

//        stage.addActor(this.nameslbl);
    }

    @Override
    public void render(float delta) {
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
