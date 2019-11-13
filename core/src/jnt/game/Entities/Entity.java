package jnt.game.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {

    protected int x, y;
    protected SpriteBatch batch;

    public Entity(SpriteBatch batch) {
        this.batch = batch;
    }

    public abstract void draw();

    public abstract void update();
}
