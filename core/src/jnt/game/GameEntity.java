package jnt.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameEntity {

    protected int id;
    protected float x, y;

    public GameEntity(int id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public abstract void render(SpriteBatch batch);

    public abstract void update();
}
