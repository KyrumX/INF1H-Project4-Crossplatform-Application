package com.paladinzzz.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

//De launcher voor de desktop versie van het spel: Hierin zetten we de configuratie van desktop specifieke eigenschappen e.g. de tite

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = com.paladinzzz.game.util.Constants.WIDTH;
		config.height = com.paladinzzz.game.util.Constants.HEIGHT;
		config.title = "Diggy the Mole";
		new LwjglApplication(new com.paladinzzz.game.CrossplatformApp(), config);
	}
}
