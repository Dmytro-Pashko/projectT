package com.mytrex.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mytrex.game.Trex;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "T-rex runner";
		config.height=512;
		config.width=512;
		new LwjglApplication(new Trex(), config);
	}
}
