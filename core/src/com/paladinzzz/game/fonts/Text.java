package com.paladinzzz.game.fonts;

/**
 * Created by Selim on 22/6/2017.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text extends ApplicationAdapter {
    SpriteBatch batch;
    BitmapFont font;

    @Override
    public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("Papy.fnt"));
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        //Example One -- Drawing Text
        font.draw(batch,"Hello World",Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);

        batch.end();
    }
}