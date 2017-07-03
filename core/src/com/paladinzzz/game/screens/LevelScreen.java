package com.paladinzzz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.paladinzzz.game.CrossplatformApp;
import com.paladinzzz.game.util.Constants;
import com.paladinzzz.game.util.playerMemory;

public class LevelScreen implements Screen {

    private OrthographicCamera camera;
    private CrossplatformApp game;
    public Stage levelstage;
    private Texture background, level1texture, level2texture, level3texture, backbutton;
    private ImageButton level1, level2, level3, back;
    private Drawable level1drawable, level2drawable, level3drawable, backdrawable;
    private Table table;
    private Sound click = Gdx.audio.newSound(Gdx.files.internal("Audio/click.wav"));

    public LevelScreen(CrossplatformApp game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.levelstage = new Stage(new FillViewport(Constants.WIDTH, Constants.HEIGHT, camera));
        this.background = new Texture("Screens/LevelScreen/LevelSelection.png");
        this.backbutton = new Texture("Screens/BackButton.png");
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
                click.play(Constants.soundLevel * 1.0f);
                MenuScreen.musicHandler.stopMusic();
                playerMemory.player.worldAndLevelData.setCurrentWorld(1);
                playerMemory.player.worldAndLevelData.setCurrentLevel(1);
                game.setScreen(new GameScreen(game));
                levelstage.dispose();
            }
        });

        level2drawable = new TextureRegionDrawable(new TextureRegion(level2texture));
        level2 = new ImageButton(level2drawable);
        level2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play(1.0f * Constants.soundLevel);
                System.out.println("Level 2 clicked");
                if (playerMemory.player.levelOneDone) {
                    MenuScreen.musicHandler.stopMusic();
                    playerMemory.player.worldAndLevelData.setCurrentWorld(2);
                    playerMemory.player.worldAndLevelData.setCurrentLevel(1);
                    levelstage.dispose();
                } else {
                    System.out.println("Complete World 1 first!");
                }
            }
        });
//mega penis
        level3drawable = new TextureRegionDrawable(new TextureRegion(level3texture));
        level3 = new ImageButton(level3drawable);
        level3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level 3 clicked");
                if (playerMemory.player.levelTwoDone) {
                    MenuScreen.musicHandler.stopMusic();
                    playerMemory.player.worldAndLevelData.setCurrentWorld(3);
                    playerMemory.player.worldAndLevelData.setCurrentLevel(1);
                    levelstage.dispose();
                } else {
                    System.out.println("Complete World 2 first!");
                }
                click.play(2.0f);
            }
        });

        backdrawable = new TextureRegionDrawable(new TextureRegion(backbutton));
        back = new ImageButton(backdrawable);
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Back button clicked");
                game.setScreen(new LoginScreen(game));
                levelstage.dispose();
                click.play(2.0f);
            }
        });

        Gdx.input.setInputProcessor(levelstage);

        table = new Table();
        table.setFillParent(true);
        table.top();
        table.add().expandX();
        table.add(level1).expandX().padTop(190);
        table.add(level2).expandX().padTop(190);
        table.add(level3).expandX().padTop(190);
        table.add().expandX();
        table.add().expandX();
        table.row();
        table.add(back).expandX().padTop(50);

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
        background.dispose();
        level1texture.dispose();
        level2texture.dispose();
        level3texture.dispose();
        backbutton.dispose();
    }
}
