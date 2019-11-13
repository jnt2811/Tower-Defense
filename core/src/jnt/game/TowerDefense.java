package jnt.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Screen.GameScreen;

public class TowerDefense extends Game {
	public SpriteBatch batch;
	public Sprite sprite;

	@Override
	public void create() {
		batch = new SpriteBatch();
		sprite = new Sprite();
		this.setScreen(new GameScreen(this));
	}
}
