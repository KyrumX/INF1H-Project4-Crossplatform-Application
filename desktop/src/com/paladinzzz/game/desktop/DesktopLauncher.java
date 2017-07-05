package com.paladinzzz.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = com.paladinzzz.game.util.Constants.WIDTH;
		config.height = com.paladinzzz.game.util.Constants.HEIGHT;
		config.title = "Diggy the Mole";
		new LwjglApplication(new com.paladinzzz.game.CrossplatformApp(), config);
	}
}
