package jnt.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import jnt.game.Game.TowerDefense;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new TowerDefense(), config);

		config.width = 1560;
		config.height = 900;
		config.resizable = false;
	}
}
