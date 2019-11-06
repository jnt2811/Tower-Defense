package jnt.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet extends Entity {

    private Texture bullet;

    public Bullet(SpriteBatch batch) {
        super(batch);

        bullet = new Texture(Gdx.files.internal("bullet.png"));
    }

    public void drawBullet(int x, int y) {
        batch.draw(bullet, x, y);
    }

    public int getWidth() {
        return bullet.getWidth();
    }
    public int getHeight() {
        return bullet.getHeight();
    }

    @Override
    public void draw() {
    }

    @Override
    public void update() {

    }
}
