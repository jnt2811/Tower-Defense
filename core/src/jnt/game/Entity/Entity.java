package jnt.game.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {

    protected int id;
    protected double x, y;
    protected float time = 0;
    protected boolean active;

    public Entity() {
        this.active = true;
    }

    public abstract void draw(SpriteBatch batch);
    public abstract void update();

    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isActive() {
        return active;
    }

    public void setX(int x) {
        this.x = x;
    }
    public double getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public double getY() {
        return y;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}