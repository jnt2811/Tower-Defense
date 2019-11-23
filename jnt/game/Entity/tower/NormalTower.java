package jnt.game.Entity.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import jnt.game.Entity.Entity;
import jnt.game.Entity.bullet.Bullet;
import jnt.game.Entity.enemy.NormalEnemy;
import jnt.game.Map.Tile;

import java.text.Normalizer;
import java.util.ArrayList;

public class NormalTower extends Entity implements Disposable {

    protected Sprite towerBase, towerGun;
    protected int range, price;
    protected String type;
    protected float coolDown, timer;

    private ArrayList<NormalEnemy> enemies, targets;
    private ArrayList<Bullet> bullets;
    private float move;

    public NormalTower(ArrayList<NormalEnemy> enemies, Tile place) {

        this.enemies = enemies;
        this.x = place.getX();
        this.y = place.getY();

        towerBase = new Sprite(new Texture(Gdx.files.internal("towerBase1.png")));
        towerGun = new Sprite(new Texture(Gdx.files.internal("towerGun1.png")));

        // Default
        type = "normal";
        range = 200;
        price = 10;
        coolDown = 0.2f;
        timer = coolDown;

        bullets = new ArrayList<>();
        targets = new ArrayList<>();

        setActive(false);
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {

        // Draw Bullets
        for (Bullet bullet : bullets) bullet.draw(batch, delta);

        // Draw the Base and the Gun
        towerBase.draw(batch);
        towerGun.draw(batch);

        // Set Base's Position
        towerBase.setX((float)x);
        towerBase.setY((float)y);

        // Set Gun's Position
        towerGun.setX((float)x);
        towerGun.setY((float)y);

        // Set Bullets FIRED
        drawBullets(batch, delta);
    }

    @Override
    public void update(float delta) {

        if(isActive()) {

            // Lock the Target
            lockTarget(enemies);

            // Unlock the Target
            unlockTarget(targets);

            // Bullets update
            for(NormalEnemy target : targets)
                updateBullets(target, delta);
        }
    }


    public void lockTarget(ArrayList<NormalEnemy> enemies) {

        for (NormalEnemy enemy : enemies) {

            if (distance(enemy) <= range) {

                if(targets.isEmpty()) {
                    targets.add(enemy);
                }

                if (!targets.isEmpty()) {

                    for(NormalEnemy target : targets)
                        gunRotate(target);
                }
            }
        }
    }


    public void unlockTarget(ArrayList<NormalEnemy> targets) {

        for(int i = 0; i < targets.size(); i++) {
            if(((distance(targets.get(i)) > range && (!targets.get(i).isActive() || targets.get(i).isActive()))
                    || (distance(targets.get(i)) <= range && !targets.get(i).isActive())) && !targets.isEmpty()) {

                targets.remove(i);
            }
        }
    }


    // Calculate the Distance between Tower and Target
    public double distance(NormalEnemy target) {

        double a = Math.abs(target.getX() - x);
        double b = Math.abs(target.getY() - y);
        double c = Math.pow(a, 2) + Math.pow(b, 2);
        return Math.sqrt(c);
    }


    private void gunRotate(NormalEnemy target) {

        // Calculate the Angle between Tower's center Point and Target's Center Point
        double angle = Math.toDegrees( Math.atan2( x - target.getX() , target.getY() - y) );

        // Set Gun's Rotation
        if(towerGun.getRotation() <= angle) towerGun.setRotation(move += 15);
        if(towerGun.getRotation() > angle) towerGun.setRotation(move -= 15);
    }

    public void drawBullets(SpriteBatch batch, float delta) {
        for (Bullet bullet : bullets) bullet.draw(batch, delta);
    }

    public void updateBullets(NormalEnemy target, float delta) {

        for (int i = 0; i< bullets.size(); i++) {

            // Remove bullet from Bullets Array
            if(!bullets.get(i).isActive())
                bullets.remove(i);
        }

        // Always Add an Bullet to the Bullets Array if It's Empty
        timer -= delta;
        if(timer <= 0) {
            bullets.add(new Bullet(type,towerGun.getX() + towerGun.getWidth()/2, towerGun.getY() + towerGun.getHeight()/2, target));
            timer = coolDown;
        }
    }

    public int getPrice() {
        return price;
    }

    @Override
    public void dispose() {
        towerBase.getTexture().dispose();
        towerGun.getTexture().dispose();
        for(Bullet bullet : bullets) bullet.dispose();
    }
}
