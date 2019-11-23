package jnt.game.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Screen.DefeatScreen;
import jnt.game.Screen.GameScreen;
import jnt.game.Screen.MenuScreen;
import jnt.game.Screen.VictoryScreen;

public class TowerDefense extends Game {
	public SpriteBatch batch;
	public Sprite sprite;

	@Override
	public void create() {
		batch = new SpriteBatch();
		sprite = new Sprite();
		this.setScreen(new MenuScreen(this));
	}
}
