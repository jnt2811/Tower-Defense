package jnt.game.Entity.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Entity.Entity;
import jnt.game.Entity.bullet.NormalBullet;
import jnt.game.Entity.enemy.NormalEnemy;

import java.util.ArrayList;

public class NormalTower extends Entity {

    protected Sprite towerBase, towerGun;
    protected int range;

    private ArrayList<NormalEnemy> enemies, targets;
    private ArrayList<NormalBullet> normalBullets;
    private float move;

    public NormalTower(int x, int y, ArrayList<NormalEnemy> enemies) {

        this.x = x;
        this.y = y;
        this.enemies = enemies;

        towerBase = new Sprite(new Texture(Gdx.files.internal("towerBase1.png")));
        towerGun = new Sprite(new Texture(Gdx.files.internal("towerGun1.png")));

        normalBullets = new ArrayList<>();
        targets = new ArrayList<>();

        // Default
        range = 150;

        setActive(true);
    }

    @Override
    public void draw(SpriteBatch batch) {

        if(isActive()) {

            // Draw Bullets
            for (NormalBullet normalBullet : normalBullets) normalBullet.draw(batch);

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
            for(NormalEnemy target : targets)
                createBullets(batch, target);

            update();
        }
    }

    @Override
    public void update() {

        // Lock the Target
        lockTarget(enemies);

        // Unlock the Target
        unlockTarget(targets);
    }


    public void lockTarget(ArrayList<NormalEnemy> enemies) {

        for (NormalEnemy normalEnemy : enemies) {

            if (distance(normalEnemy) <= range) {

                if(targets.isEmpty())
                    targets.add(normalEnemy);

                else if (!targets.isEmpty()) {

                    for(NormalEnemy target : targets)
                        gunRotate(target);
                }
            }
        }
    }


    public void unlockTarget(ArrayList<NormalEnemy> targets) {

        for(int i = 0; i < targets.size(); i++) {
            if((distance(targets.get(i)) > range || !targets.get(i).isActive()) && !targets.isEmpty())
                targets.remove(targets.get(i));
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
        if(towerGun.getRotation() <= angle) towerGun.setRotation(move += 10);
        if(towerGun.getRotation() > angle) towerGun.setRotation(move -= 10);
    }


    public void createBullets(SpriteBatch batch, NormalEnemy target) {

        for (int i = 0; i< normalBullets.size(); i++) {

            normalBullets.get(i).draw(batch);

            // Remove bullet from Bullets Array
            if(!normalBullets.get(i).isActive())
                normalBullets.remove(i);
        }

        // Always Add an Bullet to the Bullets Array if It's Empty
        if(normalBullets.size() < 1) {

            normalBullets.add(new NormalBullet(towerGun.getX() + towerGun.getWidth()/2, towerGun.getY() + towerGun.getHeight()/2, target));
        }
    }
}
