package com.paladinzzz.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.paladinzzz.game.audio.MusicHandler;
import com.paladinzzz.game.database.JSONfunctions;
import com.paladinzzz.game.database.parseJSON;
import com.paladinzzz.game.screens.MenuScreen;
import com.paladinzzz.game.util.playerMemory;

public class CrossplatformApp extends Game {
	public static SpriteBatch batch;

	@Override
	public void create () {
		MusicHandler musicHandler = new MusicHandler("Music/Main_Menu_Theme.ogg", true);
		musicHandler.playMusic();
        batch = new SpriteBatch();
        setScreen(new MenuScreen(this, musicHandler));
		try {
			JSONfunctions json = new JSONfunctions();
			parseJSON parse = new parseJSON(json.doInBackground());

			for (String i : parse.getNames()){
				if(i != null) {
					System.out.println(i);
				}
			}

			for (String i : parse.getScores()){
				if(i != null) {
					System.out.println(i);
				}
			}

			playerMemory.isConnected = true;
		}
		catch (Exception e){
			System.out.println(e);
			playerMemory.isConnected = false;
		}
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
