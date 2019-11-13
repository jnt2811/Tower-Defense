package jnt.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Tower extends Entity {

    private Texture tower;
    private int x, y;
    private ArrayList<Bullet> bullets;
    private boolean active;
    private Sprite sprite;

    public Tower(SpriteBatch batch) {
        super(batch);

        tower = new Texture(Gdx.files.internal("Tower2.png"));
        x = tower.getWidth()*3;
        y = tower.getHeight()*2;

        bullets = new ArrayList<>();

        bullets.add(new Bullet(batch, x+ 50, y + 50));
    }

    @Override
    public void draw() {
        batch.draw(tower, x, y);
    }

    @Override
    public void update() {

    }

    public void drawBullets(boolean enemyIsAlive) {
        if(enemyIsAlive) setActive(true);

        //vẽ đạn
        for (int i=0; i < bullets.size(); i++) {
                if(!bullets.get(i).isRemove()) {
                    bullets.get(i).draw();
                    bullets.get(i).increaseX();
                }
                if(bullets.get(i).getX() >= 1450) bullets.get(i).setRemove(true);
        }

        if (bullets.get(bullets.size() - 1).getX() > 500 && bullets.size() < 1000 && isActive()) {
            bullets.add(new Bullet(batch, x + 50, y + 50));
            setActive(false);
        }
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isActive() {
        return active;
    }

    public int getBulletsSize() {
        return bullets.size();
    }

    public int getEachX(int i) {
        return bullets.get(i).getX();
    }
    public int getEachY(int i) {
        return bullets.get(i).getY();
    }

    public void setEachRemove(int i, boolean b) {
        bullets.get(i).setRemove(b);
    }

    public int getBulletDamage(int i) {
        return bullets.get(i).getDamage();
    }
}
