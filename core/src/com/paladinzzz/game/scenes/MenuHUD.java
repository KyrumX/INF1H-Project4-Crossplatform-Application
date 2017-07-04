package com.paladinzzz.game.scenes;

import com.badlogic.gdx.Gdx;
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

/**
 * Created by ryanw on 4-7-2017.
 */

public class MenuHUD implements Disposable {
    public Stage button2stage;
    public Stage button3stage;
    private Viewport viewport;

    //HUD tekst:
    private String worldName;
    private int score;
    private Label label2;
    private Label label3;
    public static boolean clicked = false;


    public MenuHUD(SpriteBatch batch, String worldName) {
        this.viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        this.button2stage = new Stage(viewport, batch);
        this.button3stage = new Stage(viewport, batch);
        this.score = 0;
        this.worldName = worldName;

        //Maken van de labels:
        label2 = new Label(("Complete level 1 first!"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        label3 = new Label(("Complete level 2 first!"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        Table table2 = new Table();
        table2.center();
        table2.setFillParent(true);
        table2.add(label2);

        Table table3 = new Table();
        table3.center();
        table3.setFillParent(true);
        table3.add(label3);

        if (clicked) {
            button2stage.clear();
        }
        else {
            button2stage.addActor(table2);
        }

        if (clicked) {
            button3stage.clear();
        }
        else {
            button3stage.addActor(table3);
        }
    }

    public void removeSpaceText(){
        this.button2stage.clear();
        this.button3stage.clear();
    }

    @Override
    public void dispose() {
        this.button2stage.dispose();
        this.button3stage.dispose();
    }
}


