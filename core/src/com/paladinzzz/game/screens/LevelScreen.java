package com.paladinzzz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.paladinzzz.game.CrossplatformApp;
import com.paladinzzz.game.util.Constants;

/**
 * Created by Ralph on 28-6-2017.
 */

public class LevelScreen implements Screen {

    private OrthographicCamera camera;
    private CrossplatformApp game;
    public Stage levelstage;
    private Texture background, level1texture, level2texture, level3texture;
    private ImageButton level1, level2, level3;
    private Drawable level1drawable, level2drawable, level3drawable;
    private Table table;


    public LevelScreen(CrossplatformApp game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.levelstage = new Stage(new FillViewport(Constants.WIDTH, Constants.HEIGHT, camera));
        this.background = new Texture("Screens/LevelScreen/LevelSelection.png");
        this.level1texture = new Texture("Screens/LevelScreen/Button1.png");
        this.level2texture = new Texture("Screens/LevelScreen/Button2.png");
        this.level3texture = new Texture("Screens/LevelScreen/Button3.png");
    }



    @Override
    public void show() {

        level1drawable = new TextureRegionDrawable(new TextureRegion(level1texture));
        level1 = new ImageButton(level1drawable);
        level1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                levelstage.dispose();
            }
        });

        level2drawable = new TextureRegionDrawable(new TextureRegion(level2texture));
        level2 = new ImageButton(level2drawable);
        level2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level 2 clicked");
                levelstage.dispose();
            }
        });

        level3drawable = new TextureRegionDrawable(new TextureRegion(level3texture));
        level3 = new ImageButton(level3drawable);
        level3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level 3 clicked");
                levelstage.dispose();
            }
        });

        Gdx.input.setInputProcessor(levelstage);

        table = new Table();
        table.setFillParent(true);
        table.top();
        table.add(level1).padRight(57).padTop(190);
        table.add(level2).padRight(40).padTop(190);
        table.add(level3).padRight(50).padTop(190);

        levelstage.addActor(table);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        game.batch.end();
        levelstage.draw();

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
        levelstage.dispose();

    }
}
