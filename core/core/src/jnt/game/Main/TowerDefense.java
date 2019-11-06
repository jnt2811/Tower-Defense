package jnt.game.Main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Screen.GameScreen;

public class TowerDefense extends Game {

    public SpriteBatch batch;
    public Music buttonSound;

    @Override
    public void create() {
        batch = new SpriteBatch();
        buttonSound = Gdx.audio.newMusic(Gdx.files.internal("buttonSound.wav"));
        this.setScreen(new GameScreen(this));
    }
}