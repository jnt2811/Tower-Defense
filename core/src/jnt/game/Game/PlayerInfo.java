package jnt.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class PlayerInfo implements Disposable {

    private int health, gold;
    private BitmapFont healthNum, goldNum;
    private Texture heart, coin;

    public PlayerInfo() {

        // Default
        health = 10;
        gold = 100;

        // Textures
        heart = new Texture(Gdx.files.internal("heart.png"));
        coin = new Texture(Gdx.files.internal("coin.png"));

        // Info
        healthNum = new BitmapFont(Gdx.files.internal("health.fnt"));
        goldNum = new BitmapFont(Gdx.files.internal("health.fnt"));
    }

    public void draw(SpriteBatch batch) {

        batch.draw(heart,800, 500);
    }

    @Override
    public void dispose() {
        heart.dispose();
        coin.dispose();
        healthNum.dispose();
        goldNum.dispose();
    }
}
