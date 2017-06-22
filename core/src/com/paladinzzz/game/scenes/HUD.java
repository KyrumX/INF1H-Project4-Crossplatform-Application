package com.paladinzzz.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.paladinzzz.game.util.Constants;

/**
 * Created by aaron on 20-Jun-17.
 */

public class HUD  {
    public Stage hudStage;
    private Viewport viewport;

    //HUD tekst:
    private String worldName;
    private Label worldNameLabel;
    private int score;
    private Label scoreLabel;
    private String playerName;
    private Label playerNameLabel;



    public HUD(SpriteBatch batch, String worldName) {
        this.viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        this.hudStage = new Stage(viewport, batch);
        this.score = 0;
        this.worldName = worldName;
        this.playerName = "PLACEHOLDER";

        //Maken van de labels:
        scoreLabel = new Label(String.format("Score: " + "%03d", score), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        worldNameLabel = new Label(("WORLDNAME HERE"), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        playerNameLabel = new Label(playerName, new Label.LabelStyle(new BitmapFont(), Color.GOLD));

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(worldNameLabel).expandX().padTop((float) 65);
        table.add(scoreLabel).expandX().padTop((float) 65);
        table.add(playerNameLabel).expandX().padTop((float) 65);

        hudStage.addActor(table);

    }
}