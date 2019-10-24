package jnt.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TowerDefense extends Game  {

	public SpriteBatch batch;
	public Music buttonSound;
	public Sprite sprite;


	public void create() {
		batch = new SpriteBatch();
		buttonSound = Gdx.audio.newMusic(Gdx.files.internal("buttonSound.wav"));
		this.setScreen(new StartScreen(this));

	}

	public void render() {
		super.render();
	}


}
