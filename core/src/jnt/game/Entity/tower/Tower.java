package jnt.game.Entity.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Entity.Entity;
import jnt.game.Entity.bullet.Bullet;
import jnt.game.Entity.enemy.Enemy;

import java.util.ArrayList;

public class Tower extends Entity {

    private Sprite towerGun;
    private Texture towerBase;
    private int range;
    private ArrayList<Enemy> enemies, targets;
    private ArrayList<Bullet> bullets;
    private float move = 0;
    private boolean rotate;

    public Tower(int id, int x, int y, int range, ArrayList<Enemy> enemies) {

        this.id = id;
        this.x = x;
        this.y = y;
        this.range = range;
        this.enemies = enemies;

        if(id == 1) {
            towerBase = new Texture(Gdx.files.internal("towerBase1.png"));
            towerGun = new Sprite(new Texture(Gdx.files.internal("towerGun1.png")));
        }
        if(id == 2) {
            towerBase = new Texture(Gdx.files.internal("towerBase2.png"));
            towerGun = new Sprite(new Texture(Gdx.files.internal("towerGun2.png")));
        }
        towerGun.setX(x);
        towerGun.setY(y);

        bullets = new ArrayList<>();

        targets = new ArrayList<>();

        //default
        setActive(true);
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {

        if(isActive()) {
            batch.draw(towerBase, x, y);
            for (Bullet bullet : bullets) bullet.draw(batch, delta);
            towerGun.draw(batch);
        }

        //set bullets fired
        for(Enemy target : targets) createBullets(batch, delta, target);

        update();
    }

    @Override
    public void update() {

        //lock the target
        lockTarget(enemies);

        //unlock the target
        unlockTarget(targets);
    }

    public void lockTarget(ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {

            if (distance(enemy) <= range) {

                if(targets.isEmpty()) {
                    targets.add(enemy);
                }
                else if (!targets.isEmpty()) {
                    for(Enemy target : targets) {
                        gunRotate(target);
                    }
                }
            }
        }
    }

    public void unlockTarget(ArrayList<Enemy> targets) {

        for(int i = 0; i < targets.size(); i++)
        {
            if((distance(targets.get(i)) > range || !targets.get(i).isActive()) && !targets.isEmpty()) {
                targets.remove(targets.get(i));
            }
        }
    }

    //caculate the distance between tower and target
    public double distance(Enemy target) {

        double a = Math.abs(target.getX() - x);
        double b = Math.abs(target.getY() - y);
        double c = Math.pow(a, 2) + Math.pow(b, 2);
        return Math.sqrt(c);
    }

    private void gunRotate(Enemy target) {

        //caculate the angle between tower's center point and target's center point
        double angle = Math.toDegrees( Math.atan2( x - target.getX() , target.getY() - y) );

        //set the gun's rotation of tower
        if(towerGun.getRotation() <= angle) towerGun.setRotation(move += 2);
        if(towerGun.getRotation() > angle) towerGun.setRotation(move -= 2);
    }

    public void createBullets(SpriteBatch batch, float delta, Enemy target) {

        for (int i=0; i<bullets.size(); i++) {
            bullets.get(i).draw(batch, delta);
            if(!bullets.get(i).isActive()) {
                bullets.remove(i);
            }
        }

        //add an bullet after 5s (total 5 enemies)
        time += delta;
        if(time > 1.0 && time <= 1.02 && bullets.size() < 1) {
            bullets.add(new Bullet(id,(int)(towerGun.getX() + towerGun.getWidth()/2), (int)(towerGun.getY() + towerGun.getHeight()/2), 1, target));
            time = 0;
        }
    }
}
