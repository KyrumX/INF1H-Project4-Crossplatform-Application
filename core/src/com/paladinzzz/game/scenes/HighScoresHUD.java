package com.paladinzzz.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.paladinzzz.game.database.JSONfunctions;
import com.paladinzzz.game.database.parseJSON;
import com.paladinzzz.game.util.Constants;

import static com.paladinzzz.game.screens.LoginScreen.playername;

public class HighScoresHUD implements Disposable {
    public Stage hudStage;
    private Viewport viewport;
    JSONfunctions json = new JSONfunctions();
    parseJSON parse = new parseJSON(json.doInBackground());
    Table nametable = new Table();
    Table scoretable = new Table();
    SpriteBatch batch;


    public HighScoresHUD(SpriteBatch batch) {
        this.batch = batch;
        this.viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        this.hudStage = new Stage(viewport, this.batch);

        getNames();
        this.nametable.top();
        this.nametable.setFillParent(true);

        getScores();
        this.scoretable.top();
        this.scoretable.setFillParent(true);

//        Table table = new Table();
//        table.top();
//        table.setFillParent(true);

//        table1.add(scoreLabel).expandX().padTop((float) 1);
//        table1.add(playerNameLabel).expandX().padTop((float) 1);

        this.hudStage.addActor(this.nametable);
        this.hudStage.addActor(this.scoretable);

    }

    @Override
    public void dispose() {
        this.hudStage.dispose();
    }

    public void getNames(){
        for (String i : parse.getNames()){
            Label nameslabel = new Label((i), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            if(i != null) {
                this.nametable.add(nameslabel);
            } else {
                break;
            }
        }
    }

    public void getScores(){
        for (String i : parse.getScores()){
            Label scoreslabel = new Label((i), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            if(i != null) {
                this.scoretable.add(scoreslabel);
            } else {
                break;
            }
        }
    }
}