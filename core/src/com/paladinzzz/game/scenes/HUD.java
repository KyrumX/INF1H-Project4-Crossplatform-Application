package com.paladinzzz.game.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.paladinzzz.game.util.Constants;

/**
 * Created by aaron on 20-Jun-17.
 */

public class HUD  {
    public Stage hudStage;
    private Viewport viewport;



    public HUD(SpriteBatch batch) {
        this.viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        this.hudStage = new Stage(viewport, batch);
    }
}