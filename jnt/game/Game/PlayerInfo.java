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
        health = 1;
        gold = 50;

        // Textures
        heart = new Texture(Gdx.files.internal("heart.png"));
        coin = new Texture(Gdx.files.internal("coin.png"));

        // Info
        healthNum = new BitmapFont(Gdx.files.internal("health.fnt"));
        goldNum = new BitmapFont(Gdx.files.internal("health.fnt"));
    }

    public void draw(SpriteBatch batch) {

        batch.draw(heart,1240, 780);
        batch.draw(coin, 1240, 660);

        healthNum.draw(batch, "" + health, 1410, 860);
        goldNum.draw(batch, "" + gold, 1410, 740);
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }

    public void decreaseHealth() {
        health--;
    }
    public void decreaseGold(int price) {
        gold -= price;
    }
    public void increaseGold(int reward) {
        gold += reward;
    }

    @Override
    public void dispose() {
        heart.dispose();
        coin.dispose();
        healthNum.dispose();
        goldNum.dispose();
    }
}
