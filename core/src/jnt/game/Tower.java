package jnt.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tower extends GameEntity {

    private Texture tower;

    public Tower(int id, float x, float y) {
        super(id, x, y);
        if(id == 1) tower = new Texture("Tower1.png");
        if(id == 2) tower = new Texture("Tower2.png");
        if(id == 3) tower = new Texture("Tower3.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(tower, x, y);
    }

    @Override
    public void update() {

    }

    //function : shoot, ...
}
