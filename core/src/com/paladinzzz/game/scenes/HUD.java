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
import com.paladinzzz.game.util.Constants;
import com.paladinzzz.game.util.playerMemory;

import static com.paladinzzz.game.screens.LoginScreen.playername;

public class HUD implements Disposable {
    public Stage hudStage;
    private Viewport viewport;

    //HUD tekst:
    private String worldName;
    private Label worldNameLabel;
    private int score;
    private Label scoreLabel;
    private Label playerNameLabel;
    private Label startLabel;
    public static boolean spacepressed = false;



    public HUD(SpriteBatch batch, String worldName) {
        this.viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        this.hudStage = new Stage(viewport, batch);
        this.score = 0;
        this.worldName = worldName;

        //Maken van de labels:
        scoreLabel = new Label(String.format("Score: " + "%03d", score), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        worldNameLabel = new Label((worldName), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        playerNameLabel = new Label(playername, new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        startLabel = new Label(("Click the screen to jump!"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        Table table1 = new Table();
        table1.center();
        table1.setFillParent(true);
        table1.add(startLabel);

        if (spacepressed) {
            hudStage.clear();
        } else {
            hudStage.addActor(table1);
        }

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(worldNameLabel).expandX().padTop((float) 1);
        table.add(scoreLabel).expandX().padTop((float) 1);
        table.add(playerNameLabel).expandX().padTop((float) 1);

        hudStage.addActor(table);

    }

    public void removeSpaceText(){
        this.hudStage.clear();
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(worldNameLabel).expandX().padTop((float) 1);
        table.add(scoreLabel).expandX().padTop((float) 1);
        table.add(playerNameLabel).expandX().padTop((float) 1);

        hudStage.addActor(table);
    }

    public void update(float dt) {
        this.scoreLabel.setText(String.format("Score: " + "%03d", playerMemory.player.getScore()));
    }

    @Override
    public void dispose() {
        this.hudStage.dispose();
    }
}