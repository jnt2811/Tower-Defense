package jnt.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet extends Entity {

    private Texture bullet;
    private int x, y, distance;
    private int speed, damage;
    private boolean remove;

    public Bullet(SpriteBatch batch, int x, int y, Enemy target) {
        super(batch);
        this.x = x;
        this.y = y;
        this.target = target;

        bullet = new Texture(Gdx.files.internal("bullet.png"));
        setRemove(false);

        speed = 5;
        damage = 4;
    }

    @Override
    public void draw() {
        batch.draw(bullet, x, y);
    }

    @Override
    public void update() {

    }

    public int getWidth() {
        return bullet.getWidth();
    }
    public int getHeight() {
        return bullet.getHeight();
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }

    public int increaseX() {
        return x += speed;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }
    public boolean isRemove() {
        return remove;
    }

    public int getDamage() {
        return damage;
    }
}
