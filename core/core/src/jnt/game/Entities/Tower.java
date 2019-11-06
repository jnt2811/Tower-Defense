package jnt.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tower extends Entity {

    private Texture tower;
    private Bullet bullet;

    public Tower(SpriteBatch batch) {
        super(batch);

        tower = new Texture(Gdx.files.internal("Tower2.png"));
        bullet = new Bullet(batch);

        x = tower.getWidth()*5;
        y = tower.getHeight()*3;
    }

    @Override
    public void draw() {
        batch.draw(tower, x, y);
        bullet.drawBullet((bullet.getWidth()+tower.getWidth())/2 + x, (bullet.getHeight()+tower.getHeight())/2 + y);
    }

    @Override
    public void update() {

    }
}
